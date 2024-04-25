package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import lombok.Setter;
import service.IJuegoService;
import common.*;
import service.JuegoService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Label labelError;

    private final IJuegoService service;
    @Setter
    private Stage stage;

    public LogInController() {
        this.service = new JuegoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void logInClicked(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/menuInicial.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MenuInicialController controller = fxmlLoader.getController();
        if (service.logIn(usernameField.getText(), pwdField.getText())){
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } else {
            labelError.setText(Constantes.LOGIN_FAIL);
        }
    }

    @FXML
    public void crearClicked(MouseEvent mouseEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/crearUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CrearUsuarioController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }
}
