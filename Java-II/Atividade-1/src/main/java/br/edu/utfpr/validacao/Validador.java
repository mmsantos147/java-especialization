package br.edu.utfpr.validacao;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Positivo;
import br.edu.utfpr.anotacoes.Tamanho;
import br.edu.utfpr.dominio.ContaBancaria;
import br.edu.utfpr.dominio.ContaPessoaFisica;
import br.edu.utfpr.dominio.ContaPessoaJuridica;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Validador {

    private static final int IDADE_MINIMA = 18;
    private static final BigDecimal CAPITAL_SOCIAL_MINIMO = new BigDecimal("10000");

    public ResultadoValidacao validar(ContaBancaria conta) {

        final List<Violacao> violacoes = new ArrayList<>();

        // TODO ver requisito 1, implementar aqui
        switch (conta) {
            case null -> violacoes.add(new Violacao("(objeto)", "o objeto a validar e nulo"));
                
            default -> {
                violacoes.addAll(validarCampos(conta));
                violacoes.addAll(regrasDeNegocio(conta));
            }
        }

        return ResultadoValidacao.de(violacoes);
    }

    public List<Violacao> validarCampos(Object objeto) {

        final List<Violacao> violacoes = new ArrayList<>();

        // TODO ver requisito 2, implementar aqui
        for (Field campo : objeto.getClass().getDeclaredFields()) {
            campo.setAccessible(true);
            try {
                Object valor = campo.get(objeto);
                verificarNaoNulo(campo, valor, violacoes);
                verificarTamanho(campo, valor, violacoes);
                verificarPositivo(campo, valor, violacoes);
            } catch (IllegalAccessException e) {
            }
        }
        
        return violacoes;
        
    }

    private void verificarNaoNulo(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de nao nulo
        if (campo.isAnnotationPresent(NaoNulo.class) && valor == null) {
            NaoNulo anotacaoNaoNulo = campo.getAnnotation(NaoNulo.class);
            acc.add(new Violacao(campo.getName(), anotacaoNaoNulo.mensagem()));
        }
    }

    private void verificarTamanho(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de tamanho
        if (!campo.isAnnotationPresent(Tamanho.class) || valor == null) return;
        
        Tamanho anotacaoTamanho = campo.getAnnotation(Tamanho.class);
        if (valor.toString().length() > anotacaoTamanho.max() || valor.toString().length() < anotacaoTamanho.min()){
            acc.add(new Violacao(campo.getName(), anotacaoTamanho.mensagem()));
        }
        
    }

    private void verificarPositivo(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de positivo
        if (!campo.isAnnotationPresent(Positivo.class) || valor == null) return;

        boolean negativo = switch (valor) {
            case Integer i -> i <= 0;
            case Double d -> d <= 0.0;
            case Long l -> l <= 0;
            case BigDecimal bd -> bd.compareTo(BigDecimal.ZERO) <= 0;
            default -> false;
        };

        if (negativo) {
            Positivo anotacaoPositivo = campo.getAnnotation(Positivo.class);
            acc.add(new Violacao(campo.getName(), anotacaoPositivo.mensagem()));
        }
    }

    private List<Violacao> regrasDeNegocio(ContaBancaria conta) {
        // TODO implementar as regras de negocio por tipo
        return switch (conta) {
            case ContaPessoaFisica contaPessoaFisica -> validarMaioridade(contaPessoaFisica);
            case ContaPessoaJuridica (String _, String _, String _, String _, BigDecimal capitalSocial) -> validarCapitalSocial(capitalSocial);
        };
    }

    private List<Violacao> validarMaioridade(ContaPessoaFisica pf) {
        // TODO implementar regra de negocio de maioridade
        final List<Violacao> violacaoIdade = new ArrayList<>();

        if (pf.dataNascimento() == null) return List.of();
        
        if (LocalDate.now().getYear() - pf.dataNascimento().getYear() < IDADE_MINIMA) {
            violacaoIdade.add(new Violacao(Violacao.DATA_NASCIMENTO_CAMPO, Violacao.DATA_NASCIMENTO_MENSAGEM));
            return violacaoIdade;
        }
        return List.of();
    }

    private List<Violacao> validarCapitalSocial(BigDecimal capital) {
        // TODO implementar regra de negocio de capital social
        final List<Violacao> violacaoCapitalSocial = new ArrayList<>();
        
        if (capital == null) return List.of();

        if (capital.compareTo(CAPITAL_SOCIAL_MINIMO) < 0) {
            violacaoCapitalSocial.add(new Violacao(Violacao.CAPITAL_SOCIAL_CAMPO, Violacao.CAPITAL_SOCIAL_MENSAGEM));
            return violacaoCapitalSocial;
        }
        return List.of();
    }
}
