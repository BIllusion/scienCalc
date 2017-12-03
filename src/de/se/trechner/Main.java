package de.se.trechner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import de.se.trechner.view.TRechnerGUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Taschenrechner-Prototyp");
        TRechnerGUI tRechnerGUI = new TRechnerGUI();
       // Scene scene = new Scene(tRechnerGUI.asParent());
        Scene scene = tRechnerGUI.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
