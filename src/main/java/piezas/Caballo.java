package piezas;

import juego.Movimiento;
import juego.Tablero;

public class Caballo extends Pieza{
    public Caballo(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return (Math.abs(mov.saltoHorizontal()) == 2 && Math.abs(mov.saltoVertical()) == 1) ||
                (Math.abs(mov.saltoHorizontal()) == 1 && Math.abs(mov.saltoVertical()) == 2);
    }

    @Override
    public String toString() {
        return getColor() ? "♘":"♞";
    }
}
