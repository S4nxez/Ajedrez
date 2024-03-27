package piezas;

import Juego.Movimiento;
import Juego.Tablero;

public class Alfil extends Pieza{
    public Alfil(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return mov.esDiagonal();
    }

    @Override
    public String toString() {
        return getColor() ? "♗":"♝";
    }
}
