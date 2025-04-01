package sixmax06.javafx.enigmamachine;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private ComboBox<String> cbxTypeRotor1, cbxTypeRotor2, cbxTypeRotor3;

    @FXML
    private HBox hbxLampboard1, hbxLampboard2, hbxLampboard3;

    @FXML
    private HBox hbxKeyboard1, hbxKeyboard2, hbxKeyboard3;

    private EnigmaMachine enigmaMachine;
    private String keyboardLayout;

    /**
     * Inizializzazione applicazione
     */
    public void initialize() {
        this.enigmaMachine = new EnigmaMachine(1, 1, 1);

        //Crea combobox del primo rotore e lo seleziona
        this.cbxTypeRotor1.getItems().addAll("I", "II", "III");
        this.cbxTypeRotor1.getSelectionModel().selectFirst();

        //Crea combobox del secondo rotore e lo seleziona
        this.cbxTypeRotor2.getItems().addAll("I", "II", "III");
        this.cbxTypeRotor2.getSelectionModel().selectFirst();

        //Crea combobox del terzo rotore e lo seleziona
        this.cbxTypeRotor3.getItems().addAll("I", "II", "III");
        this.cbxTypeRotor3.getSelectionModel().selectFirst();

        //Legge da file la configurazione dei rotori
        FileReader fr;
        try {
            fr = new FileReader("keyboard_layout.csv");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 3; i++)
                sb.append(br.readLine());

            this.keyboardLayout = sb.toString();

            //Configura la tastiera
            for (int i = 0; i < 26; i++) {
                Button keyButton = new Button(String.valueOf(keyboardLayout.charAt(i)));
                keyButton.setPrefSize(46, 46);
                keyButton.setFont(new Font("System", 20));

                //Associa l'evento quando si preme il tasto
                keyButton.setOnAction(event -> this.EncryptCharacter(keyButton.getText().charAt(0)));

                //Crea la lampada
                Text lampButton = createText(String.valueOf(keyboardLayout.charAt(i)));
                Circle lampCircle = createOffLamp();
                StackPane lamp = new StackPane();
                lamp.getChildren().addAll(lampCircle, lampButton);

                //Sistema il tutto graficamente
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

            fr.close();
            br.close();

            //Eccezioni
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. File not found : " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("ERROR. I/O Error : " + e.getMessage());
            System.exit(2);
        }
    }

    /**
     * Crea un Text a partire da una stringa
     * @param string Stringa da convertire
     * @return Text (Label immodificabile)
     */
    private Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"System\";" +
                        "-fx-font-size: 20px;"
        );

        return text;
    }

    /**
     * Crea un cerchio grigio (utilizzato per le lampade spente)
     * @return Circle
     */
    private Circle createOffLamp() {
        Circle circle = new Circle();

        circle.setFill(Color.LIGHTGREY);
        circle.setRadius(23);
        circle.setStroke(Color.BLACK);

        return circle;
    }

    /**
     * Crea un cerchio giallo (utilizzato per le lampade accese)
     * @return Circle
     */
    private Circle createOnLamp() {
        Circle circle = new Circle();

        circle.setFill(Color.YELLOW);
        circle.setRadius(23);
        circle.setStroke(Color.BLACK);

        return circle;
    }

    /**
     * Crittografa una lettera utilizzando la macchina enigma
     * @param character Lettera da crittografare
     */
    private void EncryptCharacter(char character) {
        this.checkRotorsType();
        this.turnOffAllLamps();

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

        this.UpdateRotorsRotations();
    }

    /**
     * Aggiorna label in base alla rotazione dei rotori
     */
    private void UpdateRotorsRotations() {
        int[] rotations = enigmaMachine.getRotorsRotations();

        this.lblPositionRotor1.setText(String.valueOf((char) (rotations[0] + 'A')));
        this.lblPositionRotor2.setText(String.valueOf((char) (rotations[1] + 'A')));
        this.lblPositionRotor3.setText(String.valueOf((char) (rotations[2] + 'A')));
    }

    /**
     * Spegne tutte le lampade
     */
    private void turnOffAllLamps() {
        this.hbxLampboard1.getChildren().clear();
        this.hbxLampboard2.getChildren().clear();
        this.hbxLampboard3.getChildren().clear();

        for (int i = 0; i < 26; i++) {
            Text lampButton = createText(String.valueOf(this.keyboardLayout.charAt(i)));
            Circle lampCircle = createOffLamp();
            StackPane lamp = new StackPane();
            lamp.getChildren().addAll(lampCircle, lampButton);

            if (i <= 8) this.hbxLampboard1.getChildren().add(lamp);
            else if (i <= 16) this.hbxLampboard2.getChildren().add(lamp);
            else this.hbxLampboard3.getChildren().add(lamp);
        }
    }

    /**
     * Cambia il tipo del rotore in base alla scelta dell'utente nei combobox
     */
    private void checkRotorsType() {
        int[] rotorsTypes = enigmaMachine.getRotorsType();

        if (rotorsTypes[0] != cbxTypeRotor1.getValue().length())
            enigmaMachine.changeRotorType(1, cbxTypeRotor1.getValue().length());

        if (rotorsTypes[1] != cbxTypeRotor2.getValue().length())
            enigmaMachine.changeRotorType(2, cbxTypeRotor2.getValue().length());

        if (rotorsTypes[2] != cbxTypeRotor3.getValue().length())
            enigmaMachine.changeRotorType(3, cbxTypeRotor3.getValue().length());
    }

    @FXML
    public void IncreaseRotor1OnClick() {
        this.enigmaMachine.rotateRotors(1, 1);
        this.UpdateRotorsRotations();
    }

    @FXML
    public void DecreaseRotor1OnClick() {
        this.enigmaMachine.rotateRotors(-1, 1);
        this.UpdateRotorsRotations();
    }

    @FXML
    public void IncreaseRotor2OnClick() {
        this.enigmaMachine.rotateRotors(1, 2);
        this.UpdateRotorsRotations();
    }

    @FXML
    public void DecreaseRotor2OnClick() {
        this.enigmaMachine.rotateRotors(-1, 2);
        this.UpdateRotorsRotations();
    }

    @FXML
    public void IncreaseRotor3OnClick() {
        this.enigmaMachine.rotateRotors(1, 3);
        this.UpdateRotorsRotations();
    }

    @FXML
    public void DecreaseRotor3OnClick() {
        this.enigmaMachine.rotateRotors(-1, 3);
        this.UpdateRotorsRotations();
    }
}