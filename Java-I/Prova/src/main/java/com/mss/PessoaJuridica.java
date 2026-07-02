// Matheus Martins dos Santos

package com.mss;

public final class PessoaJuridica extends ClienteBanco{

    private int cnpj = 0;
    private PessoaFisica responsavel = new PessoaFisica();

    public int getCnpj() {
        return cnpj;
    }
    
    public PessoaFisica getResponsavel() {
        return responsavel;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void verifDoc() {
        if (responsavel.getNome().length() <= 30) {
            System.out.println("Nome válido para o Responsável");
        } else {
            System.out.println("Nome inválido para o Responsável");
        }   
    }
}
