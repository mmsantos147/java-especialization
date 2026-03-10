package com.utfpr;
public class Motor {
    private int qtdPist;
    private int potencia;

    public Motor()  {
        this.qtdPist = 0;
        this.potencia = 0;
    }

    public void setQtdPist(int qtdPist) {
        this.qtdPist = qtdPist;
    }

    public int getQtdPist() {
        return qtdPist;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getPotencia() {
        return potencia;
    }
}
