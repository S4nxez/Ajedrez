package piezas;

public class Alfil extends Pieza{
    public Alfil(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }


    @Override
    public String toString() {
        return getColor() ? "♗":"♝";
    }

}
