package piezas;

public class Torre extends Pieza{

    public Torre(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }

    @Override
    public String toString() {
        return getColor() ? "♖":"♜";
    }
}
