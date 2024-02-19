package piezas;

import Juego.Movimiento;
import Juego.Tablero;

public class Rey extends Pieza{
    public Rey(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return ((mov.esVertical() || mov.esHorizontal() || mov.esDiagonal()) &&
                (Math.abs(mov.saltoHorizontal() + mov.saltoVertical()) == 1 ||
                        mov.esDiagonal() && Math.abs(mov.saltoVertical()) == 1));
    }

    @Override
    public String toString() {
        return getColor() ? "♔":"♚";
    }
}
