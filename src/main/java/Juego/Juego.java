package Juego;

import piezas.Pieza;
import piezas.Rey;

public class Juego {
    private boolean elTurno = true; // 0->Negras 1->Blancas

    public boolean getTurno() {
        return elTurno;
    }

    public void setTurno(boolean nuevoTurno) {
        this.elTurno = nuevoTurno;
    }

    public Movimiento jugada(String jugada, Tablero tablero) { //control de entrada solo creo que se puede hacer mas limpio creando una excepcion
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
        Pieza figura = tablero.getPieza(psIni);
        Movimiento movimiento = new Movimiento(psIni, psFin);
        if (figura.validoMovimiento(movimiento, tablero) && !tablero.hayPiezasEntre(movimiento)) {
            boolean hayAux = false;
            Pieza aux = null;
            if (tablero.hayPieza(psFin)) {
                aux = tablero.getPieza(psFin);
                hayAux = true;
            }
            tablero.quitaPieza(psIni);
            tablero.ponPieza(figura, psFin);
            if (jaque(tablero, ubicarRey(tablero, getTurno()))) {
                tablero.quitaPieza(psFin);
                if (hayAux)
                    tablero.ponPieza(aux, psFin);
                tablero.ponPieza(figura, psIni);
                System.out.println("cuidado, te estas poniendo en jaque");
                return null;
            }
        } else
            return null;
        return movimiento;
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
                if (tablero.getPieza(i, j) != null && tablero.getPieza(i, j).getColor() == !tablero.getPieza(posRey).getColor() &&
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