package de.se.trechner.model;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LangModel {
    private static ResourceBundle bundle;
    private String baseName = "resources.langBundle";

    private static LangModel ourInstance = new LangModel();

    public static LangModel getInstance() {
        return ourInstance;
    }

    private LangModel() {
        try {
            bundle = ResourceBundle.getBundle( baseName );
        } catch ( MissingResourceException e ) {
            System.err.println( e );
        }
    }

    public Locale getCurrLocale() {
        return bundle.getLocale();
    }

    public String getKeyCaption(String key) {
        return bundle.getString(key);
    }

    public String getAccessibleText(String key) {
        return bundle.getString(key + ".AccessibleText");
    }



}
