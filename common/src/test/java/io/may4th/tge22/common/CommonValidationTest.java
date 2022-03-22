package io.may4th.tge22.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonValidationTest {

    @Test
    void test() {
        assertTrue("good".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse("b ad".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse("b\nad".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse(" b ad".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse("b ad ".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse(" b ad ".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse(" bad".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse("bad ".matches(CommonValidation.WITHOUT_WHITESPACE));
        assertFalse(" bad ".matches(CommonValidation.WITHOUT_WHITESPACE));

        assertTrue("good".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertTrue("b ad".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse("b\nad".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse(" b ad".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse("b ad ".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse(" b ad ".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse(" bad".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse("bad ".matches(CommonValidation.TRIMMED_SINGLE_LINE));
        assertFalse(" bad ".matches(CommonValidation.TRIMMED_SINGLE_LINE));

        assertTrue("good".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertTrue("b ad".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertTrue("b\nad".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertFalse(" b ad".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertFalse("b ad ".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertFalse(" b ad ".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertFalse(" bad".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertFalse("bad ".matches(CommonValidation.TRIMMED_MULTI_LINE));
        assertFalse(" bad ".matches(CommonValidation.TRIMMED_MULTI_LINE));
    }
}
