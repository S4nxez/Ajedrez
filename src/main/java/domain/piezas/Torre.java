package domain.piezas;

import domain.Movimiento;
import domain.Tablero;
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
        return getColor() ? "File:src/main/resources/imagenes/TorreBlanca.png":"File:src/main/resources/imagenes/TorreNegra.png";
    }
}
