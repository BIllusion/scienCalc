package de.se.trechner.controller;

import de.se.trechner.interfaces.FrameInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class TextListener implements ChangeListener<String> {
    private static TextListener ownInstance;

    private FrameInterface fi;

    private TextListener(FrameInterface fi){
        this.fi = fi;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        System.out.println("Text changed to: " + newValue);
        if (!newValue.matches("\\d*")) {
            fi.setBigLabel(newValue.replaceAll("[^\\d]", ""));
        }
        // Check for Wrong Inputs
    }


    public static TextListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new TextListener(fi);
        }
        return ownInstance;
    }
}
