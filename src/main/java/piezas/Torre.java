package piezas;

public class Torre extends Pieza{

    public Torre(String color, String nombre) {
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
        return color ==  "" ? "♖":"♜";
    }
}
