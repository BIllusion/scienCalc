package de.se.trechner.view;

import de.se.trechner.Main;
import de.se.trechner.model.CSSNodeIDs;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;

/**
 * Repräsentiert das Hilfe-Fenster und zeigt die Hilfe.html an
 *
 * @author ruess_c
 * @version 2017-12-16
 */
public class HelpWindow {
    private static Stage stage = new Stage();
    private static HelpWindow ownInstance = new HelpWindow();
    private URL url = Main.class.getResource("/resources/Help.html");

    /**
     * Aufbau des Hilfefensters und laden der HTML-Datei (Singelton)
     *
     */
    private HelpWindow() {
        // Setup Base
        stage.setTitle("Hilfe");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/icon.png")));
        stage.hide();
        AnchorPane baseAnchor = new AnchorPane();
        baseAnchor.setId(CSSNodeIDs.HELPWINDOW);

        // Load HTML
        WebView wv = new WebView();
        WebEngine we = wv.getEngine();

        wv.setContextMenuEnabled(true);
        we.load(url.toString());

        // Layout
        baseAnchor.setTopAnchor(wv,0.0);
        baseAnchor.setLeftAnchor(wv,0.0);
        baseAnchor.setRightAnchor(wv,0.0);
        baseAnchor.setBottomAnchor(wv,0.0);
        baseAnchor.getChildren().addAll(wv);

        Scene sc = new Scene(baseAnchor);

        sc.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    stage.close();
                    break;
            }
        });
        stage.setScene(sc);
    }

    /**
     * Macht das Hilfefenster sichtbar und hebt es in den Vordergrund
     *
     */
    public static void open() {
        stage.show();
        stage.requestFocus();
    }

    /**
     * Speichert die Help.html temporär auf das Laufwerk und öffnet diese mit dem Standart Browser
     *
     */
    public static void openHelpInBrowser() {
        try {
            InputStream fileStream = Main.class.getResourceAsStream("/resources/Help.html");

            File tempFile = File.createTempFile("Help", ".html");
            tempFile.deleteOnExit();

            OutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int len = fileStream.read(buffer);
            while (len != -1) {
                out.write(buffer, 0, len);
                len = fileStream.read(buffer);
            }

            // Close the streams
            fileStream.close();
            out.close();

            // Open Temp-File in Browser
            Desktop.getDesktop().browse(tempFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
