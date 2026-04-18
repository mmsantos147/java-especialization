package com.mss;

public final class Passeio extends Veiculo implements Calcular {

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
    
    @Override
    public int calcular() {
        return getPlaca().length() + getMarca().length() + getModelo().length() + getCor().length();
    }

    @Override
    public String toString() {
        return super.toString() +
            "Quantidade de Passageiros do Veículo: " + getQtdPassageiros() + "\n" +
            "Velocidade Máxima Convertida: " + calcVel(getVelocMax()) + " M/h\n" +
            "Calculo da quantidade de letras nos atributos Strings: " + calcular() + "\n"
        ;
    }
}
