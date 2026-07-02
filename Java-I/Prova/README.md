# Prova — Sistema Bancário

Prova da disciplina **Java I**: implementação de um sistema bancário a partir de um diagrama de classes, reunindo os principais conceitos vistos na disciplina — **classe abstrata, herança, interface, exceção verificada, encapsulamento e composição**.

## Modelo implementado

- **`ClienteBanco`** *(abstrata, implementa `Verifica`)* — atributos `numeroConta`, `nome` e `ender` (composição com `Endereco`).
  - `setNumeroConta(int)`: lança **`NumException`** se o valor for negativo;
  - `validar()` (da interface `Verifica`): imprime se o número da conta é **par ou ímpar**;
  - método abstrato `verifDoc()`.
- **`PessoaFisica`** *(final)* — atributo `cpf`; `verifDoc()` valida se o CPF está entre 10 e 20 ("CPF válido"/"CPF inválido").
- **`PessoaJuridica`** *(final)* — atributos `cnpj` e `responsavel` (composição com `PessoaFisica`); `verifDoc()` valida se o nome do responsável tem até 30 letras.
- **`Endereco`** — atributos `num` e `rua`.
- **`Verifica`** — interface com o método `validar()`.
- **`NumException`** — exceção verificada com o método `impMsg()`, que imprime *"ERRO: Não pode haver Número Negativo para conta!"*.
- **`TstConta`** — classe de teste: cadastra pessoas jurídicas (conta, CNPJ, rua, CPF e nome do responsável) seguindo a ordem de entrada/saída exigida no enunciado, exercitando os casos válidos e inválidos — incluindo número de conta negativo, cujo `catch` chama `impMsg()`.

Conforme a regra da prova, não foram criados construtores: os atributos são inicializados na própria declaração (numéricos com zero, literais com espaço em branco e objetos instanciados com seu tipo).

## Como executar

```bash
mvn compile
java -cp target/classes com.mss.TstConta
```
