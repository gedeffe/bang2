module com.example.supinmiton {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens com.supinfo.recipe to javafx.fxml;
    exports com.supinfo.recipe;
}