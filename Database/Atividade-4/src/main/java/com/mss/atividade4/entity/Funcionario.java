package com.mss.atividade4.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "funcionario")
@NamedQuery(
        name = "Funcionario.buscarPorQtdDependentes",
        query = "select f from Funcionario f where f.qtdDependentes = :qtdDependentes"
)
@NamedNativeQuery(
        name = "Funcionario.buscarPorNomeContendo",
        query = "select * from funcionario where nome like concat('%', :nome, '%')",
        resultClass = Funcionario.class
)
// Atividade Transação - questão 1: mapeamento da stored procedure que aumenta o
// salário de todos os funcionários em X por cento.
@NamedStoredProcedureQuery(
        name = "Funcionario.aumentarSalarios",
        procedureName = "aumentar_salario_funcionarios",
        parameters = {
                @StoredProcedureParameter(
                        mode = ParameterMode.IN,
                        name = "percentual",
                        type = Integer.class
                )
        }
)
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_func")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ColumnDefault("0")
    @Column(name = "qtd_dependentes", nullable = false)
    private Integer qtdDependentes;

    @Column(name = "salario", nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "cod_dept", nullable = false)
    private Departamento departamento;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdDependentes() {
        return qtdDependentes;
    }

    public void setQtdDependentes(Integer qtdDependentes) {
        this.qtdDependentes = qtdDependentes;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
