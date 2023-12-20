package Juego;

public class Juego {
    private boolean elTurno; // 0->Negras 1->Blancas

    public boolean getTurno() {
        return elTurno;
    }

    public void setTurno(boolean nuevoTurno) {
        this.elTurno = nuevoTurno;
    }

    public Movimiento jugada(String jugada, Tablero tablero) {
        // Implementa la lógica para procesar la jugada y actualizar el tablero
        // Devuelve un objeto de tipo piezas. Movimiento con la información de la jugada
        Posicion posIni = new Posicion(1, 2);
        Posicion posFin = new Posicion(2 , 2);
        Movimiento movimiento = new Movimiento(posIni, posFin);

        // Lógica para actualizar el tablero y realizar otras acciones necesarias
        // ...

        elTurno = !elTurno;
        tablero.pintarTablero();
        return movimiento;
    }
}

