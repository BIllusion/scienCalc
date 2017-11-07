package scienCalc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scienCalc.view.TRechnerGUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Taschenrechner-Prototyp");
        TRechnerGUI tRechnerGUI = new TRechnerGUI();
        Scene scene = new Scene(tRechnerGUI.asParent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
