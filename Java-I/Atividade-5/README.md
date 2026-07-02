# Atividade 5 — Collections

Evolução do Sistema de Gestão de Veículos trocando os vetores estáticos por **arrays dinâmicos (Collections)**.

## O que foi feito

- A classe **`BDVeiculos`** foi adaptada para usar `List`/`ArrayList` (`List<Passeio>` e `List<Carga>`), permitindo armazenar uma **quantidade indefinida** de veículos de cada tipo.
- O menu de console cresceu de 7 para **9 opções**, com as novas funcionalidades de **exclusão por placa** (veículo de passeio e veículo de carga); caso a placa não exista, o sistema avisa o usuário e retorna ao menu.
- Mantidas todas as regras das atividades anteriores:
  - exceções verificadas `VeicExistException` (placa duplicada) e `VelocException` (velocidade fora de 80–110 km/h, com valores padrão 100/90);
  - interface `Calcular` e conversões de velocidade em `calcVel`;
  - entrada de dados via `Leitura.entDados`;
  - `Veiculo` abstrata, filhas `final`, setters não sobrescritíveis.

## Como executar

```bash
mvn compile
java -cp target/classes com.mss.Teste
```
