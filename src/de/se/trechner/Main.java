package de.se.trechner;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import de.se.trechner.view.TRechnerGUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Barrierefreier Taschenrechner");
        TRechnerGUI tRechnerGUI = new TRechnerGUI();
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/icon.png")));
        primaryStage.setScene(tRechnerGUI.getScene());
        primaryStage.show();
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(600);
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}
