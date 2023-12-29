import Juego.Juego;
import Juego.Tablero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean mate = false;
        String jugada;
        Scanner sc = new Scanner(System.in);
        Juego juego = new Juego();
        Tablero tablero = new Tablero();

        while(!mate) {
            tablero.pintarTablero();
            jugada = sc.nextLine();
            if (juego.jugada(jugada, tablero) == null)
                System.out.println("ERROR");
        }
        tablero.pintarTablero();
    }
}