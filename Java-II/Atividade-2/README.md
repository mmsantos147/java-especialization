# Atividade 2 — Cotador de Moedas

Programa que lê uma lista de moedas de um arquivo, consulta a cotação de cada uma em relação ao dólar na API pública [Frankfurter](https://frankfurter.dev) e grava o resultado em CSV, exercitando **NIO.2 (`Files`/`Path`), `HttpClient` assíncrono, `CompletableFuture`, `Optional` e records**.

## O que foi feito

- **`CotadorDeMoedas`**
  - `verificarEntrada()` / `verificarSaida()`: retornam `entrada/moedas.txt` e `saida/cotacoes.csv`, criando os diretórios quando não existem;
  - `lerArquivoDeMoedas()`: lê o arquivo com `Files.lines`, aplica `trim`, descarta linhas vazias e códigos que não têm exatamente **3 letras** e normaliza para maiúsculas;
  - `cotarERegistrar()`: dispara todas as consultas em paralelo, aguarda com `CompletableFuture.allOf(...)` e descarta as que falharam (`Optional::stream`). Com a lista vazia, não faz nada — nem cria o arquivo.
- **`ClienteCambio`** — monta a requisição `GET` e usa `HttpClient.sendAsync`; qualquer erro é tratado em `exceptionally`, registrado no `stderr` e convertido em `Optional.empty()`, sem interromper as demais consultas.
- **`Cotacao`** — record `(moeda, valor, coletadoEm)` com o método `paraCsv()`.
- **`JsonParser`** — extrai a taxa da resposta JSON (`{"rates":{"BRL":5.43}}`) sem bibliotecas externas.
- **Testes** (JUnit 5 + AssertJ + Mockito): cobrem o filtro das moedas, arquivo inexistente, falha parcial na consulta (as demais continuam sendo gravadas) e lista vazia (o `ClienteCambio` mockado não recebe nenhuma chamada).

Arquivo de saída gerado:

```csv
moeda,valor,coletadoEm
BRL,5.43,2026-01-30T12:00:00
EUR,0.92,2026-01-30T12:00:00
```

## Como executar

Requer **Java 25** e acesso à internet. As moedas ficam em `entrada/moedas.txt`, uma por linha.

```bash
mvn compile
java -cp target/classes br.edu.utfpr.Main   # gera saida/cotacoes.csv
mvn test
```
