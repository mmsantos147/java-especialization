package com.mss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrianguloTest {

    private Triangulo triangulo;

    @Before
    public void setUp() {
        triangulo = new Triangulo();
    }

    // Casos de teste da prova
    @Test
    public void CT01_escalenoValido() {
        assertTrue(triangulo.isTriangulo(3.0, 4.0, 5.0));
        assertEquals("escaleno", triangulo.tipoTriangulo(3.0, 4.0, 5.0));
    }

    @Test
    public void CT02_isoscelesValido_lado1IgualLado2() {
        assertTrue(triangulo.isTriangulo(5.0, 5.0, 3.0));
        assertEquals("isosceles", triangulo.tipoTriangulo(5.0, 5.0, 3.0));
    }

    @Test
    public void CT03_equilateroValido() {
        assertTrue(triangulo.isTriangulo(5.0, 5.0, 5.0));
        assertEquals("equilatero", triangulo.tipoTriangulo(5.0, 5.0, 5.0));
    }

    @Test
    public void CT04_isoscelesValido_lado1IgualLado3() {
        assertTrue(triangulo.isTriangulo(5.0, 3.0, 5.0));
        assertEquals("isosceles", triangulo.tipoTriangulo(5.0, 3.0, 5.0));
    }

    @Test
    public void CT05_isoscelesValido_lado2IgualLado3() {
        assertTrue(triangulo.isTriangulo(3.0, 5.0, 5.0));
        assertEquals("isosceles", triangulo.tipoTriangulo(3.0, 5.0, 5.0));
    }

    @Test
    public void CT06_ladoZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> triangulo.isTriangulo(0.0, 4.0, 5.0));
    }
    
    @Test
    public void CT07_ladoNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> triangulo.isTriangulo((-1.0), 4.0, 5.0));
    }
    
    @Test
    public void CT08_trianguloInvalido_somaDosladosIguais() {
        assertFalse(triangulo.isTriangulo(3.0, 4.0, 7.0));
    }

    @Test
    public void CT09_trianguloInvalido_somaDosladosIguais() {
        assertFalse(triangulo.isTriangulo(3.0, 7.0, 4.0));
    }

    @Test
    public void CT10_trianguloInvalido_somaDosladosIguais() {
        assertFalse(triangulo.isTriangulo(7.0, 3.0, 4.0));
    }

    @Test
    public void CT11_trianguloInvalido_somaLado1Lado2MenorQueLado3() {
        assertFalse(triangulo.isTriangulo(2.0, 3.0, 10.0));
    }

    @Test
    public void CT12_trianguloInvalido_somaLado1Lado3MenorQueLado2() {
        assertFalse(triangulo.isTriangulo(2.0, 10.0, 3.0));
    }

    @Test
    public void CT13_trianguloInvalido_somaLado2Lado3MenorQueLado1() {
        assertFalse(triangulo.isTriangulo(10.0, 2.0, 3.0));
    }

    @Test
    public void CT14_tresZerosLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> triangulo.isTriangulo(0.0, 0.0, 0.0));
    }

    // Casos de testes para atingir 100%
    @Test
    public void CT15_lado2ZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> triangulo.isTriangulo(4.0, 0.0, 5.0));
    }

    @Test
    public void CT16_lado3ZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> triangulo.isTriangulo(4.0, 5.0, 0.0));
    }

    @Test
    public void CT17_construtorGettersSetters() {
        Triangulo t = new Triangulo(3.0, 4.0, 5.0);
        assertEquals(Double.valueOf(3.0), t.getLado1());
        assertEquals(Double.valueOf(4.0), t.getLado2());
        assertEquals(Double.valueOf(5.0), t.getLado3());

        t.setLado1(6.0);
        t.setLado2(7.0);
        t.setLado3(8.0);
        assertEquals(Double.valueOf(6.0), t.getLado1());
        assertEquals(Double.valueOf(7.0), t.getLado2());
        assertEquals(Double.valueOf(8.0), t.getLado3());
    }
}