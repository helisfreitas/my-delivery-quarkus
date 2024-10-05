package dev.helis.registration.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyCharacterAndPunctuationForCharSequence
        implements ConstraintValidator<OnlyCharacterAndPunctuation, CharSequence> {

    private static final String VALID_PATTERN = "^[\\p{L}.!?\\-_:;'\\s]+$";

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        return value.toString().matches(VALID_PATTERN);
    }

}
