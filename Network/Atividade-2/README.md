# Atividade 2 — Envio de objeto `Pessoa` via Sockets (Swing)

> Corresponde ao enunciado **Atividade 03** do curso.

Aplicação cliente/servidor com **Sockets TCP** e **serialização de objetos**: o cliente, uma janela Swing, envia um objeto `Pessoa` (nome e idade) ao servidor, que exibe os dados no console e responde confirmando o recebimento.

## O que foi feito

- **`Pessoa`** — classe `Serializable` com os atributos `nome` e `idade`, getters e setters.
- **`Servidor`** — roda em modo console, abre um `ServerSocket` na porta efêmera **50000**, aguarda **uma** conexão, lê o objeto `Pessoa` com `ObjectInputStream`, imprime nome e idade, responde *"Dados recebidos corretamente!"* e encerra a conexão. Não usa threads, conforme o enunciado.
- **`FormCliente`** — `JFrame` com campos **Nome** e **Idade**, uma `JTextArea` de *Retorno do Servidor* e o botão **Enviar** (layout com `GridBagLayout`). Ao clicar, conecta em **127.0.0.1:50000**, envia a `Pessoa` e mostra a resposta na área de texto. Idade não numérica e falhas de conexão são informadas na própria `JTextArea`.
- Os `ObjectOutputStream` são criados e "flushados" antes dos `ObjectInputStream` dos dois lados, evitando o *deadlock* na troca de cabeçalhos da serialização. Os recursos são fechados com *try-with-resources*.

## Como executar

Requer **Java 21+**. Em dois terminais, primeiro o servidor e depois o cliente:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=local.redes.Servidor      # terminal 1
mvn exec:java -Dexec.mainClass=local.redes.FormCliente   # terminal 2
```

O servidor atende um único envio e termina; para enviar de novo, inicie-o outra vez.
