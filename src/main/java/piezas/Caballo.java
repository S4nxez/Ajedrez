package piezas;

import Juego.Movimiento;

public class Caballo extends Pieza{

    public Caballo(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        if ((mov.saltoHorizontal() == 2 || mov.saltoHorizontal() == -2) && (mov.saltoVertical() == 1 || mov.saltoVertical() == -1))
            return true;
        if ((mov.saltoVertical() == 2 || mov.saltoVertical() == -2) && (mov.saltoHorizontal() == -1 || mov.saltoHorizontal() == 1))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return getColor() ? "♘":"♞";
    }
}
