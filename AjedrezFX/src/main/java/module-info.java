module javafx.juego {
    requires static lombok;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    exports domain;
    opens ui to javafx.graphics, javafx.fxml;
    exports ui;
}
