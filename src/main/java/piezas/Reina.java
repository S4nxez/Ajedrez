package piezas;

import juego.Movimiento;
import juego.Tablero;

public class Reina extends Pieza{
    public Reina(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return (mov.esDiagonal() || mov.esVertical() || mov.esHorizontal());
    }

    @Override
    public String toString() {
        return getColor() ? "♕":"♛";
    }

}
