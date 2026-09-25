package com.mss.atividade4.service;

import com.mss.atividade4.entity.Departamento;
import com.mss.atividade4.entity.Funcionario;
import com.mss.atividade4.repository.DepartamentoRepository;
import com.mss.atividade4.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    // Atividade Transação - Questões 3 e 4
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Funcionario> listarTodosFuncionarios() {
        return repository.findAll();
    }

    public Funcionario listarFuncionarioPeloNomeEDependentes(String nome, Integer qtdDependentes) {
        return repository.findFuncionarioByNomeAndQtdDependentes(nome, qtdDependentes);
    }

    public List<Funcionario> listarPorDepartamento(String nomeDepartamento) {
        return repository.buscaPorDepartamento(nomeDepartamento);
    }

    public Funcionario listarFuncionarioComMaiorSalario() {
        return repository.findTopByOrderBySalarioDesc();
    }

    public List<Funcionario> listarTresMaioresSalarios() {
        return repository.findTop3ByOrderBySalarioDesc();
    }

    public List<Funcionario> listarSemDependentesOrdenadoPorNome() {
        return repository.buscarSemDependentesOrdenadoPorNome();
    }

    public List<Funcionario> listarPorSalarioMaiorQue(BigDecimal salario) {
        return repository.findBySalarioGreaterThan(salario);
    }

    public List<Funcionario> listarPorSalarioMaiorQueNative(BigDecimal salario) {
        return repository.buscarPorSalarioMaiorNative(salario);
    }

    public List<Funcionario> listarPorQtdDependentes(Integer qtdDependentes) {
        return repository.buscarPorQtdDependentes(qtdDependentes);
    }

    public List<Funcionario> listarPorNomeContendo(String nome) {
        return repository.buscarPorNomeContendo(nome);
    }

    // Atividade Transação - Questão 1
    @Transactional
    public void aumentarSalarioDeTodosFuncionarios(Integer percentual) {
        repository.aumentarSalarioDeTodosFuncionarios(percentual);
    }

    // Atividade Transação - Questão 2
    // departamento que não possuam dependentes (parâmetros nomeados).
    public List<Funcionario> listarSemDependentesPorDepartamento(String nomeDepartamento) {
        return repository.buscarSemDependentesPorDepartamento(nomeDepartamento, 0);
    }

    // Atividade Transação - Questão 3
    @Transactional
    public int trocarFuncionariosDeDepartamento(Long codDepartamentoAtual, Long codNovoDepartamento) {
        Departamento departamentoAtual = departamentoRepository.findById(codDepartamentoAtual).orElseThrow();
        Departamento novoDepartamento = departamentoRepository.findById(codNovoDepartamento).orElseThrow();

        return repository.trocarFuncionariosDeDepartamento(departamentoAtual, novoDepartamento);
    }

    // Atividade Transação - Questão 4
    @Transactional
    public int excluirFuncionariosDoDepartamento(Long codDepartamento) {
        Departamento departamento = departamentoRepository.findById(codDepartamento).orElseThrow();

        return repository.excluirFuncionariosDoDepartamento(departamento);
    }
}
