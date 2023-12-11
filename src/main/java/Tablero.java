public class Tablero {
    char[][] tablero;

    public class Tablero {
        private int[][] tablero;

        public Tablero() {
            this.tablero = new int[][]{
                    {'♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜'},
                    {'♟', '♟', '♟', '♟', '♟', '♟', '♟', '♟'},
                    {'.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.'},
                    {'♙', '♙', '♙', '♙', '♙', '♙', '♙', '♙'},
                    {'♖', '♘', '♗', '♕', '♔', '♗', '♘', '♖'}
            };
        }

        public void pintarTablero() {
            for (int fila = 0; fila < 8; fila++) {
                for (int columna = 0; columna < 8; columna++) {
                    System.out.print((char) tablero[fila][columna] + " ");
                }
                System.out.println(); // Nueva línea después de cada fila
            }
        }

        // Resto de los métodos de la clase Tablero
    }
}
