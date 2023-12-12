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
            tablero[1][0] = tablero[6][0] = new Caballo(false,"♞");
            tablero[1][7] = tablero[6][7] = new Caballo(false, "♘");
            //Coloco los alfiles
            tablero[2][0] = tablero[5][0] = new Alfil(false, "♝");
            tablero[2][7] = tablero[5][7] = new Alfil(true, "♗");
            //Coloco el rey y la reina en cada lado
            tablero[3][0] = new Rey(false, "♚");
            tablero[3][7] = new Rey(true,"♔");
            tablero[4][0] = new Reina(false, "♛");
            tablero[4][7] = new Reina(true, "♕");
        }

        public void pintarTablero() {
            for (int fila = 0; fila < 8; fila++) {
                for (int columna = 0; columna < 8; columna++) {
                    if (tablero[fila][columna] != null)
                        System.out.print( tablero[fila][columna] + " ");
                    else
                        System.out.println("#");
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
            return ;
        }
        public void ponPieza(Pieza figura, int fila, int columna){ // no entiendo por que hay que hacer el mismo metodo metiendo fila y columna por separado y con posicion luego, no podría usar siempre uno?
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