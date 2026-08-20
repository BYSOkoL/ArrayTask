package com.arrayprocessor.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayValidatorTest {

    private static final String VALID_COMMA = "1, 2, 3";
    private static final String VALID_DASH = "1 - 2- 3";
    private static final String VALID_EMPTY = "   ";
    private static final String INVALID_LETTERS = "1y1 21 32";

    private final ArrayValidator validator = new ArrayValidator();

    @Test
    void shouldAcceptCommaSeparated() {
        // given
        String line = VALID_COMMA;

        // when
        boolean result = validator.isValid(line);

        // then
        assertTrue(result);
    }

    @Test
    void shouldAcceptEmptyLine() {
        // given
        String line = VALID_EMPTY;

        // when
        boolean result = validator.isValid(line);

        // then
        assertTrue(result);
    }

    @Test
    void shouldRejectLettersInTokens() {
        // given
        String line = INVALID_LETTERS;

        // when
        boolean result = validator.isValid(line);

        // then
        assertFalse(result);
    }
}
