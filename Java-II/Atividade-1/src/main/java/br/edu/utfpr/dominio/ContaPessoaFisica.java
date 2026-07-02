package br.edu.utfpr.dominio;

import java.time.LocalDate;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Positivo;
import br.edu.utfpr.anotacoes.Tamanho;

public record ContaPessoaFisica(
    @NaoNulo(mensagem = "Agencia não pode ser nula")
    @Tamanho(min = 4, max = 4, mensagem = "Agencia com tamanho fora do padrão")
    String agencia, 

    @NaoNulo(mensagem = "Numero da conta não pode ser nulo")
    String numero, 
    
    @NaoNulo(mensagem = "Nome do Titular não pode ser nulo")
    String titular, 
    
    @NaoNulo(mensagem = "CPF não pode ser nulo")
    @Tamanho(min = 11, max = 11, mensagem = "Cpf com tamanho fora do padrão")
    String cpf, 
    
    @NaoNulo(mensagem = "Email não pode ser nulo")
    String email, 
    
    @NaoNulo(mensagem = "a data de nascimento e obrigatoria")
    @Positivo(mensagem = "Data de nascimento deve ser um valor positivo")
    LocalDate dataNascimento) implements ContaBancaria{
    // TODO revisar e implementar corretamente conforme requisitos
}
