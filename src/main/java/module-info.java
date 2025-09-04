module mlee671.basicfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens mlee671.basicfx to javafx.fxml;
    opens mlee671.basicfx.controllers to javafx.fxml;
    exports mlee671.basicfx;
}
