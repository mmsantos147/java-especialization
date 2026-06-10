package com.mss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class MyRandomNumberTest {

    private MyRandomNumber mrn;

    @BeforeEach
    public void setUp() {
        mrn = new MyRandomNumber();
    }

    @Test
    public void testBeginNegativoLancaExcecao() {
        assertThrows(IntervaloInvalidoException.class, () -> mrn.nextRandomNumber(-1, 10));
    }

    @Test
    public void testEndNegativoLancaExcecao() {
        assertThrows(IntervaloInvalidoException.class, () -> mrn.nextRandomNumber(0, -1));
    }

    @Test
    public void testBeginMaiorQueEndLancaExcecao() {
        assertThrows(IntervaloInvalidoException.class, () -> mrn.nextRandomNumber(10, 5));
    }

    @Test
    public void testBeginIgualEndLancaExcecao() {
        assertThrows(IntervaloInvalidoException.class, () -> mrn.nextRandomNumber(5, 5));
    }

    @Test
    public void testRetornaNumeroNoIntervalo() throws IntervaloInvalidoException {
        int begin = 1, end = 10;
        int resultado = mrn.nextRandomNumber(begin, end);
        assertTrue(resultado >= begin && resultado <= end);
    }

    @Test
    public void testRetornaNumeroNoIntervaloComZero() throws IntervaloInvalidoException {
        int begin = 0, end = 100;
        int resultado = mrn.nextRandomNumber(begin, end);
        assertTrue(resultado >= begin && resultado <= end);
    }

    @Test
    public void testNaoRepeteNumeroAnteriorComMockito() throws IntervaloInvalidoException {
        Random mockRandom = Mockito.mock(Random.class);
        mrn.random = mockRandom;

        when(mockRandom.nextInt(anyInt())).thenReturn(3, 3, 5);

        int primeiro = mrn.nextRandomNumber(1, 10);
        assertEquals(4, primeiro);

        int segundo = mrn.nextRandomNumber(1, 10);
        assertNotEquals(primeiro, segundo);
        assertEquals(6, segundo);
    }

    @Test
    public void testRetryComIntervaloMinimo() throws IntervaloInvalidoException {
        Random mockRandom = Mockito.mock(Random.class);
        mrn.random = mockRandom;

        when(mockRandom.nextInt(anyInt())).thenReturn(0, 0, 1);

        int primeiro = mrn.nextRandomNumber(0, 1);
        assertEquals(0, primeiro);

        int segundo = mrn.nextRandomNumber(0, 1);
        assertNotEquals(primeiro, segundo);
        assertEquals(1, segundo);
    }
}
