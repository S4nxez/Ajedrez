package ui;

import common.Constantes;
import dao.PartidaDAO;
import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import domain.Partida;
import domain.Movimiento;
import domain.Tablero;
import lombok.Setter;
import service.JuegoService;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController implements Initializable {
    String[] movimiento = new String[2];
    @Setter
    Partida  juego;
    @Setter
    Tablero  tablero;
    MainViewModel servicio = new MainViewModel(new JuegoService(new UsuarioDAO(), new PartidaDAO()));
    @FXML
    private Label label;
    @FXML
    private GridPane mainGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pintarTablero();
    }

    public TableroController() {
        this.juego = new Partida();
        this.tablero = this.juego.getTablero();
    }

    public TableroController(Partida partida, Tablero tablero) {
        this.juego = partida;
        this.tablero = tablero;
    }

    public void accion(String coordenadas) {
        boolean flag = true;
        if (movimiento[0] == null) {
            label.setText("");
            //metes el movimiento como si fuese el primero
            if (tablero.getPieza(coordenadas.charAt(0) - '0' , coordenadas.charAt(1) - '0') != null)
                movimiento[0] = coordenadas;
        } else {
            //observable en cada casilla que hace que cada vez que cliquee llame al metodo action
            if (movimiento[1] == null) {
                //metes el movimiento como si fuese el segundo, llamas a realizar movimiento
                movimiento[1] = coordenadas;
                Movimiento mov = juego.jugada(movimiento[0] + movimiento[1], tablero);
                if (mov == null){
                    label.setText(Constantes.ENTRADA_INCORRECTA);
                    flag = false;
                }
                else if (!juego.ejecutarJugada(mov, tablero)) {
                    label.setText(Constantes.ENTRADA_INCORRECTA);
                    flag = false;
                }
                else if (juego.jaque(tablero, juego.ubicarRey(tablero, !juego.getTurno()))) {
                    label.setText(Constantes.JAQUE);
                    if (juego.jaqueMate(tablero,juego.ubicarRey(tablero, !juego.getTurno())))
                        label.setText(Constantes.JAQUE_MATE);
                    juego.setTurno(!juego.getTurno());
                } else
                    juego.setTurno(!juego.getTurno());
            }
            movimiento[0] = flag ? null : movimiento[1];
            movimiento[1] = null;
        }
        pintarTablero();
    }

    public void pintarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pane pane = new Pane();
                pane.setOnMouseClicked(e -> accion(GridPane.getRowIndex((Node) e.getSource()) + GridPane.getColumnIndex((Node) e.getSource()).toString()));
                if ((i + j) % 2 == 0)
                    pane.setStyle("-fx-background-color: #eeeed4");
                else
                    pane.setStyle("-fx-background-color: #7d945c");
                mainGrid.add(pane, j, i);
                if (tablero.getPieza(i, j) != null)
                    pane.getChildren().add(new ImageView(new Image(tablero.getPieza(i, j).toString())));
            }
        }
    }

    @FXML
    public void guardarPartida() {
        servicio.getServicio().guardarPartida(juego);
    }
}
