package by.nahorny.referencescounter.beans;

import by.nahorny.referencescounter.entity.Reference;
import by.nahorny.referencescounter.service.ReferenceService;
import by.nahorny.referencescounter.service.UrlValidatorSingleton;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class ReferencesCounterBean {

    String path;
    String path1;
    List<Reference> referencesList;

    public List<Reference> workWithReference() throws MalformedURLException, IOException {

        System.out.println(path);
        UrlValidator urlValidator = UrlValidatorSingleton.getUrlValidator();
        if (urlValidator.isValid(path)) {
            referencesList = new ReferenceService().getAllReferences(new Reference(path));
        } else {
            System.out.println("Введенная строка не является адресом");
            path = "";
        }
        System.out.println("лист ссылок: " + referencesList);
        System.out.println("Метод workWithReference закончил выполняться");
        return referencesList;
    }

    public void getOneReference() {
        System.out.println("метод getOneReference начал выполняться");
        path = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("oneReferenceHidden");
        System.out.println(path);
        System.out.println("метод getOneReference закончил выполняться");

    }

    public String clearTable() {
        System.out.println("метод clearTable выполняется");
        if (referencesList!=null)
        referencesList.clear();
        path = null;
        return "referenceCounter.xhtml";
    }
}
