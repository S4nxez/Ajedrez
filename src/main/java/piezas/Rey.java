package piezas;

import Juego.Movimiento;

public class Rey extends Pieza{
    public Rey(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }

    @Override
    public String toString() {
        return getColor() ? "♔":"♚";
    }

}
