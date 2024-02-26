package juego;

import piezas.*;

public class Tablero {
    private Pieza[][] tablero;
    public Tablero() {
        this.tablero = new Pieza[8][8];
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = new Peon(true, "♙");
            tablero[1][i] = new Peon(false,"♟");
        }
        tablero[7][0] = tablero[7][7] = new Torre(true, "♖");
        tablero[0][7] = tablero[0][0] = new Torre(false,"♜");
        tablero[7][1] = tablero[7][6] = new Caballo(true, "♘");
        tablero[0][1] = tablero[0][6] = new Caballo(false,"♞");
        tablero[7][2] = tablero[7][5] = new Alfil(true, "♗");
         tablero[0][2] = tablero[0][5] = new Alfil(false, "♝");
        tablero[7][4] = new Rey(true,"♔");
        tablero[0][4] = new Rey(false, "♚");
        tablero[7][3] = new Reina(true, "♕");
        tablero[0][3] = new Reina(false, "♛");
    }

    public void pintarTablero() {
        boolean esBlanco = true;
        int     i = 8;
        for (int fila = 0; fila <= 7; fila++) {
            System.out.print(i + " |");
            for (int columna = 0; columna <= 7; columna++) {
                if (tablero[fila][columna] != null)
                    System.out.print(tablero[fila][columna] + " ");
                else if (esBlanco)
                    System.out.print(" □ ");
                else
                    System.out.print(" ■ ");
                esBlanco = !esBlanco;
            }
            esBlanco = !esBlanco;
            System.out.println();
            i--;
        }
        System.out.println("- |A  B  C  D  E  F  G  H");
    }

    public boolean hayPieza(int fila, int columna){
        return tablero[fila][columna] != null;
    }
    public boolean hayPieza(Posicion pos){
        return tablero[pos.getFila()][pos.getColumna()] != null;
    }
    public boolean hayPiezasEntre(Movimiento mov){
        int sum;

        if (mov.esVertical()){
            sum = (mov.getPosInicial().getFila() > mov.getPosFinal().getFila()) ? -1 : 1;
            for (int i = mov.getPosInicial().getFila() + sum; i != mov.getPosFinal().getFila(); i += sum) {
                if (hayPieza(i, mov.getPosInicial().getColumna()))
                    return true;
            }
        } else if (mov.esHorizontal()) {
            sum = (mov.getPosInicial().getColumna() > mov.getPosFinal().getColumna()) ? - 1 : 1;
            for (int i = mov.getPosInicial().getColumna() + sum; i != mov.getPosFinal().getColumna(); i += sum) {
                if (hayPieza(mov.getPosInicial().getFila(), i))
                    return true;
            }
        }else if(mov.esDiagonal()){
            int sumFil = (mov.getPosInicial().getFila() > mov.getPosFinal().getFila()) ? -1 : 1;
            int sumCol  = (mov.getPosInicial().getColumna() > mov.getPosFinal().getColumna()) ? -1 : 1;

            for (int i = mov.getPosInicial().getFila() + sumFil, j = mov.getPosInicial().getColumna() + sumCol;
                 i != mov.getPosFinal().getFila() && j != mov.getPosFinal().getColumna(); i += sumFil, j += sumCol) {
                if (hayPieza(i, j))
                    return true;
            }
        }
        return false;
    }
    public void ponPieza(Pieza figura, int fila, int columna){
        tablero[fila][columna] = figura;
    }
    public void ponPieza(Pieza figura, Posicion pos){
        tablero[pos.getFila()][pos.getColumna()] = figura;
    }
    public void quitaPieza(int fila, int columna){
        tablero[fila][columna] = null;
    }
    public void quitaPieza(Posicion pos){
        tablero[pos.getFila()][pos.getColumna()] = null;
    }
    public Pieza getPieza(int fila, int columna){
        return tablero[fila][columna];
    }
    public Pieza getPieza(Posicion pos){
        return tablero[pos.getFila()][pos.getColumna()];
    }
}