package piezas;

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
        return posFinal.getColumna() == posInicial.getColumna();
    }
    public boolean esHorizontal(){
        return posInicial.getFila() == posFinal.getFila();
    }
    boolean esDiagonal(){
        return ;
    }
    int saltoHorizontal(){ //devuelve la cantidad de salto horizontal
        return ;
    }
    int saltoVertical(){ //devuelve la cantidad de salto vertical
        return ;
    }
}
