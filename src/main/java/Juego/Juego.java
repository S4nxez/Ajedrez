package Juego;

import piezas.Pieza;

public class Juego {
    private boolean elTurno = true; // 0->Negras 1->Blancas
    public boolean getTurno() {
        return elTurno;
    }
    public void setTurno(boolean nuevoTurno) {
        this.elTurno = nuevoTurno;
    } // no se yo si voy a necesitar este metodo
    public Movimiento jugada(String jugada, Tablero tablero) { //control de entrada solo
        // Implementa la lógica para procesar la jugada y actualizar el tablero
        // Devuelve un objeto de tipo piezas. Movimiento con la información de la jugada
        Posicion posIni = new Posicion((jugada.charAt(1) - 49), (jugada.charAt(0) - 98));
        Posicion posFin = new Posicion((jugada.charAt(3) - 49), (jugada.charAt(2) - 98));
        Pieza figura = tablero.getPieza(posIni);
        Movimiento movimiento = new Movimiento(posIni, posFin);
        if (figura.validoMovimiento(movimiento) && figura.getColor() == elTurno){ //cuidao que aqui parece que cuando pillas un null en la posinicial peta en ejecucion
            tablero.quitaPieza(posIni);
            tablero.ponPieza(figura, posFin);
            elTurno = !elTurno;
        }else
            movimiento = null;
        return movimiento; //if movimiento == null reviso fuera y lanzo alguna excepcion
    }
}