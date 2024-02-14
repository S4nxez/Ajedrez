package Juego;

import piezas.Caballo;
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
        if (jugada.charAt(0) < 'a' || jugada.charAt(0) > 'h' || jugada.charAt(1) < '1' || jugada.charAt(1) > '8' ||
                jugada.charAt(2) < 'a' || jugada.charAt(2) > 'h' || jugada.charAt(3) < '1' || jugada.charAt(3) > '8')
            return null;
        Posicion psIni = new Posicion((7-(jugada.charAt(1) - 49)), (jugada.charAt(0) - 97));
        // Resto 49 no 48 ya que como mínimo introduce un 1 equivalente al 0 del array
        if (tablero.getPieza(psIni) == null || tablero.getPieza(psIni).getColor() != elTurno)
            return null;
        Posicion psFin = new Posicion((7-(jugada.charAt(3) - 49)), (jugada.charAt(2) - 97));
        if (tablero.getPieza(psFin) != null && tablero.getPieza(psFin).getColor() == tablero.getPieza(psIni).getColor())
            return null;
        Pieza figura = tablero.getPieza(psIni);
        Movimiento movimiento = new Movimiento(psIni, psFin);
        if (figura.validoMovimiento(movimiento)  && !tablero.hayPiezasEntre(movimiento)) {
            tablero.quitaPieza(psIni);
            tablero.ponPieza(figura, psFin);
            //meter aqui que si con esto movido el rey esta en jaque mandamos un error y quitoPieza psFin y ponPieza psIni
        }else
            movimiento = null;
        return movimiento;
    }
    public boolean jaque(Tablero tablero) {
        Posicion posRey = null;
        for (int i = 0; i < 8; i++) { //Busco la posición del rey enemigo como aún no se ha cambiado el turno deberia de dividir esto en otro metodo ubicar rey
            for (int j = 0; j < 8; j++) {
                if (tablero.getPieza(i, j) != null && tablero.getPieza(i, j).getClass() == Rey.class &&
                        tablero.getPieza(i, j).getColor() == !elTurno)
                    posRey = new Posicion(i, j);
            }
        }
        for (int i = 0; i < 8; i++) { //Busco si alguna pieza puede comerse al rey AQUI NO TENGO EN CUENTA HAYPIEZASENTRE Y CUANDO LO SOLUCIONE NO SE COMO HACER CON EL CABALLO, QUIERO METER EL HAY PIEZAS ENTRE EN CADA VALIDO MOVIMIENTO DE CADA PIEZA Y EN EL CABALLO NO SE LO PONGO
            for (int j = 0; j < 8; j++) {
                if (tablero.getPieza(i, j) != null && tablero.getPieza(i, j).getColor() == elTurno &&
                        tablero.getPieza(i, j).validoMovimiento(new Movimiento(new Posicion(i, j), posRey)))
                    return true;
            }
        }
        return false;
    }
}