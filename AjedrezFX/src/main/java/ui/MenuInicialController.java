package ui;

import dao.PartidaDAO;
import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;
import service.JuegoService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuInicialController implements Initializable{

    @FXML
    public AnchorPane initialPane;
    @FXML
    public AnchorPane partidasPane;
    @FXML
    public TableView tablaPartidas;
    @FXML
    public TableColumn columnaFecha;
    @FXML
    public TableColumn columnaId;

    @Setter
    private Stage stage;
    private final MainViewModel viewModel;

    public MenuInicialController() {
        viewModel = new MainViewModel(new JuegoService(new UsuarioDAO(), new PartidaDAO()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void jugarClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/tablero.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menuDatosClicked(MouseEvent event) {
        initialPane.setVisible(false);
        partidasPane.setVisible(true);

        tablaPartidas.setItems(viewModel.getPartidas());
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    private void volverClicked(MouseEvent event) {
        partidasPane.setVisible(false);
        initialPane.setVisible(true);
    }
}
