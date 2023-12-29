package piezas;

import Juego.Movimiento;

public class Reina extends Pieza{
    public Reina(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return (mov.esDiagonal() || mov.esVertical() || mov.esHorizontal());
    }

    @Override
    public String toString() {
        return getColor() ? "♕":"♛";
    }

}
