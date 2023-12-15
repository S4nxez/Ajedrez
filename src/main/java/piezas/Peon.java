package piezas;

import Juego.Movimiento;

public class Peon extends Pieza{

    public Peon(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }

    @Override
    public String toString() {
        return getColor() ? "♙":"♟";
    }

}
