package by.nahorny.referencescounter.entity;

import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;

@Data
public class Reference {

    URL url;
    String name;
    int number;


    public Reference(URL url, String name, int number) {
        this.url = url;
        this.name = name;
        this.number = number;
    }

    public Reference(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public Reference(String url, String name, int number) throws MalformedURLException {
        this.url = new URL(url);
        this.name = name;
        this.number = number;
    }

    public Reference() {
    }

    public URL getUrl() {
        return url;
    }

    public String getStringUrl() {
        return url.toString();
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }
}
