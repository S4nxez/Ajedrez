package piezas;

import Juego.Movimiento;

public class Alfil extends Pieza{
    public Alfil(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return mov.esDiagonal();
    }

    @Override
    public String toString() {
        return getColor() ? "♗":"♝";
    }
}
