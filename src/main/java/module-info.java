module com.example.supinmiton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.supinmiton to javafx.fxml;
    exports com.example.supinmiton;
}