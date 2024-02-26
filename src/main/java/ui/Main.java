package ui;

import juego.Juego;
import juego.Tablero;
import juego.Movimiento;
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
            Movimiento mov = juego.jugada(jugada, tablero);

            if (mov == null)
                System.out.println("Entrada no valida");
            else if (!juego.ejecutarJugada(mov, tablero))
                System.out.println("Movimiento ilegal");
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