package br.edu.utfpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class CotadorDeMoedas {

    public Path verificarEntrada() {
        // TODO
        Path entrada = Path.of("entrada", "moedas.txt");
        if (!Files.exists(entrada.getParent())) {
            try {
                Files.createDirectories(entrada.getParent());
            } catch (IOException e) {
                System.err.println("Não foi possível criar o diretório de entrada '"
                        + entrada.getParent() + "': " + e.getMessage());
                System.exit(1);
            }
        }
        return entrada;
    }

    public Path verificarSaida() {
        // TODO
        Path saida = Path.of("saida", "cotacoes.csv");
        if (!Files.exists(saida.getParent())) {
            try {
                Files.createDirectories(saida.getParent());
            } catch (IOException e) {
                System.err.println("Não foi possível criar o diretório de saída '"
                        + saida.getParent() + "': " + e.getMessage());
                System.exit(1);
            }
        }
        return saida;
    }

    public List<String> lerArquivoDeMoedas(Path entrada) {
        // TODO
        
        List<String> moedas = List.of();
        try (Stream<String> linhas = Files.lines(entrada)) {
            moedas = linhas.map(String::trim)
                  .filter(s -> !s.isBlank())
                  .filter(s -> s.length() == 3)
                  .filter(s -> s.chars().allMatch(Character::isLetter))
                  .map(String::toUpperCase)
                  .toList();
        } catch (IOException e) {
            System.err.println("Não foi possível ler o arquivo de moedas '" + entrada
                    + "': " + e.getMessage());
        }
        return moedas;
    }

    public void cotarERegistrar(Path saida, List<String> moedas, ClienteCambio cliente) {
        // TODO
        if (moedas.isEmpty()) return;
        
        List<CompletableFuture<Optional<Cotacao>>> futuros = moedas.stream()
            .map(cliente::consultar)
            .toList();

        CompletableFuture
            .allOf(futuros.toArray(new CompletableFuture[0]))
            .join();

        List<Cotacao> cotacoes = futuros.stream()
            .map(CompletableFuture::join)
            .flatMap(Optional::stream)
            .toList();

        gravarEmCSV(saida, cotacoes);
    }

    private void gravarEmCSV(Path saida, List<Cotacao> cotacoes) {

        // TODO
        final String conteudo = cotacoes.stream()
            .map(c -> c.paraCsv())
            .reduce("moeda,valor,coletadoEm", (a, b) -> a + "\n" + b);

        try {
            Files.writeString(saida, conteudo);
        } catch (IOException e) {
            System.err.println("Não foi possível gravar as cotações em '"
                    + saida + "': " + e.getMessage());
            System.exit(1);
        }
    }
}
