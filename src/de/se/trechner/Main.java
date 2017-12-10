package de.se.trechner;

import javafx.application.Application;
import javafx.stage.Stage;
import de.se.trechner.view.TRechnerGUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Taschenrechner-Prototyp");
        TRechnerGUI tRechnerGUI = new TRechnerGUI();
        primaryStage.setScene(tRechnerGUI.getScene());
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(600);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
