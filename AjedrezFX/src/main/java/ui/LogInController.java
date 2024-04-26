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

    @FXML
    private Label signUpLabelError;
    @FXML
    private Label labelErrorRepetir;
    @FXML
    private TextField signUpUsernameField;
    @FXML
    private PasswordField signUpPwdField;
    @FXML
    private PasswordField pwdFieldRepeat;


    private final IJuegoService service;
    @Setter
    private Stage stage;

    public LogInController() {
        this.service = new JuegoService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void logInClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/menuInicial.fxml"));
        Scene scene;
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
    public void crearClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/crearUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        LogInController controller = fxmlLoader.getController(); //esto no lo entiendo
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUpcrearClicked(MouseEvent mouseEvent) throws IOException {
        if (signUpPwdField.getText().equals(pwdFieldRepeat.getText())){
            if (service.createUser(signUpUsernameField.getText(), signUpPwdField.getText())){
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/logIn.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                LogInController controller = fxmlLoader.getController();// no entiendo esto
                controller.setStage(stage);

                stage.setScene(scene);
                stage.show();
            }else {
                signUpLabelError.setText(Constantes.USUARIO_YA_EXISTE);
            }
        } else {
            labelErrorRepetir.setText(Constantes.CONTRASENYAS_NO_COINCIDEN);
        }
    }
}
