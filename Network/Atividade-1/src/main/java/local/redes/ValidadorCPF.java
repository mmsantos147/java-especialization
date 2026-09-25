package local.redes;

import java.util.stream.IntStream;

/**
 * @author Matheus Martins dos Santos
 */
public class ValidadorCPF {

    private static final int TAMANHO_CPF = 11;

    private ValidadorCPF() {
    }

    public static boolean ehValido(String cpf) {
        if (cpf == null) return false;
        cpf = apenasDigitos(cpf);
        if (temFormatoValido(cpf)) {
            return digitosVerificadoresConferem(cpf);
        }
        
        return false;
    }

    private static String apenasDigitos(String cpf) {
        return cpf.replace(".", "").replace("-","").trim();
    }

    private static boolean temFormatoValido(String cpf) {
        if (cpf.length() != TAMANHO_CPF) return false;
        if (!cpf.chars().allMatch(Character::isDigit)) return false;
        if (cpf.chars().allMatch(c -> c == cpf.charAt(0))) return false;
        return true;
    }

    private static boolean digitosVerificadoresConferem(String cpf) {
        int verificador1 = calcularDigito(cpf, 9, 10);
        String base = cpf.substring(0, 9) + verificador1;
        int verificador2 = calcularDigito(base, 10, 11);

        return (base + verificador2).equals(cpf);
    }

    private static int calcularDigito(String cpf, int quantidade, int pesoInicial) {
        int resultado = IntStream.range(0, quantidade)
            .map(i -> Character.getNumericValue(cpf.charAt(i)) * (pesoInicial-i)) 
            .sum() % 11;

        return resultado < 2 ? 0 : 11 - resultado;
    }
}
