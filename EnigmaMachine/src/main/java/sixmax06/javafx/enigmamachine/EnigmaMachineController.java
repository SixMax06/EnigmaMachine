package sixmax06.javafx.enigmamachine;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

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

    private EnigmaMachine enigmaMachine;
    private String keyboardLayout;

    public void initialize() {
        this.enigmaMachine = new EnigmaMachine(2, 1, 1);

        FileReader fr = null;
        try {
            fr = new FileReader("keyboard_layout.csv");
            BufferedReader br = new BufferedReader(fr);
            this.keyboardLayout = "";

            for (int i = 0; i < 3; i++)
                this.keyboardLayout += br.readLine();

            for (int i = 0; i < 26; i++) {
                Button keyButton = new Button(String.valueOf(keyboardLayout.charAt(i)));
                keyButton.setPrefSize(45, 45);
                keyButton.setFont(new Font("System", 20));

                keyButton.setOnAction(event -> {
                    this.turnOffAllLamps();
                    this.EncryptCharacter(keyButton.getText().charAt(0));
                });

                Text lampButton = createText(String.valueOf(keyboardLayout.charAt(i)));
                Circle lampCircle = createOffLamp();
                StackPane lamp = new StackPane();
                lamp.getChildren().addAll(lampCircle, lampButton);

                if (i <= 8) {
                    this.hbxKeyboard1.getChildren().add(keyButton);
                    this.hbxLampboard1.getChildren().add(lamp);

                } else if (i <= 16) {
                    this.hbxKeyboard2.getChildren().add(keyButton);
                    this.hbxLampboard2.getChildren().add(lamp);

                } else {
                    this.hbxKeyboard3.getChildren().add(keyButton);
                    this.hbxLampboard3.getChildren().add(lamp);
                }
            }

            System.out.println(hbxKeyboard1.getChildren());

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

    private Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"System\";" +
                        "-fx-font-size: 20px;"
        );

        return text;
    }

    private Circle createOffLamp() {
        Circle circle = new Circle();
        circle.setFill(Color.LIGHTGREY);
        circle.setRadius(22);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private Circle createOnLamp() {
        Circle circle = new Circle();
        circle.setFill(Color.YELLOW);
        circle.setRadius(22);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private void EncryptCharacter(char character) {
        char encryptedCharacter = this.enigmaMachine.getEncryptedCharacter(character);
        int index = this.keyboardLayout.indexOf(encryptedCharacter);

        Text lampButton = createText(String.valueOf(encryptedCharacter));
        Circle lampCircle = createOnLamp();
        StackPane EncryptedLamp = new StackPane();
        EncryptedLamp.getChildren().addAll(lampCircle, lampButton);

        if (index <= 8) {
            hbxLampboard1.getChildren().remove(index);
            hbxLampboard1.getChildren().add(index, EncryptedLamp);

        } else if (index <= 16) {
            hbxLampboard2.getChildren().remove(index - 9);
            hbxLampboard2.getChildren().add(index - 9, EncryptedLamp);

        } else {
            hbxLampboard3.getChildren().remove(index - 17);
            hbxLampboard3.getChildren().add(index - 17, EncryptedLamp);
        }

    }

    private void turnOffAllLamps() {
        this.hbxLampboard1.getChildren().clear();
        this.hbxLampboard2.getChildren().clear();
        this.hbxLampboard3.getChildren().clear();

        for (int i = 0; i < 26; i++) {
            Text lampButton = createText(String.valueOf(this.keyboardLayout.charAt(i)));
            Circle lampCircle = createOffLamp();
            StackPane lamp = new StackPane();
            lamp.getChildren().addAll(lampCircle, lampButton);

            if (i <= 8) {
                this.hbxLampboard1.getChildren().add(lamp);

            } else if (i <= 16) {
                this.hbxLampboard2.getChildren().add(lamp);

            } else {
                this.hbxLampboard3.getChildren().add(lamp);
            }
        }
    }
}