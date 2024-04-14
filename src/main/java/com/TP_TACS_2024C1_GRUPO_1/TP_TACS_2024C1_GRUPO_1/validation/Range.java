package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
public @interface Range {
    String message() default "The target value must be within the specified range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String min();
    String max();
    boolean inclusive() default true;
}
