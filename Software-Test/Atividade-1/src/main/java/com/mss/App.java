package com.mss;

public class App {
    
    public static void main(String[] args) {
        String identifier = Leitura.entDados("Digite o identificador: ");
        System.out.println(identifier);
 
        try {
            validarIdentificador(identifier);
            System.out.println("O identificador " + identifier + " é válido");
 
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public static void validarIdentificador(String identifier) {
 
        // Classe inválida (2): tamanho fora do intervalo [1, 6]
        if (identifier.length() < 1 || identifier.length() > 6) {
            throw new IllegalArgumentException(
                "Tamanho inválido: o identificador \"" + identifier +
                "\" possui " + identifier.length() +
                " caractere(s). Deve ter entre 1 e 6."
            );
        }
 
        // Classe inválida (4): primeiro caractere não é letra
        if (!Character.isLetter(identifier.charAt(0))) {
            throw new IllegalArgumentException(
                "Primeiro caractere inválido: o identificador \"" + identifier +
                "\" começa com '" + identifier.charAt(0) +
                "'. O primeiro caractere deve ser uma letra."
            );
        }
 
        // Classe inválida (6): contém caractere que não é letra nem dígito
        for (char c : identifier.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException(
                    "Caractere inválido: o identificador \"" + identifier +
                    "\" contém o caractere '" + c +
                    "'. Apenas letras e dígitos são permitidos."
                );
            }
        }
    }

}
