package sixmax06.javafx.enigmamachine;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private EnigmaMachine enigmaMachine;

    public void initialize() {
        this.enigmaMachine = new EnigmaMachine(1, 1, 1);

        FileReader fr = null;
        try {
            fr = new FileReader("keyboard_layout.csv");
            BufferedReader br = new BufferedReader(fr);
            String keyboardLayout;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++)
                sb.append(br.readLine());

            keyboardLayout = sb.toString();

            for (int i = 0; i < 26; i++) {
                Button keyButton = new Button(String.valueOf(keyboardLayout.charAt(i)));
                keyButton.setPrefSize(45, 45);
                keyButton.setFont(new Font("System", 20));

                keyButton.setOnAction(event -> {
                    this.EncryptCharacter(keyButton.getText().charAt(0));
                });

                Label lampButton = new Label(String.valueOf(keyboardLayout.charAt(i)));
                lampButton.setPrefSize(50, 50);
                lampButton.setFont(new Font("System", 24));
                lampButton.alignmentProperty().set(Pos.CENTER);

                if (i <= 8) {
                    this.hbxKeyboard1.getChildren().add(keyButton);
                    this.hbxLampboard1.getChildren().add(lampButton);
                } else if (i <= 16) {
                    this.hbxKeyboard2.getChildren().add(keyButton);
                    this.hbxLampboard2.getChildren().add(lampButton);
                } else {
                    this.hbxKeyboard3.getChildren().add(keyButton);
                    this.hbxLampboard3.getChildren().add(lampButton);
                }
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

    private void EncryptCharacter(char character){
        char encryptedCharacter = this.enigmaMachine.getEncryptedCharacter(character);
        int index = encryptedCharacter - 'A';

        Label EncryptedLamp = new Label(String.valueOf(encryptedCharacter));
        EncryptedLamp.setPrefSize(45, 45);
        EncryptedLamp.setFont(new Font("System", 20));
        EncryptedLamp.alignmentProperty().set(Pos.CENTER);
        EncryptedLamp.setTextFill(Color.color(1, 0, 0));
        EncryptedLamp.setUnderline(true);

        try {
            if (index <= 9) {
                Node temp = hbxLampboard1.getChildren().get(index);
                hbxLampboard1.getChildren().remove(index);
                hbxLampboard1.getChildren().add(index, EncryptedLamp);

                Thread.sleep(3000);

                hbxLampboard1.getChildren().remove(index);
                hbxLampboard1.getChildren().add(index, temp);

            } else if (index <= 16) {
                Node temp = hbxLampboard2.getChildren().get(index);
                hbxLampboard2.getChildren().remove(index - 10);
                hbxLampboard2.getChildren().add(index, EncryptedLamp);

                Thread.sleep(3000);

                hbxLampboard2.getChildren().remove(index - 10);
                hbxLampboard2.getChildren().add(index, temp);

            } else {
                Node temp = hbxLampboard3.getChildren().get(index);
                hbxLampboard3.getChildren().remove(index - 17);
                hbxLampboard3.getChildren().add(index, EncryptedLamp);

                Thread.sleep(3000);

                hbxLampboard3.getChildren().remove(index - 17);
                hbxLampboard3.getChildren().add(index, temp);
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR. Interrupted Exception : " + e.getMessage());
            System.exit(3);
        }
    }
}