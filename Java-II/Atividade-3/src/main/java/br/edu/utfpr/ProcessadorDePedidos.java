package br.edu.utfpr;

import br.edu.utfpr.dominio.*;
import br.edu.utfpr.utilidades.LeitorDePedidos;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

import static br.edu.utfpr.utilidades.ServicosExternos.*;

public final class ProcessadorDePedidos {

    public ResultadoPedido processarPedido(Pedido pedido) {
        // TODO ver requisitos

        try (final var escopo = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())){
            final Subtask<Estoque> estoqueSubtask =
                   escopo.fork(() -> consultarEstoque(pedido.produto(), pedido.identificador()));

            final Subtask<Preco> precoSubtask =
                   escopo.fork(() -> consultarPreco(pedido.produto(), pedido.identificador()));

            escopo.join();

            final Estoque estoque = estoqueSubtask.get();
            final Preco preco = precoSubtask.get();

            if (estoque.quantidadeDisponivel() < pedido.quantidade()) {
                return new PedidoRejeitado(pedido.identificador(),
                        "estoque insuficiente disponivel: " + estoque.quantidadeDisponivel()
                                + ", solicitado " + pedido.quantidade());
            }

            final CotacaoFrete frete = cotarFrete(pedido.produto());

            final BigDecimal precoTotal = preco.valorUnitario()
                       .multiply(BigDecimal.valueOf(pedido.quantidade()))
                       .add(frete.valor());

            return new PedidoAprovado(pedido.identificador(), precoTotal, frete);

        } catch (StructuredTaskScope.FailedException e) {
            return new PedidoRejeitado(pedido.identificador(), e.getCause().getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new PedidoRejeitado(pedido.identificador(), "Processamento interrompido");
        }
    }

    private CotacaoFrete cotarFrete(String produto) throws InterruptedException {
        // TODO ver requisitos

        try (final var escopo = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.<CotacaoFrete>anySuccessfulResultOrThrow())) {

            escopo.fork(() -> cotarFreteTransportadoraUm(produto));
            escopo.fork(() -> cotarFreteTransportadoraDois(produto));

            return escopo.join();
        }
    }

    public Relatorio processarArquivo(Path arquivoEntrada) {
        // TODO ver requisitos
        final List<Pedido> pedidos = LeitorDePedidos.ler(arquivoEntrada);
        final List<ResultadoPedido> resultados;
        final List<PedidoAprovado> aprovados = new ArrayList<>();
        final List<PedidoRejeitado> rejeitados = new ArrayList<>();

        try (final var escopo = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.<ResultadoPedido>awaitAllSuccessfulOrThrow())) {

            final List<Subtask<ResultadoPedido>> subtarefas = pedidos.stream()
                    .map(pedido -> escopo.fork(() -> processarPedido(pedido)))
                    .toList();

            escopo.join();

            resultados = subtarefas.stream()
                    .map(Subtask::get)
                    .toList();

        } catch (final InterruptedException excecao) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Processamento interrompido", excecao);
        }

        for (final ResultadoPedido resultado : resultados) {
            switch (resultado) {
                case PedidoAprovado aprovado -> aprovados.add(aprovado);
                case PedidoRejeitado rejeitado -> rejeitados.add(rejeitado);
            }
        }

        return new Relatorio(aprovados, rejeitados);
    }
}
