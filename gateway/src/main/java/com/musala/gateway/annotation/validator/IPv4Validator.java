package com.musala.gateway.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.musala.gateway.annotation.IPv4;

public class IPv4Validator implements ConstraintValidator<IPv4, String> {

    private static final String IPv4_PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

    private boolean validate(String ip) {
        if(ip == null) return false;
        return ip.matches(IPv4_PATTERN);
    }

    @Override
    public void initialize(IPv4 constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvContext) {
        return validate(value);
    }
}