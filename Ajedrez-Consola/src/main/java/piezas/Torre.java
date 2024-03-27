package piezas;

import Juego.Movimiento;
import Juego.Tablero;
import lombok.Getter;

@Getter
public class Torre extends Pieza{
    private boolean movido;
    public Torre(boolean color, String nombre) {
        super(color, nombre);
        this.movido = false;
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        if (mov.esHorizontal() || mov.esVertical())
        {
            movido = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getColor() ? "♖":"♜";
    }
}
