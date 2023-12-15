package piezas;

import Juego.Movimiento;

public class Caballo extends Pieza{

    public Caballo(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }


    @Override
    public String toString() {
        return getColor() ? "♘":"♞";
    }
}
