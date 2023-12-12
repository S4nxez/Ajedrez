package piezas;

public class Peon extends Pieza{

    public Peon(String color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        return false;
    }

    @Override
    public void pintarPieza() {
        System.out.println(toString());
    }


    @Override
    public String toString() {
        return "0".equals(getColor()) ? "♙":"♟";
    }

}
