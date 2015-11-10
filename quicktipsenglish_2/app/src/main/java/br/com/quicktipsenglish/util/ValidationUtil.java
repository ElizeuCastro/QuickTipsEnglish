package br.com.quicktipsenglish.util;

import org.apache.commons.validator.GenericValidator;

public final class ValidationUtil {

    private static ValidationUtil instance;

    public static ValidationUtil getInstance() {
        if (instance == null) {
            instance = new ValidationUtil();
        }
        return instance;
    }

    public static boolean isNotEmpty(String... values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(String... values) {
        boolean empty = false;
        for (String value : values) {
            empty = value != null && GenericValidator.isBlankOrNull(value);
            if (empty) {
                empty = true;
                break;
            }
        }
        return empty;
    }

    public static boolean isValidEmail(final String email) {
        return GenericValidator.isEmail(email);
    }

}











