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
    public boolean esDiagonal(){
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
    public int saltoHorizontal(){ //devuelve la cantidad de salto horizontal hay que arreglar este metodo del mismo modo que el de salto vertical
        int ret = 0;

        for (int i = getPosInicial().getColumna(); i < getPosFinal().getColumna(); i++) {
            ret++;
        }
        System.out.println("SALTO HORIZONTAL: " + ret);
        return ret;
    }
    public int saltoVertical(){ //devuelve la cantidad de salto vertical
        int ret = 0;
        int incremento = -1;

        if (getPosFinal().getFila() > getPosInicial().getFila())
            incremento = 1;

        for (int i = getPosInicial().getFila(); i < getPosFinal().getFila(); i += incremento) {
            ret += incremento;
        }
        System.out.println("SALTO VERTICAL: " + ret);
        return ret;
    }
}
