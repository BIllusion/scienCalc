package scienCalc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class alternativListener implements EventHandler<ActionEvent> {

    private int myInt;

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Event kam an");
        System.out.println(((Button)event.getSource()).getId());
        System.out.println(((Button)event.getSource()).getText());
        //((Button)event.getSource()).setLabel("Neuer Name von Event");
        event.consume();
    }

    public alternativListener(int i) {
        myInt = i;
    }
}
