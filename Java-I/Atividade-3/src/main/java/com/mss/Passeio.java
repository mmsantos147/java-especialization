package com.mss;

public class Passeio extends Veiculo {

    private int qtdPassageiros;

    public Passeio () {
        this.qtdPassageiros = 0;
    }

    public Passeio (int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    public Passeio(String placa, String marca, String modelo, String cor,
               float velocMax, int qtdRodas, int qtdPist, int potencia,
               int qtdPassageiros) {
        super(placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia);
        this.qtdPassageiros = qtdPassageiros;
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
