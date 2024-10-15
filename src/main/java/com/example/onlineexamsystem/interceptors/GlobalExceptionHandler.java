//package com.example.onlineexamsystem.interceptors;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    // 处理 AccessDeniedException (403 错误)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public String handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
//        // 输出错误信息到日志
//        logger.error("403 Forbidden error at URL: " + request.getRequestURL(), ex);
//        System.out.println(ex.getStackTrace());
//        // 返回自定义的错误信息
//        return "You do not have permission to access this resource.";
//    }
//
//    // 可以添加更多的异常处理方法，例如 404, 500 等
//}
