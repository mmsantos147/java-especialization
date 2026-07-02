// Matheus Martins dos Santos

package com.mss;

public abstract class ClienteBanco implements Verifica{
 
    private int numeroConta = 0;
    private String nome = "";
    private Endereco ender = new Endereco();

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEnder() {
        return ender;
    }

    public void setNumeroConta(int numeroConta) throws NumException {
        if (numeroConta < 0) {
            throw new NumException();
        }

        this.numeroConta = numeroConta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract void verifDoc();

    @Override
    public void validar() {
        if (numeroConta % 2 == 0) {
            System.out.println("Número da conta é par");            
        } else {
            System.out.println("Número da conta é impar");
        }
    }
}