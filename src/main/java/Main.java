import Juego.Juego;
import Juego.Tablero;
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        Tablero tablero = new Tablero();
        juego.jugada("", tablero);
    }
}