# Atividade 3 — Servidor multi-cliente com Threads

> Corresponde ao enunciado **Atividade 04** do curso, continuação da [Atividade 2](../Atividade-2).

Mesma aplicação da atividade anterior — cliente Swing enviando um objeto `Pessoa` (nome e idade) ao servidor via **Sockets TCP** —, agora com o servidor atendendo **várias conexões** usando **Threads**, sem ser encerrado após o primeiro cliente.

## O que foi feito

- **`Pessoa`** e **`FormCliente`** — iguais aos da Atividade 2: objeto `Serializable` e janela com campos **Nome**, **Idade**, área *Retorno do Servidor* e botão **Enviar**, conectando em **127.0.0.1:50000**.
- **`Servidor`** — abre o `ServerSocket` na porta **50000** e fica em um laço `while (true)` bloqueado em `accept()`. Cada conexão aceita é entregue a uma nova `Thread` com o `Runnable` **`TratadorCliente`**, liberando o servidor para aguardar o próximo cliente imediatamente.
- **`TratadorCliente`** (classe interna) — lê a `Pessoa`, imprime nome e idade no console, responde *"Dados recebidos corretamente!"* e fecha apenas o socket daquele cliente. Erros de comunicação são tratados dentro da thread, sem derrubar o servidor.

## Como executar

Requer **Java 21+**. Inicie o servidor e depois quantos clientes quiser:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=local.redes.Servidor      # terminal 1 (fica rodando)
mvn exec:java -Dexec.mainClass=local.redes.FormCliente   # terminal 2, 3, ...
```

Cada janela de cliente pode enviar várias vezes. Para parar o servidor, use `Ctrl+C`.
