package br.edu.utfpr.dominio;

import java.math.BigDecimal;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Positivo;
import br.edu.utfpr.anotacoes.Tamanho;

public record ContaPessoaJuridica(
    @NaoNulo(mensagem = "Agencia não pode ser nula")
    @Tamanho(min = 4, max = 4, mensagem = "Agencia com tamanho fora do padrão") 
    String agencia, 
    
    @NaoNulo(mensagem = "Numero da conta não pode ser nulo")
    String numero, 
    
    @NaoNulo(mensagem = "Razao social não pode ser nulo")
    String razaoSocial, 
    
    @NaoNulo(mensagem = "Cnpj não pode ser nulo")
    @Tamanho(min = 14, max = 14, mensagem = "Cnpj com tamanho fora do padrão")
    String cnpj, 
    
    @NaoNulo(mensagem = "o capital social e obrigatorio")
    @Positivo(mensagem = "Capital social deve ser maior que zero")
    BigDecimal capitalSocial) implements ContaBancaria {
    // TODO revisar e implementar corretamente conforme requisitos
}
