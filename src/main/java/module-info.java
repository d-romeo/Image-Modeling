module com.example.imagemodeling{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.imagemodeling to javafx.fxml,javafx.graphics;
    exports com.example.imagemodeling to javafx.fxml,javafx.graphics;
}