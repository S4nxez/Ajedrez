module javafx.juego {
    requires static lombok;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.logging;
    requires MaterialFX;
    requires org.apache.logging.log4j;
    requires com.google.gson;
    opens domain to com.google.gson;
    exports domain;
    opens ui to javafx.graphics, javafx.fxml;
    exports ui;
}
