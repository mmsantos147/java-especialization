# Atividade 4 — Exceções

Evolução do Sistema de Gestão de Veículos introduzindo **exceções verificadas customizadas** e a classe de armazenamento `BDVeiculos`.

## O que foi feito

- Criada a classe **`BDVeiculos`**, que centraliza o armazenamento dos veículos em dois vetores estáticos de 5 posições (`listaPasseio: Passeio[5]` e `listaCarga: Carga[5]`).
- Criadas duas exceções **verificadas** (estendem `Exception`):
  - **`VeicExistException`** — disparada ao tentar cadastrar um veículo com placa já existente; informa *"Já existe um veículo com esta placa"* e retorna ao menu;
  - **`VelocException`** — disparada quando a velocidade máxima informada é menor que 80 ou maior que 110 km/h; informa *"A velocidade máxima está fora dos limites brasileiros"*. Nesse caso o veículo assume o valor padrão: **100 km/h** para passeio e **90 km/h** para carga.
- Menu de console mantido (7 opções); a busca pela placa passou a avisar o usuário quando o veículo não é encontrado.
- Mantidos: interface `Calcular`, entrada de dados via `Leitura.entDados`, conversões de velocidade em `calcVel` e todas as restrições de herança/sobrescrita.

## Como executar

```bash
mvn compile
java -cp target/classes com.mss.Teste
```
