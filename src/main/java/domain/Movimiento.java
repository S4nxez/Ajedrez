package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Movimiento { //revisar lo de record class con Gema
    private final Posicion posInicial;
    private final Posicion posFinal;

    public boolean esVertical(){
        return getPosFinal().getColumna() == getPosInicial().getColumna();
    }

    public boolean esHorizontal(){
        return getPosInicial().getFila() == getPosFinal().getFila();
    }

    public boolean esDiagonal(){
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
