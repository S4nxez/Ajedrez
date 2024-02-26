package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import juego.Juego;
import juego.Movimiento;
import juego.Tablero;
import piezas.Pieza;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private GridPane mainGrid;

    String jugada;
    Juego juego = new Juego();
    Tablero tablero = new Tablero();
    String movimiento[] = new String[2];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pintarTablero();
        jugar();
    }

    private void jugar() {
/*        while(!juego.jaqueMate(tablero, juego.ubicarRey(tablero, juego.getTurno()))) {
            tablero.pintarTablero();
            jugada = "TODO";
            Movimiento mov = juego.jugada(jugada, tablero);

            if (mov == null)
                label.setText("Entrada no valida");
            else if (!juego.ejecutarJugada(mov, tablero))
                label.setText("Movimiento ilegal");
            else if (juego.jaque(tablero, juego.ubicarRey(tablero, !juego.getTurno()))) {
                label.setText("Jaque");
                juego.setTurno(!juego.getTurno());
            }else
                juego.setTurno(!juego.getTurno());
        }
        tablero.pintarTablero();
        label.setText("Jaque Mate");*/
    }

    public void accion(String coordenadas){
        //manejar la jugada, que no haga click en el vacio, que no
        //llamar a juego
        if (movimiento[0] == null){
            //metes el movimiento como si fuese el primero
            movimiento[0]=coordenadas;
        }
        else{
            if (movimiento[0] != null && movimiento[1] == null) {
                //metes el movimiento como si fuese el segundo, llamas a realizar movimiento
                movimiento[1] = coordenadas;
                Movimiento mov = juego.jugada(movimiento[0] + movimiento[1], tablero);
                if (mov == null)
                    label.setText("Entrada no valida");
                else if (!juego.ejecutarJugada(mov, tablero))
                    label.setText("Movimiento ilegal");
                else if (juego.jaque(tablero, juego.ubicarRey(tablero, !juego.getTurno()))) {
                    label.setText("Jaque");
                    juego.setTurno(!juego.getTurno());
                } else
                    juego.setTurno(!juego.getTurno());
                }
        }
    }
    public void pintarTablero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pane pane = new Pane();
                //observable en cada casilla que hace que cada vez que cliquee llame al metodo action
                pane.setOnMouseClicked(e -> accion(GridPane.getColumnIndex((Node) e.getSource()).toString()  + GridPane.getRowIndex((Node) e.getSource())));
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

    public void enroque(ActionEvent actionEvent) {

    }
}
