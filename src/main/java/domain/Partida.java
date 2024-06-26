package domain;

import dao.PartidaDAO;
import domain.piezas.Pieza;
import domain.piezas.Rey;
import domain.piezas.Torre;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Partida implements Serializable {
    private boolean elTurno = true; // 0->Negras 1->Blancas
    private LocalDate fecha;
    private int id;
    private Tablero tablero;

    public Partida() {
        this.fecha = LocalDate.now();
        this.id = PartidaDAO.getAutonumerico();
        this.tablero = new Tablero();
    }

    public boolean getTurno() {
        return elTurno;
    }

    public void setTurno(boolean nuevoTurno) {
        this.elTurno = nuevoTurno;
    }

    public Movimiento jugada(String jugada, Tablero tablero) {
        Posicion psIni = new Posicion(( (jugada.charAt(0) - '0')), (jugada.charAt(1) - '0'));
        if (tablero.getPieza(psIni) == null || tablero.getPieza(psIni).getColor() != elTurno)
            return null;

        Posicion psFin = new Posicion(( (jugada.charAt(2) - '0')), (jugada.charAt(3) - '0'));
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
                return false;
            }
            this.tablero = tablero;
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

        int i = columna + sum; // hay un error tomo como referencia la posición final del rey y cuando hago el enroque largo si hay una pieza en b1 lo hace igual
        if (sum == -1 && tab.hayPieza(fila, 1))
            return false;
        while ( i != mov.getPosFinal().getColumna()){
            i += sum;
            if  (tab.hayPieza(fila, i))
                return false;
           tab.quitaPieza(fila, i - sum);
           tab.ponPieza(rey, fila, i);
           if (jaque(tab, new Posicion(fila, i))){
                tab.quitaPieza(fila, i);
               return false;
           }
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
                Pieza pieza = tablero.getPieza(i, j);
                if (pieza != null && pieza.getColor() != tablero.getPieza(posRey).getColor() &&
                        tablero.getPieza(i, j).validoMovimiento(new Movimiento(new Posicion(i, j), posRey), tablero) &&
                        !tablero.hayPiezasEntre(new Movimiento(new Posicion(i, j), posRey)))
                    return true;
            }
        }
        return false;
    }

    public boolean jaqueMate(Tablero tablero, Posicion posRey) {
        /*
        //este método pasa a jaque las coordenadas del rey si estuviese en las posiciones que le rodean,
        //pero el método jaque usa directamente la posición del rey para ver su color y esto provoca un null pointer
        int nuevaFila, nuevaColumna;

        if (!jaque(tablero, posRey))
            return false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                nuevaFila = posRey.getFila() + i;
                nuevaColumna = posRey.getColumna() + j;

                if (posValida(nuevaFila, nuevaColumna) && !tablero.hayPieza(nuevaFila, nuevaColumna))
                    if (!jaque(tablero, new Posicion(nuevaFila, nuevaColumna)) && tablero.getPieza(nuevaFila, nuevaColumna) == null ||
                            tablero.getPieza(nuevaFila, nuevaColumna).getColor() != tablero.getPieza(posRey).getColor())
                        return false;
            }
        }*/
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partida partida = (Partida) o;
        return id == partida.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}