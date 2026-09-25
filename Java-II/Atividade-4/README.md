# Atividade 4 — Análise de Vendas com Streams

Leitura de um CSV com ~5.300 vendas (2010–2014) e implementação de 10 consultas sobre esses dados, exercitando **Streams, composição de `Predicate`, `Optional`, `Collectors` (`groupingBy`, `reducing`, `counting`, `teeing`), `Collector` customizado, `Gatherers` e `PriorityQueue`**.

## O que foi feito

- **Leitura do CSV** com **OpenCSV**: `VendaCsv` mapeia as colunas (separador `;`) e converte cada linha no record **`Venda`**. Os campos que não são texto usam conversores próprios:
  - `LocalDateConverter` — datas no formato `dd/MM/yyyy`;
  - `FormaPagamentoConverter` e `StatusConverter` — convertem o texto do CSV nos enums `FormaPagamento` e `Status`.
- **Consultas em `LeitorDeVendas`**:

| # | Método | Recurso utilizado |
|---|---|---|
| 1 | `vendasConcluidasAcimaDoValorNaRegiao` | composição de `Predicate` com `and` |
| 2 | `vendaDeMaiorValorNaRegiao` | `max` retornando `Optional`, sem `get()` |
| 3 | `topNVendasPorValor` | `PriorityQueue` limitada a N elementos (*min-heap*) |
| 4 | `top3VendedoresPorFaturamento` | `groupingBy` + `reducing`, ordenação com desempate por nome |
| 5 | `valorTotalDeVendasConcluidasPorRegiao` | `groupingBy` + `reducing` com `BigDecimal` |
| 6 | `resumoDeVendasConcluidas` | `Collector.of` com o acumulador `AcumuladorDeResumo` (total, média e quantidade) |
| 7 | `faturamentoAcumuladoPorVendedorEMes` | `Gatherers.scan` para a soma acumulada |
| 8 | `formaPagamentoComMaisCancelamentos` | `groupingBy` + `counting` |
| 9 | `diasEntrePrimeiraEUltimaVendaCancelada` | `Collectors.teeing` com `minBy` e `maxBy` |
| 10 | `quantidadeDeVendasConcluidasPorFormaDePagamentoAgrupadasPorAno` | `groupingBy` aninhado |

Os testes (`LeitorDeVendasTest` e `LocalDateConverterTest`) conferem os resultados de cada consulta contra `src/main/resources/vendas.csv`.

## Como executar

Requer **Java 25**.

```bash
mvn test
```
