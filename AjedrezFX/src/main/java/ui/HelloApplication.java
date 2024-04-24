package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menuInicial.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            // Obtener el controlador y establecer el Stage
            MenuInicialController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(scene);
            stage.setTitle("Daniel's Chess");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}