//package com.example.onlineexamsystem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class MyCorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);  // 是否允许发送Cookie信息
//        config.addAllowedOriginPattern("*");  // 允许的域，注意 * 不能用于携带cookie的请求
//        config.addAllowedHeader("*");  // 允许的头部
//        config.addAllowedMethod("*");  // 允许的HTTP请求方法
//
//        // 对所有路径应用该配置
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
//}
