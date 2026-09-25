# Atividade 3 — Processador de Pedidos com Structured Concurrency

Processamento concorrente de pedidos consultando serviços externos simulados (estoque, preço e duas transportadoras), usando **Structured Concurrency (`StructuredTaskScope`)**, **virtual threads**, **sealed interfaces**, **records** e **pattern matching em `switch`**.

## O que foi feito

- **`ProcessadorDePedidos.processarPedido`**
  - consulta **estoque e preço em paralelo** em um escopo com `Joiner.awaitAllSuccessfulOrThrow()`. Cada serviço leva 200 ms, então o pedido fica pronto em ~200 ms e não em ~400 ms;
  - se qualquer consulta falhar (produto inexistente ou erro simulado), o escopo cancela a outra e o pedido vira **`PedidoRejeitado`** com o motivo da exceção;
  - com estoque insuficiente, rejeita informando a quantidade disponível e a solicitada;
  - caso contrário, calcula `preço unitário × quantidade + frete` e retorna **`PedidoAprovado`**.
- **`cotarFrete`** — consulta as duas transportadoras em paralelo com `Joiner.anySuccessfulResultOrThrow()` e fica com a **primeira que responder**, cancelando a mais lenta.
- **`processarArquivo`** — lê os pedidos (`LeitorDePedidos`), processa todos concorrentemente (um *fork* por pedido) e separa aprovados e rejeitados em um **`Relatorio`** usando `switch` com pattern matching sobre a interface selada `ResultadoPedido`.
- **Domínio** (`dominio/`): `Pedido`, `Estoque`, `Preco`, `CotacaoFrete`, `PedidoAprovado`, `PedidoRejeitado` e `Relatorio` são records; `ResultadoPedido` é `sealed` e só permite os dois resultados.
- **`ServicosExternos`** *(fornecido)* simula latência e falhas: identificadores terminados em número par sempre falham, e o `TECLADO` está sem estoque.

Os 9 testes verificam aprovação, os três tipos de rejeição, o cálculo do valor total, a escolha do frete mais rápido, os tempos de resposta (< 350 ms para estoque + preço, < 700 ms com o frete) e o processamento de um arquivo completo.

## Como executar

Requer **Java 25**. `StructuredTaskScope` ainda é *preview* nessa versão, e o `pom.xml` já habilita `--enable-preview` na compilação e nos testes.

```bash
mvn test
```
