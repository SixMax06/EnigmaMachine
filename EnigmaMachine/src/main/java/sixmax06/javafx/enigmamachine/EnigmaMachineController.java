package sixmax06.javafx.enigmamachine;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EnigmaMachineController {
    @FXML
    private Label lblPositionRotor1, lblPositionRotor2, lblPositionRotor3;

    @FXML
    private Button btnIncreaseRotor1, btnIncreaseRotor2, btnIncreaseRotor3;

    @FXML
    private Button btnDecreaseRotor1, btnDecreaseRotor2, btnDecreaseRotor3;

    @FXML
    private VBox vbxLampboard, vbxKeyboard;

    @FXML
    private HBox hbxLampboard1, hbxLampboard2, hbxLampboard3;

    @FXML
    private HBox hbxKeyboard1, hbxKeyboard2, hbxKeyboard3;

    private ToggleGroup tgLampboard;

    public void initialize() {
        this.tgLampboard = new ToggleGroup();

        FileReader fr = null;
        try {
            fr = new FileReader("keyboard_layout.csv");
            BufferedReader br = new BufferedReader(fr);
            String[] line = new String[3];
            final int size = 45;

            for (int i = 0; i < 3; i++)
                line[i] = br.readLine();

            for (Character c : line[0].toCharArray()) {
                Button keyButton = new Button(String.valueOf(c));
                keyButton.setPrefSize(size, size);
                keyButton.setFont(new Font("System", 18));
                keyButton.setOnAction(event -> {
                    System.out.println("Pressed key: " + keyButton.getText());
                });
                this.hbxKeyboard1.getChildren().add(keyButton);

                RadioButton lampButton = new RadioButton(String.valueOf(c));
                lampButton.setPrefSize(size, size);
                lampButton.setFont(new Font("System", 18));
                this.tgLampboard.getToggles().add(lampButton);
                this.hbxLampboard1.getChildren().add(lampButton);
            }

            for (Character c : line[1].toCharArray()) {
                Button keyButton = new Button(String.valueOf(c));
                keyButton.setPrefSize(size, size);
                keyButton.setFont(new Font("System", 18));
                hbxKeyboard2.getChildren().add(keyButton);

                RadioButton lampButton = new RadioButton(String.valueOf(c));
                lampButton.setPrefSize(size, size);
                lampButton.setFont(new Font("System", 18));
                this.tgLampboard.getToggles().add(lampButton);
                this.hbxLampboard2.getChildren().add(lampButton);
            }

            for (Character c : line[2].toCharArray()) {
                Button keyButton = new Button(String.valueOf(c));
                keyButton.setPrefSize(size, size);
                keyButton.setFont(new Font("System", 18));
                hbxKeyboard3.getChildren().add(keyButton);

                RadioButton lampButton = new RadioButton(String.valueOf(c));
                lampButton.setPrefSize(size, size);
                lampButton.setFont(new Font("System", 18));
                this.tgLampboard.getToggles().add(lampButton);
                this.hbxLampboard3.getChildren().add(lampButton);
            }

            fr.close();
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR. File not found : " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("ERROR. I/O Error : " + e.getMessage());
            System.exit(2);
        }
    }
}