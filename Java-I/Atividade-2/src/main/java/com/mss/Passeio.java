package com.mss;

public class Passeio extends Veiculo {

    private int qtdPassageiros;

    public Passeio () {
        this.qtdPassageiros = 0;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }
    
}
