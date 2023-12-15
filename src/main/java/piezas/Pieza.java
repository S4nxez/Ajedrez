package piezas;

import Juego.Movimiento;

public abstract class Pieza {
    private boolean color;
    private String nombre;

    public Pieza(boolean color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    public boolean getColor() {
        return color;
    }

    public abstract boolean validoMovimiento(Movimiento mov);

    public void pintarPieza(){
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return nombre;
    }
}