package sixmax06.javafx.enigmamachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EnigmaMachineApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EnigmaMachineApplication.class.getResource("EnigmaMachine-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        stage.setTitle("Enigma Machine I");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}