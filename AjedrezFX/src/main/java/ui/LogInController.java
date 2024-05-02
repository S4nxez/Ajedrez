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
import service.IJuegoService;
import common.*;
import service.JuegoService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/menuAdmin.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                MenuAdminController controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/menuInicialUser.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                MenuInicialController controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            labelError.setText(Constantes.LOGIN_FAIL);
        }
    }

    @FXML
    public void crearClicked(MouseEvent mouseEvent) {
        logInPane.setVisible(false);
        crearCuentaPane.setVisible(true);
    }

    @FXML
    public void signUpcrearClicked(MouseEvent mouseEvent){
            logInPane.setVisible(true);
            crearCuentaPane.setVisible(false);
    }

    public void signUpLogIn(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        LogInController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
}
