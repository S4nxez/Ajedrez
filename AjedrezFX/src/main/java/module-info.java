module javafx.juego {
    requires static lombok;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.logging;
    requires MaterialFX;
    exports domain;
    opens ui to javafx.graphics, javafx.fxml;
    exports ui;
}
