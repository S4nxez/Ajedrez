package piezas;

import Juego.Movimiento;
import Juego.Tablero;

public class Torre extends Pieza{
    private boolean movido;
    public Torre(boolean color, String nombre) {
        super(color, nombre);
        this.movido = false;
    }

    @Override

    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return (mov.esHorizontal() || mov.esVertical());
    }
    boolean setMovido (boolean movido){
        return this.movido = movido;
    }
    @Override
    public String toString() {
        return getColor() ? "♖":"♜";
    }
}
