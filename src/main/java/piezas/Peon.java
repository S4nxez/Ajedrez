package piezas;

import Juego.Movimiento;

public class Peon extends Pieza{

    public Peon(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov) {
        int salto;

        if (!mov.esVertical())
            return false;

        salto = mov.saltoVertical();
        if (mov.getPosInicial().getFila() == 1 && getColor()) //esto quiere decir que si es blancas y no ha movido aun
            return (salto == 1 || salto == 2);
        if (mov.getPosInicial().getFila() == 6 && !getColor())
            return (salto == -1 || salto == -2);
        if (getColor())
            return salto == 1;
        else
            return salto == -1;
    }

    @Override
    public String toString() {
        return getColor() ? "♙":"♟";
    }

}
