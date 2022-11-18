package by.nahorny.referencescounter.service;

import by.nahorny.referencescounter.entity.Reference;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static by.nahorny.referencescounter.service.strings.Message.*;

public class ReferenceService {


    public List<Reference> getAllReferences(Reference reference) throws IOException {
        Document doc = Jsoup.connect(reference.getStringUrl()).get();
        Elements references = doc.select("a[href]");
        List<Reference> referenceList = new ArrayList<>();
        UrlValidator urlValidator = UrlValidatorSingleton.getUrlValidator();
        int i = 1;
        String referenceName;
        for (Element link : references) {
            if (urlValidator.isValid(link.attr("abs:href"))) {
                referenceName = (link.ownText().equals(""))?NOT_NAME:link.ownText();
                referenceList.add
                        (new Reference(link.attr("abs:href"),
                                referenceName,
                                i));
                i++;
            }
        }
        return referenceList;
    }
}
