package com.masonwang.pnp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)

public @interface Password {

    String message() default "The password is not valid, please follow password rules.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}