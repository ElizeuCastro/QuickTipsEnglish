package br.com.quicktipsenglish.test.util;

import org.junit.Before;
import org.junit.Test;

import br.com.quicktipsenglish.util.ValidationUtil;

import static junit.framework.Assert.assertEquals;

public class ValidationUtilTest {

    private ValidationUtil validationUtil;

    @Before
    public void setup() {
        validationUtil = ValidationUtil.getInstance();
    }

    @Test
    public void isEmpty_ShouldReturnFalse_Test() {
        boolean isValid = validationUtil.isEmpty("a", "teste");
        assertEquals(false, isValid);
    }

    @Test
    public void isEmpty_ShouldReturnTrue_Test() {
        boolean isValid = validationUtil.isEmpty("", "teste");
        assertEquals(true, isValid);
    }

    @Test
    public void isNoEmpty_ShouldReturnFalse_Test() {
        boolean isValid = validationUtil.isNotEmpty("", "teste");
        assertEquals(false, isValid);
    }

    @Test
    public void isNoEmpty_ShouldReturnTrue_Test() {
        boolean isValid = validationUtil.isNotEmpty("a", "teste");
        assertEquals(true, isValid);
    }

    @Test
    public void isValid_ShouldReturnTrue_Test() {
        boolean isValid = validationUtil.isValidEmail("maria@gmail.com");
        assertEquals(true, isValid);
    }

    @Test
    public void isValid_ShouldReturnFalse_Test() {
        boolean isValid = validationUtil.isValidEmail("maria#gmail.com");
        assertEquals(false, isValid);
    }

    @Test
    public void isValid_ShouldReturnFalseToo_Test() {
        boolean isValid = validationUtil.isValidEmail("a");
        assertEquals(false, isValid);
    }

}
