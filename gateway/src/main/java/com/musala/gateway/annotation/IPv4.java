package com.musala.gateway.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.musala.gateway.annotation.validator.IPv4Validator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
@Constraint(validatedBy = IPv4Validator.class)
public @interface IPv4 {
  String message() default "IPv4 Invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}