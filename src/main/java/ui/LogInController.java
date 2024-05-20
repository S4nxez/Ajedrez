package ui;

import dao.UsuarioDAO;
import domain.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import service.IJuegoService;
import common.*;
import service.JuegoService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Log4j2
public class LogInController implements Initializable {
    @FXML
    private AnchorPane logInPane;
    @FXML
    private AnchorPane crearCuentaPane;
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
        this.service = new JuegoService(new UsuarioDAO(),null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void logInClicked() throws IOException {
        Usuario user = service.logIn(usernameField.getText(), pwdField.getText());
        if (user != null) {
            if (user.isAdmin()){
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/menuAdmin.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/menuInicialUser.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                MenuInicialController controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            log.info(Constantes.LOGIN_FAIL);
            labelError.setText(Constantes.LOGIN_FAIL);
        }
    }

    @FXML
    public void crearClicked(MouseEvent mouseEvent) {
        logInPane.setVisible(false);
        crearCuentaPane.setVisible(true);
    }

    @FXML
    public void signUpcrearClicked(){
        String username = signUpUsernameField.getText();
        String password = signUpPwdField.getText();
        String repeatPassword = pwdFieldRepeat.getText();
        // Expresión regular para validar contraseña: al menos 8 caracteres, al menos una letra mayúscula, al menos una
        //letra minúscula, al menos un dígito y al menos un carácter especial
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=!])(?=\\S+$).{8,}$";
        // Expresión regular para validar nombre de usuario: entre 4 y 12 caracteres
        String usernameRegex = "^.{4,12}$";

        if (password.equals(repeatPassword)){
            if (username.matches(usernameRegex) && password.matches(passwordRegex)) {
                if (service.addUser(username, password, false)) {
                    signUpLogIn();
                } else {
                    signUpLabelError.setText(Constantes.USUARIO_YA_EXISTE);
                }
            } else {
                signUpLabelError.setText(Constantes.USUARIO_CONTRASENYA_NO_VALIDOS);
            }

        } else {
            labelErrorRepetir.setText(Constantes.CONTRASENYAS_NO_COINCIDEN);
        }

    }

    public void signUpLogIn() {
        usernameField.clear(); //Limpio todos los campos y los labels de error
        pwdField.clear();
        signUpUsernameField.clear();
        signUpPwdField.clear();
        pwdFieldRepeat.clear();
        signUpLabelError.setVisible(false);
        labelErrorRepetir.setVisible(false);
        labelError.setVisible(false);

        crearCuentaPane.setVisible(false);
        logInPane.setVisible(true);
    }
}
