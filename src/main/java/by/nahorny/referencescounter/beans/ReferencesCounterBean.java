package by.nahorny.referencescounter.beans;

import by.nahorny.referencescounter.entity.Reference;
import by.nahorny.referencescounter.service.ReferenceService;
import by.nahorny.referencescounter.service.ReferenceServiceSingleton;
import by.nahorny.referencescounter.service.UrlValidatorSingleton;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import static by.nahorny.referencescounter.service.strings.Message.*;

@ManagedBean
@ViewScoped
@Data
public class ReferencesCounterBean {

    private String path;
    private List<Reference> referencesList = new ArrayList<>();


    public List<Reference> workWithReference() throws MalformedURLException, IOException {
        try {
            UrlValidator urlValidator = UrlValidatorSingleton.getUrlValidator();
            if (urlValidator.isValid(path)) {
                referencesList = ReferenceServiceSingleton.getReferenceService()
                        .getAllReferences(new Reference(path));
            } else {
                referencesList.clear();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, STRING_NOT_URL, STRING_NOT_URL));
            }
        } catch (UnknownHostException ignored) {
            referencesList.clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, UNKNOWHOSTEXCEPTION, UNKNOWHOSTEXCEPTION));
        }
        return referencesList;
    }

    public void getOneReference() {
        path = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("oneReferenceHidden");

    }

    public  void clearTable() {
        if (referencesList!= null)
            referencesList.clear();
        path = "";
    }
}
