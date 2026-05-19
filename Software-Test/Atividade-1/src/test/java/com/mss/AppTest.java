package com.mss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    
    /** Classes (1,3,5) — válido */
    @Test
    void testValido() {
        assertDoesNotThrow(() -> App.validarIdentificador("a1"),
                "\"a1\" é válido e não deve lançar exceção");
    }
 
    /** Classe (2) — inválido, tamanho maior que 6 */
    @Test
    void testTamanhoExcedido() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> App.validarIdentificador("A1b2C3d")
        );
        assertTrue(ex.getMessage().contains("Tamanho inválido"),
                "Mensagem deve indicar que o tamanho é inválido");
    }
    
    /** Classe (4) — inválido, primeiro caractere é dígito */
    @Test
    void testPrimeiroCharInvalido() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> App.validarIdentificador("2B3")
        );
        assertTrue(ex.getMessage().contains("Primeiro caractere inválido"),
                "Mensagem deve indicar que o primeiro caractere é inválido");
    }
 
    /** Classe (6) — inválido, contém caractere especial */
    @Test
    void testCaractereEspecial() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> App.validarIdentificador("Z-12")
        );
        assertTrue(ex.getMessage().contains("Caractere inválido"),
                "Mensagem deve indicar que há um caractere inválido");
    }

}
