# Atividade 2 — Herança

Evolução do modelo Motor/Veículo introduzindo **herança, classe abstrata, polimorfismo e o modificador `final`**.

## O que foi feito

- **`Veiculo`** passou a ser uma classe **abstrata**, com o método abstrato `calcVel(float velocMax)`.
- Criadas as subclasses:
  - **`Passeio`** (`qtdPassageiros`) — sobrescreve `calcVel` convertendo a velocidade de km/h para **m/h** (× 1.000);
  - **`Carga`** (`cargaMax`, `tara`) — sobrescreve `calcVel` convertendo de km/h para **cm/h** (× 100.000).
- O método `calcVel` apenas converte e retorna o valor, sem alterar o atributo `velocMax`, como exigido.
- Regras restritivas do enunciado garantidas via `final`:
  - `Passeio` e `Carga` são classes `final` (não podem ser estendidas);
  - os setters não podem ser sobrescritos.
- **`Teste`** instancia **5 veículos de cada tipo** (Passeio/Carga), preenche os dados via setters e imprime tudo na tela, incluindo a velocidade convertida de cada veículo.

## Como executar

```bash
mvn compile
java -cp target/classes com.mss.Teste
```
