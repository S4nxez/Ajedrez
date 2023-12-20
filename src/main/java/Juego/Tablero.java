package Juego;

import piezas.*;
public class Tablero {
        private Pieza[][] tablero;
        public Tablero() {
            this.tablero = new Pieza[8][8];
            //Coloco los peones:
            for (int i = 0; i < 8; i++) {
                tablero[1][i] = new Peon(false,"♟");
                tablero[6][i] = new Peon(true, "♙");
            }
            //Coloco las torres:
            tablero[0][0] = tablero[0][7] = new Torre(false,"♜");
            tablero[7][7] = tablero[7][0] = new Torre(true, "♖");
            //Coloco los caballos
            tablero[0][1] = tablero[0][6] = new Caballo(false,"♞");
            tablero[7][1] = tablero[7][6] = new Caballo(true, "♘");
            //Coloco los alfiles
            tablero[0][2] = tablero[0][5] = new Alfil(false, "♝");
            tablero[7][2] = tablero[7][5] = new Alfil(true, "♗");
            //Coloco el rey y la reina en cada lado
            tablero[0][4] = new Rey(false, "♚");
            tablero[7][4] = new Rey(true,"♔");
            tablero[0][3] = new Reina(false, "♛");
            tablero[7][3] = new Reina(true, "♕");
        }

        public void pintarTablero() {
            for (int fila = 0; fila < 8; fila++) {
                System.out.print(fila + 1 + " | ");
                for (int columna = 0; columna < 8; columna++) {
                    if (tablero[fila][columna] != null)
                        System.out.print( tablero[fila][columna] + " ");
                    else
                        System.out.print("");
                }
                System.out.println();
            }
        }

        public boolean hayPieza(int fila, int columa){
            return tablero[fila][columa] != null;
        }
        public boolean hayPieza(Posicion pos){
            return tablero[pos.getFila()][pos.getColumna()] != null;
        }
        public boolean hayPiezasEntre(Movimiento mov){
            int sum = 1;

            if (mov.esHorizontal()){
                if (mov.getPosInicial().getFila() > mov.getPosFinal().getFila())
                    sum = -1;
                for (int i = mov.getPosInicial().getFila(); i != mov.getPosFinal().getFila(); i += sum) {
                    if (tablero[mov.getPosInicial().getColumna()][i] != null)
                        return false;
                }
            } else if (mov.esVertical()) {
                for (int i = mov.getPosInicial().getColumna(); i < mov.getPosFinal().getColumna(); i += sum) {
                    if (tablero[i][mov.getPosInicial().getFila()] != null)
                        return false;
                }
            }else{ //en este caso sería un movimiento diagonal porque para el caballo no uso el método
                if (mov.getPosInicial().getFila() > mov.getPosFinal().getFila())
                    sum = -1;
                for (int i = mov.getPosInicial().getFila(); i < mov.getPosFinal().getFila(); i += sum) {
                    for (int j = mov.getPosInicial().getColumna(); j < mov.getPosFinal().getColumna(); j += sum) {
                        if (tablero[i][j] != null)
                            return false;
                    }
                }
            }
            return true;
        }
        public void ponPieza(Pieza figura, int fila, int columna){ // no entiendo por qué hay que hacer el mismo metodo metiendo fila y columna por separado y con posicion luego, no podría usar siempre uno?
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