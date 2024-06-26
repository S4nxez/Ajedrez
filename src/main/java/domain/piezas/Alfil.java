package domain.piezas;

import domain.Movimiento;
import domain.Tablero;

public class Alfil extends Pieza{
    public Alfil(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        return mov.esDiagonal();
    }

    @Override
    public String toString() {
        return getColor() ? "File:src/main/resources/imagenes/AlfilBlanco.png" : "File:src/main/resources/imagenes/AlfilNegro.png";
    }
}
