package piezas;

public abstract class Pieza {
    private String color;
    private String nombre;

    public Pieza(String color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean validoMovimiento(Movimiento mov);

    public abstract void pintarPieza();

    @Override
    public String toString() {
        return nombre;
    }
}