package piezas;

import Juego.Juego;
import Juego.Movimiento;
import Juego.Tablero;
import lombok.Getter;

@Getter
public class Rey extends Pieza{
    private boolean movido;
    public Rey(boolean color, String nombre) {
        super(color, nombre);
        this.movido = false;
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        boolean ret = false;
        Juego   juego = new Juego();
        if (juego.jaque(tab, mov.getPosFinal())) {
            return false;
        }
        if ((mov.esVertical() || mov.esHorizontal() || mov.esDiagonal()) &&
                (Math.abs(mov.saltoVertical()) == 1 && mov.saltoHorizontal() == 0) ||
                (Math.abs(mov.saltoHorizontal()) == 1 && mov.saltoVertical() == 0) ||
                (Math.abs(mov.saltoHorizontal()) == 1) && mov.esDiagonal() ||
                (Math.abs(mov.saltoHorizontal()) == 2 && mov.esHorizontal() && !movido && juego.validoEnroque(mov, tab))) {
            ret = true;
            this.movido = true;
        }
        return ret;
    }

    /*private byte validoEnroque(Movimiento mov, Tablero tab) {
        int fila = mov.getPosInicial().getFila();
        int columna = mov.getPosInicial().getColumna();

        if (mov.saltoHorizontal() == 2) {
            if (getColor() && (fila == 7 && columna == 4 && !tab.hayPieza(fila, 5) && !tab.hayPieza(fila, 6)
                    && tab.hayPieza(fila, 7) && tab.getPieza(fila, 7).toString().equals("♖"))) {
                return 1;
            } else if (!getColor() && (fila == 0 && columna == 4 && !tab.hayPieza(fila, 5) && !tab.hayPieza(fila, 6)
                    && tab.hayPieza(fila, 7) && tab.getPieza(fila, 7).toString().equals("♜"))) {
                return 1;
            }
        } else {
            if (getColor() && (fila == 7 && columna == 4 && !tab.hayPieza(fila, 3) && !tab.hayPieza(fila, 2)
                    && !tab.hayPieza(fila, 1) && tab.hayPieza(fila, 0) &&
                    tab.getPieza(fila, 0).toString().equals("♖"))) {
                return 2;
            } else if (!getColor() && (fila == 0 && columna == 4 && !tab.hayPieza(fila, 3) && !tab.hayPieza(fila, 2)
                    && !tab.hayPieza(fila, 1) && tab.hayPieza(fila, 0) &&
                    tab.getPieza(fila, 0).toString().equals("♜"))) {
                return 2;
            }
        }
        return -1;
    }*/

    @Override
    public String toString() {
        return getColor() ? "♔":"♚";
    }
}