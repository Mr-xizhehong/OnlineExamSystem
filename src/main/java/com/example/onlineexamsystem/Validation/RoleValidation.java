package com.example.onlineexamsystem.Validation;

import com.example.onlineexamsystem.anno.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.swing.plaf.nimbus.State;
import java.awt.*;

public class RoleValidation implements ConstraintValidator<Role,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null){
            return false;
        }
        if("学生".equals(value) || "老师".equals(value)){
            return true;
        }
        return false;
    }
}
