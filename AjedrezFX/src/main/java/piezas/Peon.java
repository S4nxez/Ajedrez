package piezas;

import juego.Movimiento;
import juego.Tablero;

public class Peon extends Pieza{

    public Peon(boolean color, String nombre) {
        super(color, nombre);
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        int salto;

        salto = mov.saltoVertical();
        if (mov.esVertical()) {
            if (mov.getPosInicial().getFila() == 6 && getColor()) //Si es blancas y no ha movido aun
                return (salto == -1 || salto == -2);
            if (mov.getPosInicial().getFila() == 1 && !getColor())
                return (salto == 1 || salto == 2);
            if (getColor())
                return (salto == -1);
            else
                return (salto == 1);
        }else if (mov.esDiagonal()) {
            if (getColor())
                return (salto == -1 && tab.hayPieza(mov.getPosFinal()));
            else
                return (salto == 1 && tab.hayPieza(mov.getPosFinal()));
        }
        return (false);
    }

    @Override
    public String toString() {
        return getColor() ? "File:src/main/resources/imagenes/PeonBlanco.png":"File:src/main/resources/imagenes/PeonNegro.png";
    }

}
