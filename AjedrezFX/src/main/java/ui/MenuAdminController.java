package ui;

import dao.PartidaDAO;
import dao.UsuarioDAO;
import domain.Usuario;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;
import service.JuegoService;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

@Setter
public class MenuAdminController implements Initializable {

    private Stage stage;

    private final MainViewModel viewModel;
    @FXML
    private TableView<Usuario> tablaUsuarios;
    @FXML
    private TableColumn<Usuario, Integer> columna1;
    @FXML
    private TableColumn<Usuario, String> columna2;
    @FXML
    private TableColumn<Usuario, String> columna3;
    @FXML
    private TableColumn<Usuario, Boolean> columna4;

    @FXML
    private MFXComboBox<String> comboBox;
    @FXML
    private TextField nombre;
    @FXML
    private TextField contrasenya;
    @FXML
    private TextField id;

    public MenuAdminController() {
        viewModel = new MainViewModel(new JuegoService(new UsuarioDAO(), new PartidaDAO()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaUsuarios.setItems(viewModel.getUsuarios());

        columna1.setCellValueFactory(new PropertyValueFactory<>("id"));
        columna2.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        columna3.setCellValueFactory(new PropertyValueFactory<>("contrasenya"));
        columna4.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        comboBox.getItems().addAll("true", "false");

        tablaUsuarios.setOnMouseClicked((MouseEvent event) -> onEdit());
    }

    public void onEdit() {
        if (tablaUsuarios.getSelectionModel().getSelectedItem() != null) {
            Usuario selectedItem = tablaUsuarios.getSelectionModel().getSelectedItem();
            id.setText(String.valueOf(selectedItem.getId()));
            nombre.setText(selectedItem.getNombreUsuario());
            contrasenya.setText(String.valueOf(selectedItem.getContrasenya()));
            comboBox.setValue(String.valueOf(selectedItem.isAdmin()));
        }
    }

    @FXML
    private void addUsuario() {
        if (id.getText().isEmpty() || nombre.getText().isEmpty() || contrasenya.getText().isEmpty() || comboBox.getValue().isEmpty()) {
            alertaError("añadir usuario");
        }else {
            Usuario usuario = new Usuario(Integer.parseInt(id.getText()), Boolean.parseBoolean(comboBox.getValue()), nombre.getText(), contrasenya.getText());
            if (viewModel.getServicio().addUsuario(usuario)) {
                tablaUsuarios.getItems().add(usuario);
                    limpiarCampos();
            } else {
                alertaError("añadir usuario");
            }
        }
    }

    @FXML
    private void limpiarCampos() {
        id.clear();
        nombre.clear();
        contrasenya.clear();
        comboBox.clear();
    }

    @FXML
    private void alertaError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error al " + error);
        alert.setContentText("Revise los campos");
        alert.show();
    }

    @FXML
    public void deleteUsuario() {
        Usuario usuario = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuario != null && viewModel.getServicio().deleteUsuario(usuario)) {
            viewModel.getServicio().getUsuarios().remove(usuario);
            tablaUsuarios.getItems().remove(usuario);
        }
    }

    @FXML
    private void updateUsuario() {
        if (nombre.getText() == null || contrasenya.getText() == null || id.getText() == null || comboBox.getValue() == null) {
            alertaError("actualizar usuario");
        } else {
            Usuario user1 = new Usuario(Integer.parseInt(id.getText()), Boolean.parseBoolean(comboBox.getValue()), nombre.getText(), contrasenya.getText());
            Usuario user2 = tablaUsuarios.getSelectionModel().getSelectedItem();
            if (viewModel.getServicio().updateUsuario(user1, user2)) {
                tablaUsuarios.getItems().remove(user2);
                tablaUsuarios.getItems().add(user1);
                limpiarCampos();
                tablaUsuarios.refresh();
            } else {
                alertaError("actualizar usuario");
            }
        }
    }
}