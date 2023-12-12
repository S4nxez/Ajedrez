package piezas;

public class Caballo extends Pieza{

    public Caballo(String color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }

    @Override
    public void pintarPieza() {

    }

    @Override
    public String toString() {
        return "0".equals(getColor()) ? "♘":"♞";
    }
}
