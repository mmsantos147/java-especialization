# Atividade 1 — Modelo Motor/Veículo/Teste

Primeira atividade da disciplina: implementação de um modelo de classes simples a partir de um diagrama UML, exercitando **classes, objetos, encapsulamento e composição**.

## O que foi feito

- **`Motor`** — classe com os atributos privados `qtdPist` e `potencia`, expostos apenas por getters/setters.
- **`Veiculo`** — classe com os atributos `placa`, `marca`, `modelo`, `cor`, `velocMax` e `qtdRodas`, além de um atributo do tipo `Motor` (relação de composição: todo `Veiculo` **possui** um `Motor`).
- **`Teste`** — classe com o `main`, que instancia **5 veículos**, preenche todos os atributos via setters (inclusive os do motor, acessado somente através do objeto `Veiculo`, conforme as cardinalidades do diagrama) e imprime os dados na tela.
- Os construtores default inicializam atributos numéricos com `0` e literais com espaço em branco, como pedia o enunciado.

## Como executar

```bash
mvn compile
java -cp target/classes com.utfpr.Teste
```
