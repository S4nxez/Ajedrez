package piezas;

import Juego.Juego;
import Juego.Movimiento;
import Juego.Tablero;
import lombok.Getter;

@Getter
public class Rey extends Pieza{
    private boolean movido;
    public Rey(boolean color, String nombre) {
        super(color, nombre);
        this.movido = false;
    }

    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tab) {
        Juego   juego = new Juego();
        boolean ret = false;
        int     saltoVertical = Math.abs(mov.saltoVertical()),
                saltoHorizontal = Math.abs(mov.saltoHorizontal());

        if ((mov.esVertical() || mov.esHorizontal() || mov.esDiagonal()) && (saltoVertical == 1 && saltoHorizontal == 0)
                || (saltoHorizontal == 1 && saltoVertical == 0) || (saltoHorizontal == 1 && mov.esDiagonal())
                || (saltoHorizontal == 2 && mov.esHorizontal() && !movido && juego.validoEnroque(mov, tab)
                    && juego.ejecutoEnroque(tab,mov) )) {
            ret = true;
            this.movido = true;
        }
        return ret;
    }

    @Override
    public String toString() {
        return getColor() ? "♔":"♚";
    }
}