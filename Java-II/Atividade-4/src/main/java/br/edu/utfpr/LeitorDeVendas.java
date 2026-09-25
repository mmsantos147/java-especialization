package br.edu.utfpr;

import br.edu.utfpr.model.FormaPagamento;
import br.edu.utfpr.model.RankingVendedor;
import br.edu.utfpr.model.ResumoVendas;
import br.edu.utfpr.model.Venda;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;

public class LeitorDeVendas {

    private final List<Venda> vendas;

    public LeitorDeVendas(String salesFile) {

        final var stream = getClass().getClassLoader().getResourceAsStream(salesFile);

        if (stream == null) {
            throw new IllegalStateException("Arquivo não encontrado");
        }

        final var builder = new CsvToBeanBuilder<VendaCsv>(new InputStreamReader(stream, StandardCharsets.UTF_8));

        vendas = builder
                .withType(VendaCsv.class)
                .withSeparator(';')
                .build()
                .parse()
                .stream()
                .map(VendaCsv::toVenda)
                .toList();
    }

    public List<Venda> vendasConcluidasAcimaDoValorNaRegiao(BigDecimal valorMinimo, String regiao) {
        // TODO apenas operações funcionais com streams
        Predicate<Venda> naRegiao = venda -> venda.regiao().equals(regiao);
        Predicate<Venda> minimoValor = venda -> venda.valor().compareTo(valorMinimo) > 0;
        Predicate<Venda> concluida = Venda::isConcluida;
        Predicate<Venda> concluidaMinimoValorNaRegiao = concluida.and(minimoValor).and(naRegiao);

        return vendas.stream().filter(concluidaMinimoValorNaRegiao).toList();
    }

    public Optional<Venda> vendaDeMaiorValorNaRegiao(String regiao) {
        // TODO apenas operações funcionais com streams
        Predicate<Venda> naRegiao = venda -> venda.regiao().equals(regiao);
        return vendas.stream().filter(naRegiao).max(Comparator.comparing(Venda::valor));
    }

    public List<Venda> topNVendasPorValor(int n) {
        // TODO utilizar PriorityQueue
        Comparator<Venda> porValor = Comparator.comparing(Venda::valor);
        PriorityQueue<Venda> topVendas = new PriorityQueue<>(porValor);
        for (Venda v : vendas) {
            topVendas.offer(v);
            if (topVendas.size() > n) topVendas.poll();
        }
        List<Venda> result = new ArrayList<>(topVendas);
        result.sort(porValor.reversed());
        return result;
    }

    public List<RankingVendedor> top3VendedoresPorFaturamento() {
        // TODO apenas operações funcionais com streams
        Comparator<RankingVendedor> porFaturamentoVendedor = Comparator.comparing(RankingVendedor::faturamento).reversed()
                .thenComparing(RankingVendedor::vendedor);
        return vendas.stream().filter(Venda::isConcluida)
                .collect(Collectors.groupingBy(Venda::vendedor,
                        Collectors.reducing(BigDecimal.ZERO, Venda::valor, BigDecimal::add)))
                .entrySet().stream().map(entry -> new RankingVendedor(entry.getKey(),entry.getValue()))
                .sorted(porFaturamentoVendedor).limit(3).toList();
    }

    public Map<String, BigDecimal> valorTotalDeVendasConcluidasPorRegiao() {
        // TODO apenas operações funcionais com streams
        return vendas.stream().filter(Venda::isConcluida)
                .collect(Collectors.groupingBy(Venda::regiao,
                        Collectors.reducing(BigDecimal.ZERO, Venda::valor, BigDecimal::add)));
    }

    public ResumoVendas resumoDeVendasConcluidas() {
        // TODO apenas operações funcionais com streams, usar collector customizado, use a classe AcumuladorDeResumo
        final Collector<BigDecimal, AcumuladorDeResumo, ResumoVendas> resumoDasVendas = Collector.of(
                AcumuladorDeResumo::new,
                AcumuladorDeResumo::somar,
                AcumuladorDeResumo::combinar,
                AcumuladorDeResumo::finalizar
        );

        return vendas.stream().filter(Venda::isConcluida).map(Venda::valor).collect(resumoDasVendas);
    }

    public List<BigDecimal> faturamentoAcumuladoPorVendedorEMes(String vendedor, YearMonth mes) {
        // TODO apenas operações funcionais com streams, use Gatherers
        return vendas.stream().filter(Venda::isConcluida)
                .filter(venda -> venda.vendedor().equals(vendedor))
                .filter(venda -> YearMonth.from(venda.dataVenda()).equals(mes))
                .sorted(Comparator.comparing(Venda::dataVenda))
                .map(Venda::valor)
                .gather(Gatherers.scan(() -> BigDecimal.ZERO, BigDecimal::add))
                .toList();
    }

    public FormaPagamento formaPagamentoComMaisCancelamentos() {
        // TODO apenas operações funcionais com streams, usar groupingBy + counting
        return vendas.stream().filter(Venda::isCancelada)
                .collect(Collectors.groupingBy(Venda::formaPagamento, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElseThrow();
    }

    public long diasEntrePrimeiraEUltimaVendaCancelada() {
        // TODO apenas operações funcionais com streams, usar Collectors.teeing com minBy e maxBy

        return vendas.stream().filter(Venda::isCancelada).collect(Collectors.teeing(
                Collectors.minBy(Comparator.comparing(Venda::dataVenda)),
                Collectors.maxBy(Comparator.comparing(Venda::dataVenda)),
                (dataMinima, dataMaxima) ->
                        ChronoUnit.DAYS.between(dataMinima.orElseThrow().dataVenda(), dataMaxima.orElseThrow().dataVenda())
        ));
    }

    public Map<Integer, Map<FormaPagamento, Long>> quantidadeDeVendasConcluidasPorFormaDePagamentoAgrupadasPorAno() {
        // TODO apenas operações funcionais com streams, usar groupingBy aninhado
        return vendas.stream().filter(Venda::isConcluida)
                .collect(Collectors.groupingBy(venda -> venda.dataVenda().getYear(), Collectors.groupingBy(Venda::formaPagamento, Collectors.counting())));
    }

    private static final class AcumuladorDeResumo {

        private BigDecimal total = BigDecimal.ZERO;
        private long quantidade = 0;

        private void somar(BigDecimal valor) {
            total = total.add(valor);
            quantidade++;
        }

        private AcumuladorDeResumo combinar(AcumuladorDeResumo outro) {
            total = total.add(outro.total);
            quantidade += outro.quantidade;
            return this;
        }

        private ResumoVendas finalizar() {
            final var media = quantidade == 0
                    ? BigDecimal.ZERO
                    : total.divide(BigDecimal.valueOf(quantidade), 2, RoundingMode.HALF_UP);
            return new ResumoVendas(total, media, quantidade);
        }
    }
}
