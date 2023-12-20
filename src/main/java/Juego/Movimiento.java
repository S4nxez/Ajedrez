package Juego;

public class Movimiento {
    private Posicion posInicial;
    private Posicion posFinal;

    public Movimiento(Posicion posInicial, Posicion posFinal) {
        this.posInicial = posInicial;
        this.posFinal = posFinal;
    }

    public Posicion getPosFinal() {
        return posFinal;
    }

    public Posicion getPosInicial() {
        return posInicial;
    }

    public boolean esVertical(){
        return getPosFinal().getColumna() == getPosInicial().getColumna();
    }
    public boolean esHorizontal(){
        return getPosInicial().getFila() == getPosFinal().getFila();
    }
    boolean esDiagonal(){
        int sum = 1;
        if (getPosFinal().getFila() < getPosInicial().getFila())
            sum = -1;
        for (int i = 0; i <= getPosFinal().getColumna(); i += sum) {
            for (int j = 0; j <= getPosFinal().getFila(); j += sum) { // Esto me va a dar un bucle infinito en caso de que no sea un movimiento diagonal
                if (getPosInicial().getFila() == j)
                    return true;
            }
        }
        return false;
    }
    int saltoHorizontal(){ //devuelve la cantidad de salto horizontal
        return 3;
    } //hay que hacer este y el de abajo aun
    int saltoVertical(){ //devuelve la cantidad de salto vertical
        return 1;
    }
}
