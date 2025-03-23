package sixmax06.javafx.enigmamachine;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EnigmaMachineController {
    @FXML
    private Label lblPositionRotor1, lblPositionRotor2, lblPositionRotor3;

    @FXML
    private Button btnIncreaseRotor1, btnIncreaseRotor2, btnIncreaseRotor3;

    @FXML
    private Button btnDecreaseRotor1, btnDecreaseRotor2, btnDecreaseRotor3;

    public void initialize() {
    }
}