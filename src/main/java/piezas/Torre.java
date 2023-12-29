package piezas;

import Juego.Movimiento;

public class Torre extends Pieza{

    public Torre(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override

    public boolean validoMovimiento(Movimiento mov) {
        return (mov.esHorizontal() || mov.esVertical());
    }

    @Override
    public String toString() {
        return getColor() ? "♖":"♜";
    }
}
