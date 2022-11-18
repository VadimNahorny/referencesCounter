package by.nahorny.referencescounter.service;

import org.apache.commons.validator.routines.UrlValidator;

public class ReferenceServiceSingleton {

    static ReferenceService referenceService;

    public static ReferenceService getReferenceService () {
        if (referenceService == null)
            return referenceService = new ReferenceService();
        else return referenceService;
    }
}

