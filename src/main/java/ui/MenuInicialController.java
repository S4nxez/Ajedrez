package ui;

import dao.PartidaDAO;
import dao.UsuarioDAO;
import domain.Partida;
import domain.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;
import service.JuegoService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class MenuInicialController implements Initializable{

    @FXML
    public AnchorPane initialPane;
    @FXML
    public AnchorPane partidasPane;
    @FXML
    public TableView<Partida> tablaPartidas;
    @FXML
    public TableColumn<Partida, LocalDate> columnaFecha;
    @FXML
    public TableColumn<Partida, Integer> columnaId;

    @Setter
    private Stage stage;
    private final MainViewModel viewModel;

    public MenuInicialController() {
        this.viewModel = new MainViewModel(new JuegoService(new UsuarioDAO(), new PartidaDAO()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void jugarClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/tablero.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menuDatosClicked() {
        initialPane.setVisible(false);
        partidasPane.setVisible(true);

        tablaPartidas.setItems(viewModel.getPartidas());
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    private void volverClicked() {
        partidasPane.setVisible(false);
        initialPane.setVisible(true);
    }

    public void recuperar() throws IOException {
        if (tablaPartidas.getSelectionModel().getSelectedItem() != null) {
            int partidaId = tablaPartidas.getSelectionModel().getSelectedItem().getId();
            Partida partida = viewModel.getServicio().getPartidaById(partidaId);
            Tablero tab = partida.getTablero();

            // Cargar el nuevo FXML
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/tablero.fxml"));

            // Establecer la fábrica de controladores
            fxmlLoader.setControllerFactory(param -> new TableroController(partida, tab));
            // Cargar el nuevo FXML
            Parent root = fxmlLoader.load();

            // Mostrar la nueva escena
            Window window = tablaPartidas.getScene().getWindow();
            if (window instanceof Stage stage) {
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }
}
