package com.mss.atividade4.repository;

import com.mss.atividade4.entity.Departamento;
import com.mss.atividade4.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findFuncionarioByNomeAndQtdDependentes(String nome, Integer qtdDependentes);

    @Query("select f from Funcionario f where f.departamento.nome = :nomeDepartamento")
    List<Funcionario> buscaPorDepartamento(@Param("nomeDepartamento") String nomeDepartamento);

    Funcionario findTopByOrderBySalarioDesc();

    List<Funcionario> findTop3ByOrderBySalarioDesc();

    @Query("select f from Funcionario f where f.qtdDependentes = 0 order by f.nome asc")
    List<Funcionario> buscarSemDependentesOrdenadoPorNome();

    @Query("select f from Funcionario f where f.salario > :salario")
    List<Funcionario> findBySalarioGreaterThan(@Param("salario") BigDecimal salario);

    @Query(value = "select * from funcionario where salario > ?1", nativeQuery = true)
    List<Funcionario> buscarPorSalarioMaiorNative(BigDecimal salario);

    List<Funcionario> buscarPorQtdDependentes(@Param("qtdDependentes") Integer qtdDependentes);

    List<Funcionario> buscarPorNomeContendo(@Param("nome") String nome);

    // Atividade Transação - Questão 1
    @Procedure(name = "Funcionario.aumentarSalarios")
    void aumentarSalarioDeTodosFuncionarios(@Param("percentual") Integer percentual);

    // Atividade Transação - Questão 2
    @Query("select f from Funcionario f "
            + "where f.departamento.nome = :nomeDepartamento "
            + "and f.qtdDependentes = :qtdDependentes")
    List<Funcionario> buscarSemDependentesPorDepartamento(
            @Param("nomeDepartamento") String nomeDepartamento,
            @Param("qtdDependentes") Integer qtdDependentes);

    // Atividade Transação - Questão 3
    @Modifying
    @Query("update Funcionario f set f.departamento = :novoDepartamento "
            + "where f.departamento = :departamentoAtual")
    int trocarFuncionariosDeDepartamento(
            @Param("departamentoAtual") Departamento departamentoAtual,
            @Param("novoDepartamento") Departamento novoDepartamento);

    // Atividade Transação - Questão 4
    @Modifying
    @Query("delete from Funcionario f where f.departamento = :departamento")
    int excluirFuncionariosDoDepartamento(@Param("departamento") Departamento departamento);

}
