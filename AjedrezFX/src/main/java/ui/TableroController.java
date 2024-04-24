package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import domain.Juego;
import domain.Movimiento;
import domain.Tablero;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController implements Initializable {
    Juego juego = new Juego();
    Tablero tablero = new Tablero();
    String[] movimiento = new String[2];
    @FXML
    private Label label;
    @FXML
    private GridPane mainGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pintarTablero();
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
                    label.setText("Entrada no valida");
                    flag = false;
                }
                else if (!juego.ejecutarJugada(mov, tablero)) {
                    label.setText("Movimiento ilegal");
                    flag = false;
                }
                else if (juego.jaque(tablero, juego.ubicarRey(tablero, !juego.getTurno()))) {
                    label.setText("Jaque");
                    if (juego.jaqueMate(tablero,juego.ubicarRey(tablero, !juego.getTurno())))
                        label.setText("Jaque Mate");
                    juego.setTurno(!juego.getTurno());
                } else
                    juego.setTurno(!juego.getTurno());
            }
            if (flag) {
                movimiento[0] = null;
                movimiento[1] = null;
            }else {
                movimiento[0] = movimiento[1];
                movimiento[1] = null;
            }
        }

        pintarTablero();
    }

    public void pintarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pane pane = new Pane();
                //observable en cada casilla que hace que cada vez que cliquee llame al metodo action
                pane.setOnMouseClicked(e ->     accion(GridPane.getRowIndex((Node) e.getSource()) + GridPane.getColumnIndex((Node) e.getSource()).toString()));
                if ((i + j) % 2 == 0) {
                    pane.setStyle("-fx-background-color: #eeeed4");
                } else {
                    pane.setStyle("-fx-background-color: #7d945c");
                }
                mainGrid.add(pane, j, i);
                if (tablero.getPieza(i, j) != null) {
                    pane.getChildren().add(new ImageView(new Image(tablero.getPieza(i, j).toString())));
                }
            }
        }
    }

}
