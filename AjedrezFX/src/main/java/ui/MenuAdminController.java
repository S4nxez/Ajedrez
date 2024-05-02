package ui;

import dao.PartidaDAO;
import dao.UsuarioDAO;
import domain.Usuario;
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
    private ComboBox<String> comboBox;
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
}
