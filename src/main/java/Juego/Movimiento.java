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
        // Calculamos las diferencias en fila y columna
        int diffFila = Math.abs(getPosFinal().getFila() - getPosInicial().getFila());
        int diffColumna = Math.abs(getPosFinal().getColumna() - getPosInicial().getColumna());

        return (diffFila == diffColumna);
    }
    public int saltoHorizontal(){
        int ret = 0;
        int incremento = (getPosFinal().getColumna() > getPosInicial().getColumna()) ? 1 : -1;

        for (int i = getPosInicial().getColumna(); i != getPosFinal().getColumna(); i+= incremento)
            ret += incremento;
        return ret;
    }
    public int saltoVertical(){
        int ret = 0;
        int incremento = (getPosFinal().getFila() > getPosInicial().getFila()) ? 1 : -1;

        for (int i = getPosInicial().getFila(); i != getPosFinal().getFila(); i += incremento)
            ret += incremento;
        return ret;
    }
}
