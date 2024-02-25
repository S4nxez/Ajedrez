package Juego;

import piezas.Pieza;
import piezas.Rey;
import piezas.Torre;


public class Juego {
    private boolean elTurno = true; // 0->Negras 1->Blancas
    public boolean getTurno() {
        return elTurno;
    }

    public void setTurno(boolean nuevoTurno) {
        this.elTurno = nuevoTurno;
    }

    public Movimiento jugada(String jugada, Tablero tablero) { //creo que se puede hacer más limpio creando una excepción
        if (jugada == null || jugada.length() != 4)
            return null;
        if (!posValida(jugada.charAt(0) - 'a', jugada.charAt(1) - '1') ||
                !posValida(jugada.charAt(2) - 'a', jugada.charAt(3) - '1'))
            return null;
        Posicion psIni = new Posicion((7 - (jugada.charAt(1) - '1')), (jugada.charAt(0) - 'a'));
        // Como mínimo introduce un 1 equivalente al 0 del array
        if (tablero.getPieza(psIni) == null || tablero.getPieza(psIni).getColor() != elTurno)
            return null;
        Posicion psFin = new Posicion((7 - (jugada.charAt(3) - '1')), (jugada.charAt(2) - 'a'));
        if (tablero.getPieza(psFin) != null && tablero.getPieza(psFin).getColor() == tablero.getPieza(psIni).getColor())
            return null;
        return new Movimiento(psIni, psFin);
    }
    public boolean ejecutarJugada(Movimiento movimiento, Tablero tablero){
        Posicion psIni = movimiento.getPosInicial(),
                 psFin = movimiento.getPosFinal();
        Pieza    figura = tablero.getPieza(psIni);

        if (figura instanceof  Rey && Math.abs(movimiento.saltoHorizontal()) == 2 && movimiento.esHorizontal() &&
                !tablero.hayPiezasEntre(movimiento) && validoEnroque(movimiento, tablero) && !(((Rey) figura).isMovido())) {
            ejecutoEnroque(tablero, movimiento);
            return true;
        }
        if (figura.validoMovimiento(movimiento, tablero) && !tablero.hayPiezasEntre(movimiento)) {
            Pieza aux = null;
            if (tablero.hayPieza(psFin))
                aux = tablero.getPieza(psFin);
            tablero.quitaPieza(psIni);
            tablero.ponPieza(figura, psFin);
            if (jaque(tablero, ubicarRey(tablero, getTurno()))) {
                tablero.quitaPieza(psFin);
                if (aux != null)
                    tablero.ponPieza(aux, psFin);
                tablero.ponPieza(figura, psIni);
                System.out.println("Te estas poniendo en jaque");
                return false;
            }
            return true;
        } else
            return false;
    }
    public boolean validoEnroque(Movimiento mov, Tablero tab) {
        int     fila = mov.getPosInicial().getFila();
        int     columna = mov.getPosInicial().getColumna();
        int     sum = (mov.saltoHorizontal() == 2) ? 1 : -1;
        Pieza   rey = tab.getPieza(mov.getPosInicial());
        Pieza   torre;

        if (jaque(tab, ubicarRey(tab, getTurno() ))|| ((Rey) rey).isMovido() || tab.hayPiezasEntre(mov))
            return false;
        if (mov.saltoHorizontal() > 0)
            torre = tab.getPieza(fila, 7);
        else
            torre = tab.getPieza(fila, 0);
        if ( torre.getColor() != rey.getColor() || torre.getClass() != Torre.class || ((Torre) torre).isMovido())
            return false;

        int i = columna + sum; // hay un error tomo como referencia la posicion final del rey y cuando hago el enroque largo si hay una pieza en b1 lo hace igual
        while ( i != mov.getPosFinal().getColumna()){
            i += sum;
            if  (tab.hayPieza(fila, i))
                return false;
           tab.quitaPieza(fila, i - sum);
           tab.ponPieza(rey, fila, i);
           if (jaque(tab, new Posicion(fila, i)))
               return false;
       }
        tab.ponPieza(rey, fila, i);
        return !jaque(tab, new Posicion(fila, i));
    }
    private void ejecutoEnroque(Tablero tablero, Movimiento movimiento) {
        int     psIniFila = movimiento.getPosInicial().getFila();
        Pieza   rey = tablero.getPieza(psIniFila, 4);

        if (movimiento.saltoHorizontal() == 2) {
            Pieza torre = tablero.getPieza(psIniFila, 7);

            tablero.quitaPieza(psIniFila, 7);
            tablero.ponPieza(torre, psIniFila, 5);
            tablero.quitaPieza(psIniFila, 4);
            tablero.ponPieza( rey, psIniFila, 6);
        } else if (movimiento.saltoHorizontal() == -2) {
            Pieza torre = tablero.getPieza(psIniFila, 0);

            tablero.quitaPieza(psIniFila, 0);
            tablero.ponPieza(torre, psIniFila, 3);
            tablero.quitaPieza(psIniFila, 4);
            tablero.ponPieza(rey, psIniFila, 2);
        }
    }
    public Posicion ubicarRey(Tablero tablero, boolean color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero.getPieza(i, j) != null && tablero.getPieza(i, j).getClass() == Rey.class &&
                        tablero.getPieza(i, j).getColor() == color)
                    return new Posicion(i, j);
            }
        }
        return null;
    }

    public boolean jaque(Tablero tablero, Posicion posRey) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero.getPieza(i, j) != null && tablero.getPieza(i, j).getColor() != tablero.getPieza(posRey).getColor() &&
                        tablero.getPieza(i, j).validoMovimiento(new Movimiento(new Posicion(i, j), posRey), tablero) &&
                        !tablero.hayPiezasEntre(new Movimiento(new Posicion(i, j), posRey)))
                    return true;
            }
        }
        return false;
    }

    public boolean jaqueMate(Tablero tablero, Posicion posRey) {
        int nuevaFila, nuevaColumna;

        if (!jaque(tablero, posRey))
            return false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                nuevaFila = posRey.getFila() + i;
                nuevaColumna = posRey.getColumna() + j;

                if (posValida(nuevaFila, nuevaColumna) && tablero.hayPieza(nuevaFila, nuevaColumna))
                    if (!jaque(tablero, new Posicion(nuevaFila, nuevaColumna)) && tablero.getPieza(nuevaFila, nuevaColumna) == null ||
                            tablero.getPieza(nuevaFila, nuevaColumna).getColor() != tablero.getPieza(posRey).getColor())
                        return false;
            }
        }
        return true;
    }

    private boolean posValida(int fila, int columna){
        return (fila >= 0 && columna >= 0 && fila < 8 && columna < 8);
    }
}