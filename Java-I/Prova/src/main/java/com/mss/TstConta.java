// Matheus Martins dos Santos

package com.mss;

public class TstConta 
{
    public static void main( String[] args )
    {
        PessoaJuridica pessoaJuridica1 = new PessoaJuridica();
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica();
        PessoaJuridica pessoaJuridica3 = new PessoaJuridica();
        try {
            System.out.println("----- CLIENTE 1 -----");

            pessoaJuridica1.setNumeroConta(23);
            pessoaJuridica1.setCnpj(45);
            pessoaJuridica1.getEnder().setRua("Rua Vitorio");
            pessoaJuridica1.getResponsavel().setCpf(15);
            pessoaJuridica1.getResponsavel().setNome("Matheus Martins dos Santos");

            System.out.println("Número da conta..: " + pessoaJuridica1.getNumeroConta());
            pessoaJuridica1.validar();
            System.out.println("CNPJ..: " + pessoaJuridica1.getCnpj() + "\n" +
                    "Rua..: " + pessoaJuridica1.getEnder().getRua());
            System.out.println("CPF do Responsável pela conta..: " + pessoaJuridica1.getResponsavel().getCpf());
            pessoaJuridica1.getResponsavel().verifDoc();
            System.out.println("Nome do Responsável..: " + pessoaJuridica1.getResponsavel().getNome());
            pessoaJuridica1.verifDoc();

            System.out.println("\n----- CLIENTE 2 -----");

            pessoaJuridica2.setNumeroConta(22);
            pessoaJuridica2.setCnpj(45);
            pessoaJuridica2.getEnder().setRua("Rua Vitorio");
            pessoaJuridica2.getResponsavel().setCpf(5);
            // pessoaJuridica2.getResponsavel().setCpf(25);
            pessoaJuridica2.getResponsavel().setNome("Matheus Souza Martins dos Santos");

            System.out.println("Número da conta..: " + pessoaJuridica2.getNumeroConta());
            pessoaJuridica2.validar();
            System.out.println("CNPJ..: " + pessoaJuridica2.getCnpj() + "\n" +
                    "Rua..: " + pessoaJuridica2.getEnder().getRua());
            System.out.println("CPF do Responsável pela conta..: " + pessoaJuridica2.getResponsavel().getCpf());
            pessoaJuridica2.getResponsavel().verifDoc();
            System.out.println("Nome do Responsável..: " + pessoaJuridica2.getResponsavel().getNome());
            pessoaJuridica2.verifDoc();

            System.out.println("\n----- CLIENTE 3 -----");

            pessoaJuridica3.setNumeroConta(-23);
            pessoaJuridica3.setCnpj(45);
            pessoaJuridica3.getEnder().setRua("Rua Vitorio");
            pessoaJuridica3.getResponsavel().setCpf(15);
            pessoaJuridica3.getResponsavel().setNome("Matheus Martins dos Santos");

            System.out.println("Número da conta..: " + pessoaJuridica3.getNumeroConta());
            pessoaJuridica3.validar();
            System.out.println("CNPJ..: " + pessoaJuridica3.getCnpj() + "\n" +
                    "Rua..: " + pessoaJuridica3.getEnder().getRua());
            System.out.println("CPF do Responsável pela conta..: " + pessoaJuridica3.getResponsavel().getCpf());
            pessoaJuridica3.getResponsavel().verifDoc();
            System.out.println("Nome do Responsável..: " + pessoaJuridica3.getResponsavel().getNome());
            pessoaJuridica3.verifDoc();
        } catch (NumException e) {
            e.impMsg();
        }
    }
}
