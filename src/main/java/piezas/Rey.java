package piezas;

import Juego.Movimiento;
import Juego.Tablero;

public class Rey extends Pieza{
    boolean movido;
    public Rey(boolean color, String nombre) {
        super(color, nombre);
        this.movido = false;
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        boolean ret = false;

        if (!movido && mov.esHorizontal() && Math.abs(mov.saltoHorizontal()) == 2 && validoEnroque(mov, tab)){
            ret = true;
            this.movido = true;
        }

        if ((mov.esVertical() || mov.esHorizontal() || mov.esDiagonal()) && (Math.abs(mov.saltoVertical()) == 1) ||
                (Math.abs(mov.saltoHorizontal()) == 1)) {
            ret = true;
            this.movido = true;
        }
        return ret;
    }

    private boolean validoEnroque(Movimiento mov, Tablero tab) {
        int fila = mov.getPosInicial().getFila();
        int columna = mov.getPosInicial().getColumna();

        if (mov.saltoHorizontal() == 2) {
            if (getColor()) {
                return (fila == 7 && columna == 4 && !tab.hayPieza(fila, 5) && !tab.hayPieza(fila, 6)
                        && tab.hayPieza(fila, 7) && tab.getPieza(fila, 7).toString().equals("♖"));
            } else {
                return (fila == 0 && columna == 4 && !tab.hayPieza(fila, 5) && !tab.hayPieza(fila, 6)
                        && tab.hayPieza(fila, 7) && tab.getPieza(fila, 7).toString().equals("♜"));
            }
        } else {
            if (getColor()) {
                return (fila == 7 && columna == 4 && !tab.hayPieza(fila, 3) && !tab.hayPieza(fila, 2)
                        && !tab.hayPieza(fila, 1) && tab.hayPieza(fila, 0) &&
                        tab.getPieza(fila, 0).toString().equals("♖"));
            } else {
                return (fila == 0 && columna == 4 && !tab.hayPieza(fila, 3) && !tab.hayPieza(fila, 2)
                        && !tab.hayPieza(fila, 1) && tab.hayPieza(fila, 0) &&
                        tab.getPieza(fila, 0).toString().equals("♜"));
            }
        }
    }

    @Override
    public String toString() {
        return getColor() ? "♔":"♚";
    }
}