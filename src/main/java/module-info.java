module com.example.supinmiton {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires javafx.swing;


    opens com.supinfo.recipe to javafx.fxml;
    exports com.supinfo.recipe;
    exports com.supinfo.recipe.recipe;
    opens com.supinfo.recipe.recipe to javafx.fxml;
    exports com.supinfo.recipe.common;
    opens com.supinfo.recipe.common to javafx.fxml;
}