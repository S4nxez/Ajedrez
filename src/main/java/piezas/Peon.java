package piezas;

import Juego.Movimiento;

public class Peon extends Pieza{

    public Peon(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        if (!mov.esVertical())
            return false;
        if (mov.getPosInicial().getFila() == 1 && getColor()) //esto quiere decir que si es blancas y no ha movido aun
            return (mov.saltoVertical() == 1 || mov.saltoVertical() == 2);
        if (mov.getPosInicial().getFila() == 7 && !getColor())
            return (mov.saltoVertical() == -1 || mov.saltoVertical() == -2);
        if (getColor())
            return mov.saltoVertical() == 1;
        else
            return mov.saltoVertical() == -1;
    }

    @Override
    public String toString() {
        return getColor() ? "♙":"♟";
    }

}
