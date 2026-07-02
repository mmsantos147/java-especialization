# Atividade 3 — Interface

Evolução do sistema de veículos introduzindo **interfaces** e transformando a classe `Teste` em um **menu interativo de console** (sem GUI).

## O que foi feito

- Criada a interface **`Calcular`**, com o método `calcular(): int`, implementada pelas duas classes filhas:
  - em **`Passeio`**: retorna a soma das quantidades de letras de todos os atributos `String`;
  - em **`Carga`**: retorna a soma de todos os atributos numéricos.
- Criada a classe **`Leitura`**, com o método `entDados(String)` (encapsula um `Scanner`), usada para toda a entrada de dados na classe `Teste`.
- A classe **`Teste`** ganhou o *Sistema de Gestão de Veículos* com menu inicial de 7 opções: cadastrar veículo de passeio/carga, imprimir todos, buscar pela placa e sair.
- Armazenamento em **2 vetores de 5 posições** (um para Passeio, outro para Carga); ao cadastrar, a placa é verificada **nos dois vetores** para impedir duplicidade.
- Após cada operação o sistema retorna ao menu inicial; ao final de cada cadastro é perguntado se deseja cadastrar outro veículo do mesmo tipo.
- Mantidas as restrições das atividades anteriores: `Veiculo` abstrata (não instanciável), filhas `final`, setters não sobrescritíveis.

## Como executar

```bash
mvn compile
java -cp target/classes com.mss.Teste
```
