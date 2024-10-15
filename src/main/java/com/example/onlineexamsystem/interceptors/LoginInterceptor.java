package com.example.onlineexamsystem.interceptors;

import com.example.onlineexamsystem.Service.Impl.LoadUserByUsernameService;
import com.example.onlineexamsystem.utils.JwtUtil;
import com.example.onlineexamsystem.utils.ThreadLocalUtil;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@Component
public class LoginInterceptor extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LoadUserByUsernameService loadUserByUsernameService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        // 从请求头中获取Token
        String token = request.getHeader("Authorization");

        if (token != null) {
            try {
                // 从Redis中获取Token
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                String redisToken = operations.get(token);

                if (redisToken == null) {
                    // Token失效，设置响应状态码为401
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                // 验证Token
                Map<String, Object> claims = JwtUtil.parseToken(token);
                String username = (String) claims.get("username");
                UserDetails loginuser = loadUserByUsernameService.loadUserByUsername(username);
                // 将用户信息存储到SecurityContext中
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        loginuser, null, loginuser.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 把业务数据存储到ThreadLocal中
                ThreadLocalUtil.set(claims);
            } catch (Exception e) {
                // 如果验证失败，返回401错误
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // 放行
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } finally {
            // 清空ThreadLocal中的数据
            ThreadLocalUtil.remove();
        }
    }
}


