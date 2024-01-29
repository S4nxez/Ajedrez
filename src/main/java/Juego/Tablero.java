package Juego;

import piezas.*;
public class Tablero {
        private Pieza[][] tablero;
        public Tablero() {
            this.tablero = new Pieza[8][8];
            //Coloco los peones:
            for (int i = 0; i < 8; i++) {
                tablero[1][i] = new Peon(true, "♙");
                tablero[6][i] = new Peon(false,"♟");
            }
            //Coloco las torres:
            tablero[0][0] = tablero[0][7] = new Torre(true, "♖");
            tablero[7][7] = tablero[7][0] = new Torre(false,"♜");
            //Coloco los caballos
            tablero[0][1] = tablero[0][6] = new Caballo(true, "♘");
            tablero[7][1] = tablero[7][6] = new Caballo(false,"♞");
            //Coloco los alfiles
            tablero[0][2] = tablero[0][5] = new Alfil(true, "♗");
            tablero[7][2] = tablero[7][5] = new Alfil(false, "♝");
            //Coloco el rey y la reina en cada lado
            tablero[0][3] = new Rey(true,"♔");
            tablero[7][3] = new Rey(false, "♚");
            tablero[0][4] = new Reina(true, "♕");
            tablero[7][4] = new Reina(false, "♛");
        }

        public void pintarTablero() { // tener en cuenta que esto invierte el tablero al imprimirlo para mejor UI pero el tablero esa almacenado con las blancas arriba por el tema del input que parte de arriba
            boolean esBlanco = true;
            for (int fila = 7; fila >= 0; fila--) {
                System.out.print(fila + 1 + " |");
                for (int columna = 7; columna >= 0; columna--) {
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
            }
            System.out.println("- | A  B  C  D  E  F  G  H");
        }

        public boolean hayPieza(int fila, int columna){
            return tablero[fila][columna] != null;
        }
        public boolean hayPieza(Posicion pos){
            return tablero[pos.getFila()][pos.getColumna()] != null;
        }
        public boolean hayPiezasEntre(Movimiento mov){ //Este metodo lo tengo aqui y no en la clase movimiento proque sino tendria que crear un tablero en la clase movimiento creo
            int sum = 1;

            if (mov.esHorizontal()){
                if (mov.getPosInicial().getFila() > mov.getPosFinal().getFila())
                    sum = -1;
                for (int i = mov.getPosInicial().getFila(); i != mov.getPosFinal().getFila(); i += sum) {
                    if (hayPieza(mov.getPosInicial().getColumna(), i))
                        return true;
                }
            } else if (mov.esVertical()) {
                for (int i = mov.getPosInicial().getColumna(); i < mov.getPosFinal().getColumna(); i += sum) {
                    if (hayPieza(i, mov.getPosInicial().getFila()))
                        return true;
                }
            }else{ //en este caso sería un movimiento diagonal porque para el caballo no uso el método
                if (mov.getPosInicial().getFila() > mov.getPosFinal().getFila())
                    sum = -1;
                for (int i = mov.getPosInicial().getFila(); i < mov.getPosFinal().getFila(); i += sum) {
                    for (int j = mov.getPosInicial().getColumna(); j < mov.getPosFinal().getColumna(); j += sum) {
                        if (hayPieza(i, j))
                            return true;
                    }
                }
            }
            return false;
        }
        public void ponPieza(Pieza figura, int fila, int columna){ // no entiendo por qué hay que hacer el mismo método metiendo fila y columna por separado y con posición luego, no podría usar siempre uno?
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