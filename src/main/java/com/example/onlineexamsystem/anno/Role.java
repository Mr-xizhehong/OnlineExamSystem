package com.example.onlineexamsystem.anno;

import com.example.onlineexamsystem.Validation.RoleValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented//元注解
@Target({ FIELD,PARAMETER})//元注解
@Retention(RUNTIME)//元注解
@Constraint(validatedBy = { RoleValidation.class})//指定提供校验规则的类
public @interface Role {
    //提供校验失败后的提示信息
    String message() default "职务只能是老师，学生";
    //指定分组
    Class<?>[] groups() default { };
    //负载  获取到State注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
