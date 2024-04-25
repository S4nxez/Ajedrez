package ui;

import common.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;
import service.IJuegoService;
import service.JuegoService;

import java.io.IOException;

public class CrearUsuarioController {

    @FXML
    private Label labelError;
    @FXML
    private Label labelErrorRepetir;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField pwdField;
    @FXML
    private PasswordField pwdFieldRepeat;
    @Setter
    private Stage stage;
    private final IJuegoService service;

    public CrearUsuarioController() {
        this.service = new JuegoService();
    }

    @FXML
    public void crearClicked(MouseEvent mouseEvent) {
        if (pwdField.getText().equals(pwdFieldRepeat.getText())){
            if (service.createUser(usernameField.getText(), pwdField.getText())){
            try {
                logInClicked();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            }else {
                labelError.setText(Constantes.USUARIO_YA_EXISTE);
            }
        } else {
            labelErrorRepetir.setText(Constantes.CONTRASENYAS_NO_COINCIDEN);
        }
    }

    @FXML
    public void logInClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LogInController controller = fxmlLoader.getController();

        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

}
