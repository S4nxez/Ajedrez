import Juego.Juego;
import Juego.Tablero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String jugada;
        Scanner sc = new Scanner(System.in);
        Juego juego = new Juego();
        Tablero tablero = new Tablero();
        while(!juego.jaqueMate(tablero, juego.ubicarRey(tablero, juego.getTurno()))) {
            tablero.pintarTablero();
            jugada = sc.nextLine().toLowerCase();
            if (juego.jugada(jugada, tablero) == null)
                System.out.println("ERROR");
            else if (juego.jaque(tablero, juego.ubicarRey(tablero, !juego.getTurno()))) {
                System.out.println("Jaque");
                juego.setTurno(!juego.getTurno());
            }else
                juego.setTurno(!juego.getTurno());
        }
        tablero.pintarTablero();
        System.out.println("Jaque Mate");
    }
}