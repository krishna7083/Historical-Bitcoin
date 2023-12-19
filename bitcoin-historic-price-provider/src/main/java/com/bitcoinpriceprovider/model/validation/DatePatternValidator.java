package com.bitcoinpriceprovider.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePatternValidator implements ConstraintValidator<DatePattern, String> {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    @Override
    public void initialize(DatePattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !isValidDateFormat(value)) {
            return false;
        }
        // Additional validation logic, if needed
        return true;
    }

    private boolean isValidDateFormat(String value) {
        Matcher matcher = DATE_PATTERN.matcher(value);
        return matcher.matches();
    }
}

