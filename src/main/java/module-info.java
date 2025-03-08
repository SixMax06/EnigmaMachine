module sixmax06.javafx.enigmamachine {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens sixmax06.javafx.enigmamachine to javafx.fxml;
    exports sixmax06.javafx.enigmamachine;
}