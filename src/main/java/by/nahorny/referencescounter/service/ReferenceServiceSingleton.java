package by.nahorny.referencescounter.service;

public class ReferenceServiceSingleton {

   private static ReferenceService referenceService;

    public static ReferenceService getReferenceService () {
        if (referenceService == null)
            return referenceService = new ReferenceService();
        else return referenceService;
    }
}

