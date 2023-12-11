public class Juego {
    private int elTurno; // 0->Negras 1->Blancas //Boolean o String o char

    public int getTurno() {
        return elTurno;
    }

    public void setTurno(int nuevoTurno) {
        this.elTurno = nuevoTurno;
    }

    public Movimiento jugada(String jugada, Tablero tablero) {
        // Implementa la lógica para procesar la jugada y actualizar el tablero
        // Devuelve un objeto de tipo Movimiento con la información de la jugada
        Movimiento movimiento = new Movimiento(/* Parámetros de la jugada */);

        // Lógica para actualizar el tablero y realizar otras acciones necesarias
        // ...

        // Actualiza el turno
        elTurno = 1 - elTurno; // Alternar entre 0 y 1

        return movimiento;
    }
}

