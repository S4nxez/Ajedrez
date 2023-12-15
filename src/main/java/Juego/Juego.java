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
        Movimiento movimiento = new Movimiento(/* Parámetros de la jugada */);

        // Lógica para actualizar el tablero y realizar otras acciones necesarias
        // ...

        // Actualiza el turno
        elTurno = !elTurno; // Alternar entre 0 y 1

        return movimiento;
    }
}

