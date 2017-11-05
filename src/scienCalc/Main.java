package scienCalc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Locale loc = new Locale("en" ,"EN");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.MyBundels"));
        Parent root = fxmlLoader.load(getClass().getResource("TestScene.fxml").openStream());
        primaryStage.setTitle("Hello World");
        Scene myScene = new Scene(root, 300, 275);
        myScene.setOnKeyPressed(
                (KeyEvent keyEvent) -> {
                        System.out.println("Es wurde folgende Taste gedrückt:\t" + keyEvent.getCode() + keyEvent.getText());
                        if (keyEvent.getCode() == KeyCode.MINUS ) {
                            System.out.println("plus1");
                        }
                    }
        );



        //primaryStage.setScene(myScene);

        SecondScene secondScene = new SecondScene();
        Scene mySecondScene = new Scene(secondScene.asParent(), 300, 500);

        mySecondScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent keyEvent)
                    {
                        System.out.println("Es wurde folgende Taste gedrückt:\t" + keyEvent.getCode() + keyEvent.getText());
                        if (keyEvent.getCode() == KeyCode.MINUS ) {
                            System.out.println("plus1");
                        }
                    }
                }
        );

        primaryStage.setScene(mySecondScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
