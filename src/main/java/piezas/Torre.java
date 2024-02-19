package piezas;

import Juego.Movimiento;
import Juego.Tablero;

public class Torre extends Pieza{

    public Torre(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override

    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return (mov.esHorizontal() || mov.esVertical());
    }

    @Override
    public String toString() {
        return getColor() ? "♖":"♜";
    }
}
