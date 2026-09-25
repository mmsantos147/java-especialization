# Atividade 4 — Spring Data JPA: Consultas e Transações

Projeto **Spring Boot + Spring Data JPA** sobre o banco `empresa` (MariaDB), com as entidades `Departamento` e `Funcionario`. Reúne as atividades de **mapeamento JPA**, **consultas** (derivadas, JPQL, nativas e nomeadas) e **transações** (`@Transactional`, `@Modifying` e stored procedure).

## O que foi feito

- **Configuração manual** (`SpringDataConfig`): `DataSource` com HikariCP apontando para `jdbc:mariadb://localhost:3306/empresa`, `EntityManagerFactory` com Hibernate (`generateDdl = true`) e `JpaTransactionManager`.
- **Entidades**
  - `Departamento` — `cod_dept` e `nome`;
  - `Funcionario` — `cod_func`, `nome`, `qtd_dependentes` (padrão `0`), `salario`, `cargo` e relacionamento `@ManyToOne` com `Departamento`.
- **Consultas em `FuncionarioRepository`**
  - *query methods*: por nome e dependentes, maior salário (`findTop...`) e 3 maiores salários (`findTop3...`);
  - JPQL com `@Query`: por nome do departamento, sem dependentes ordenado por nome, salário maior que um valor;
  - SQL nativo: salário maior que um valor (`nativeQuery = true`);
  - `@NamedQuery` (por quantidade de dependentes) e `@NamedNativeQuery` (nome contendo um trecho).
- **Transações** (questões da atividade):
  1. **Aumentar o salário de todos em X%** via stored procedure `aumentar_salario_funcionarios`, mapeada com `@NamedStoredProcedureQuery` e chamada com `@Procedure`;
  2. **Funcionários sem dependentes de um departamento**, com parâmetros nomeados;
  3. **Trocar todos os funcionários de departamento** com `@Modifying` + `update` em JPQL;
  4. **Excluir os funcionários de um departamento** com `@Modifying` + `delete` em JPQL;
  5. **Salvar um departamento e um funcionário na mesma transação** (`DepartamentoService.salvarDepartamentoComFuncionario`).

## Como executar

Requer **Java 17+** e um **MariaDB** local com o banco `empresa`. As tabelas são criadas pelo Hibernate; a stored procedure precisa ser criada à mão:

```bash
mariadb -u root -p empresa < src/main/resources/procedure.sql
```

As credenciais vêm de variáveis de ambiente (`DB_USER` é opcional e usa `root` por padrão):

```bash
export DB_PASSWORD=<sua senha>
mvn spring-boot:run
```
