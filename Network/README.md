# Java Redes

Atividades desenvolvidas na disciplina **Java Redes** (CETEJ33) da Pós-Graduação em Tecnologia Java (UTFPR).

A disciplina trata da comunicação em rede com Java usando **Sockets TCP** (`ServerSocket`/`Socket`) no modelo cliente/servidor. As atividades evoluem da troca de texto pelo console para o envio de objetos serializados a partir de uma interface Swing e, por fim, para um servidor concorrente com Threads. Todas usam o pacote `local.redes`, o IP **127.0.0.1** e a porta efêmera **50000**.

## Estrutura

| Pasta | Enunciado do curso | Conceitos trabalhados |
|---|---|---|
| [Atividade-1](./Atividade-1) | Atividade 01 — Validação de CPF | Sockets TCP, streams de texto, validação de CPF |
| [Atividade-2](./Atividade-2) | Atividade 03 — Objeto `Pessoa` via Swing | Serialização (`ObjectInput/OutputStream`), cliente Swing |
| [Atividade-3](./Atividade-3) | Atividade 04 — Servidor com Threads | `Thread`/`Runnable`, servidor multi-cliente |

## Como executar

Todos os projetos usam **Maven** e **Java 21+**. Em cada pasta, inicie primeiro o servidor e depois o cliente, em terminais separados:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=local.redes.Servidor
mvn exec:java -Dexec.mainClass=local.redes.Cliente       # Atividade-1
mvn exec:java -Dexec.mainClass=local.redes.FormCliente   # Atividade-2 e 3
```
