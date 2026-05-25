package com.mss;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FuncionarioTest {

    // --- DEV ---
    @Test
    public void deveAplicar20PorCentoParaDevComSalarioAcimaDe3000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 5000.0, Cargo.DESENVOLVEDOR);
        assertEquals(4000.0, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar10PorCentoParaDevComSalarioAbaixoDe3000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 2000.0, Cargo.DESENVOLVEDOR);
        assertEquals(1800.0, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar20PorCentoParaDevComSalarioIgualA3000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 3000.0, Cargo.DESENVOLVEDOR);
        assertEquals(2400.0, f.calcularSalarioLiquido(), 0.01);
    }

    // --- DBA ---

    @Test
    public void deveAplicar25PorcentoParaDbaComSalarioAcimaDe2000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 3000.00, Cargo.DBA);
        assertEquals(2250.00, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar15PorcentoParaDbaComSalarioAbaixoDe2000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 1500.00, Cargo.DBA);
        assertEquals(1275.00, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar25PorcentoParaDbaComSalarioIgualA2000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 2000.00, Cargo.DBA);
        assertEquals(1500.00, f.calcularSalarioLiquido(), 0.01);
    }

    //--- TESTADOR ---
    
    @Test
    public void deveAplicar25PorcentoParaTestadorComSalarioAcimaDe2000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 2500.00, Cargo.TESTADOR);
        assertEquals(1875.00, f.calcularSalarioLiquido(), 0.01);
    }
    
    @Test
    public void deveAplicar15PorcentoParaTestadorComSalarioAbaixoDe2000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 550.00, Cargo.TESTADOR);
        assertEquals(467.50, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar25PorcentoParaTestadorComSalarioigualA2000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 2000.00, Cargo.TESTADOR);
        assertEquals(1500.00, f.calcularSalarioLiquido(), 0.01);
    }

    //--- GERENTE ---

    @Test
    public void deveAplicar30PorcentoParaGerenteComSalarioAcimaDe5000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 6000.00, Cargo.GERENTE);
        assertEquals(4200.00, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar20PorcentoParaGerenteComSalarioAbaixoDe5000() {
        Funcionario f = new Funcionario("matheus", "matheus@email.com", 2500.00, Cargo.GERENTE);
        assertEquals(2000.00, f.calcularSalarioLiquido(), 0.01);
    }

    @Test
    public void deveAplicar30PorcentoParaGerenteComSalarioIgualA5000() {
        Funcionario f = new Funcionario("Matheus", "matheus@email.com", 5000.00, Cargo.GERENTE);
        assertEquals(3500.00, f.calcularSalarioLiquido(), 0.01);
    }
}
