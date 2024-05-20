package domain.piezas;

import domain.Movimiento;
import domain.Tablero;

import java.io.Serializable;

public abstract class Pieza implements Serializable {
    private final boolean color;
    private final String nombre;

    public Pieza(boolean color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    public boolean getColor() {
        return color;
    }

    public abstract boolean  validoMovimiento(Movimiento mov, Tablero tab);

    @Override
    public String toString() {
        return nombre;
    }
}