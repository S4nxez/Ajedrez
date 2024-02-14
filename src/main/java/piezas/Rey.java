package piezas;

import Juego.Movimiento;

public class Rey extends Pieza{
    public Rey(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return ((mov.esVertical() || mov.esHorizontal() || mov.esDiagonal()) &&
                (Math.abs(mov.saltoHorizontal()) == 1 || Math.abs(mov.saltoVertical()) == 1));
    }//darle un repaso

    @Override
    public String toString() {
        return getColor() ? "♔":"♚";
    }
}
