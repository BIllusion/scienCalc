package de.se.trechner.model;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Verwaltet das Language Bundle und hält die Texte für den Screen-Reader, sowie Buttonbeschriftungen vor
 *
 * @author ruess_c
 * @version 2017-12-16
 */
public class LangModel {
    private static ResourceBundle bundle;
    private String baseName = "resources.langBundle";

    private static LangModel ourInstance = new LangModel();

    public static LangModel getInstance() {
        return ourInstance;
    }

    /**
     * Lädt das Bundel
     */
    private LangModel() {
        try {
            bundle = ResourceBundle.getBundle( baseName );
        } catch ( MissingResourceException e ) {
            System.err.println( e );
        }
    }

    /**
     * Gibt die Sprache des geladenen Bundels an
     *
     * @return die Sprache des geladenen Bundels
     */
    public Locale getCurrLocale() {
        return bundle.getLocale();
    }

    /**
     * Gibt die Buttonbeschriftung zurück
     *
     * @param key Identifikationsschlüssel. Ist identisch zu GridActions und ToolbarActions
     * @return Beschriftung
     * @see de.se.trechner.model.GridActions
     * @see de.se.trechner.model.ToolbarActions
     */
    public String getKeyCaption(String key) {
        return bundle.getString(key);
    }

    /**
     * Gibt den Screen-Reader Alternativtext zurück
     *
     * @param key Identifikationsschlüssel. Ist identisch zu GridActions und ToolbarActions
     * @return Alternativtext für Screenreader
     * @see de.se.trechner.model.GridActions
     * @see de.se.trechner.model.ToolbarActions
     */
    public String getAccessibleText(String key) {
        return bundle.getString(key + ".AccessibleText");
    }

    /**
     * Gibt eine Erweiterte Beschreibung für den Screenreader
     *
     * @param key Identifikationsschlüssel. Ist identisch zu GridActions und ToolbarActions
     * @return Erweiterte Beschreibung für Screenreader
     * @see de.se.trechner.model.GridActions
     * @see de.se.trechner.model.ToolbarActions
     */
    public String getAccessibleHelp(String key) {
        return bundle.getString(key + ".AccessibleHelp");
    }


}
