package scienCalc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


public class TestSceneController {

    public final static String NEW = "neu";

    public TestSceneController() {
        System.out.println("first");
    }


    @FXML
    private Button TestBtn;
    @FXML
    private GridPane myGrid;
    @FXML
    private Button TestBtn2;

    private Button newButton;
    private EventHandler<ActionEvent> myListener = new alternativListener(1);

    @FXML
    public void initialize() {
        newButton = new Button("Hello World");
       // newButton.setOnAction(this::processNumpad);
        newButton.setId(NEW);
        // newButton.setOnAction(myListener );
        newButton.addEventHandler(ActionEvent.ACTION, myListener);
        myGrid.add(newButton, 0,0);
        TestBtn.setId(NEW);
        TestBtn.setTooltip(new Tooltip("Hello World"));
        System.out.println("second");
        // allow button to grow:
        TestBtn2.setMaxWidth(Double.MAX_VALUE);
        TestBtn2.setMaxHeight(Double.MAX_VALUE);

// ask GridPane to make button fill it's cells:
        myGrid.setFillWidth(TestBtn2, true);
        myGrid.setFillHeight(TestBtn2, true);
    }


    @FXML
    private void handleKeyPressed(KeyEvent ke){
        System.out.println("Key Pressed: " + ke.getCode());
    }

    @FXML
    private void testFkt(ActionEvent e) {
        TestBtn.setDisable(true);
        String btnCaption = ((Button)e.getSource()).getText();
        System.out.println(btnCaption);
    }

    @FXML
    private void processNumpad(ActionEvent e) {
        // Value aus dem Button holen
        String value = ((Button)e.getSource()).getId();
        System.out.println(value);
    }

    @FXML
    private void enableBtn() {
        TestBtn.setDisable(false);
    }
}
