package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ArabicNameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ArabicName {
    String message() default "Invalid Arabic name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
