package ui;

import common.Constantes;
import dao.PartidaDAO;
import dao.UsuarioDAO;
import domain.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Setter;
import service.JuegoService;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

import java.util.stream.Collectors;


@Setter
public class MenuAdminController implements Initializable {

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
    private ComboBox<String> comboBox;
    @FXML
    private TextField nombre;
    @FXML
    private TextField contrasenya;
    @FXML
    private TextField id;
    @FXML
    private ComboBox<String> selectOrder;
    @FXML
    public CheckBox adminPrimero;

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
        selectOrder.getItems().addAll("Id", "Nombre");
        selectOrder.setValue("Id");

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
        if (id.getText().isEmpty() || nombre.getText().isEmpty() || contrasenya.getText().isEmpty() ||
                comboBox.getValue().isBlank()) {
            alertaError(Constantes.ANYADIR_USUARIO);
        }else {
            Usuario usuario = new Usuario(Integer.parseInt(id.getText()), Boolean.parseBoolean(comboBox.getValue()),
                    nombre.getText(), contrasenya.getText());
            if (viewModel.getServicio().addUsuario(usuario)) {
                tablaUsuarios.getItems().add(usuario);
                    limpiarCampos();
            } else {
                alertaError(Constantes.ANYADIR_USUARIO);
            }
        }
    }

    @FXML
    private void limpiarCampos() {
        id.clear();
        nombre.clear();
        contrasenya.clear();
        comboBox.setValue(null);
    }

    @FXML
    private void alertaError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Constantes.ERROR);
        alert.setHeaderText(Constantes.ERROR_AL + error);
        alert.setContentText(Constantes.REVISAR_CAMPOS);
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
            alertaError(Constantes.ACTUALIZAR_USUARIO);
        } else {
            Usuario user1 = new Usuario(Integer.parseInt(id.getText()), Boolean.parseBoolean(comboBox.getValue()),
                    nombre.getText(), contrasenya.getText());
            Usuario user2 = tablaUsuarios.getSelectionModel().getSelectedItem();
            if (viewModel.getServicio().updateUsuario(user1, user2)) {
                tablaUsuarios.getItems().remove(user2);
                tablaUsuarios.getItems().add(user1);
                limpiarCampos();
                tablaUsuarios.refresh();
            } else {
                alertaError(Constantes.ACTUALIZAR_USUARIO);
            }
        }
    }

    @FXML
    public void orderBy() {
        String orden = selectOrder.getValue();
        tablaUsuarios.setItems(viewModel.getServicio().getUsuarios().stream()
                .sorted((u1, u2) ->
                        orden.equals("Id") ?
                                Integer.compare(u1.getId(), u2.getId()) :
                                u1.getNombreUsuario().compareTo(u2.getNombreUsuario())
                ).sorted((u1, u2) -> adminPrimero.isSelected() ? Boolean.compare(u2.isAdmin(), u1.isAdmin()) : 0)
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList))
        );
    }
}
