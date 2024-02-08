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

        // Verificamos si las diferencias son iguales (lo que indica un movimiento diagonal)
        return (diffFila == diffColumna);
    }
    public int saltoHorizontal(){
        int ret = 0;

        if (getPosFinal().getColumna() > getPosInicial().getColumna())
            incremento = 1;
        for (int i = getPosInicial().getColumna(); i != getPosFinal().getColumna(); i+= incremento)
            ret++;
        System.out.println("SALTO HORIZONTAL: " + ret);
        return ret;
    }
    public int saltoVertical(){
        int ret = 0;
        int incremento = -1;

        if (getPosFinal().getFila() > getPosInicial().getFila())
            incremento = 1;
        for (int i = getPosInicial().getFila(); i != getPosFinal().getFila(); i += incremento)
            ret += incremento;
        System.out.println("SALTO VERTICAL: " + ret);
        return ret;
    }
}
