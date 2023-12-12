package piezas;

public class Reina extends Pieza{
    public Reina(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }

    @Override
    public String toString() {
        return getColor() ? "♕":"♛";
    }

}
