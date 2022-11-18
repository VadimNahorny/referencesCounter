package by.nahorny.referencescounter.service;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidatorSingleton {

    private static UrlValidator urlValidator;

    public static UrlValidator getUrlValidator () {
        if (urlValidator == null)
            return urlValidator = new UrlValidator();
        else return urlValidator;
    }
}
