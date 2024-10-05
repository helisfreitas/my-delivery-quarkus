package dev.helis.registration.validation;

import dev.helis.helper.annotation.ErrorTest;
import dev.helis.helper.annotation.SuccessTest;
import dev.helis.helper.annotation.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UnitTest
public class OnlyCharacterAndPunctuationForCharSequenceTest {

    @SuccessTest
    public void shouldReturnTrueWhenInputIsEmptyString() {
        OnlyCharacterAndPunctuationForCharSequence validator = new OnlyCharacterAndPunctuationForCharSequence();
        boolean isValid = validator.isValid("", null);
        assertEquals(true, isValid);
    }

    @ErrorTest
    void shouldReturnFalseWhenInputContainsOnlyAlphanumericCharacters() {
        OnlyCharacterAndPunctuationForCharSequence validator = new OnlyCharacterAndPunctuationForCharSequence();
        String input = "HelloWorld123";
        boolean isValid = validator.isValid(input, null);
        assertEquals(false, isValid);
    }
    
    @SuccessTest
    void shouldReturnTrueWithMultipleConsecutivePunctuationMarksCorrectly() {
        OnlyCharacterAndPunctuationForCharSequence validator = new OnlyCharacterAndPunctuationForCharSequence();
        String input = "Hello...World!!!";
        boolean isValid = validator.isValid(input, null);
        assertEquals(true, isValid);
    }

}