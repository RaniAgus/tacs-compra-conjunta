package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class RangeValidator implements ConstraintValidator<Range, Object> {
    private String minFieldName;
    private String maxFieldName;
    private boolean inclusive;

    @Override
    public void initialize(Range constraintAnnotation) {
        minFieldName = constraintAnnotation.min();
        maxFieldName = constraintAnnotation.max();
        inclusive = constraintAnnotation.inclusive();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            if (value == null) {
                return true;
            }

            BeanWrapper wrapper = new BeanWrapperImpl(value);

            Object minValue = wrapper.getPropertyValue(minFieldName);
            Object maxValue = wrapper.getPropertyValue(maxFieldName);

            if (minValue == null || maxValue == null) {
                return true;
            }

            BigDecimal min = new BigDecimal(minValue.toString());
            BigDecimal max = new BigDecimal(maxValue.toString());

            return inclusive ? min.compareTo(max) <= 0 : min.compareTo(max) < 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
