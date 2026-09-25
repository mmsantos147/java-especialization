# Atividade 1 — Validação de CPF via Sockets

Aplicação cliente/servidor com **Sockets TCP**: o cliente lê um CPF do teclado e o envia ao servidor, que verifica os dígitos verificadores e responde se o CPF é válido ou inválido.

## O que foi feito

- **`Servidor`** — abre um `ServerSocket` na porta efêmera **50000**, aguarda **uma** conexão, lê o CPF, valida, responde *"Este CPF é válido."* ou *"Este CPF é inválido."* e encerra a conexão. Cada etapa é impressa no console.
- **`Cliente`** — pede *"Digite um CPF para verificação:"*, conecta em **127.0.0.1:50000**, envia o CPF e exibe a resposta do servidor.
- **`ValidadorCPF`** — aceita o CPF com ou sem máscara (`.` e `-`), exige 11 dígitos, rejeita sequências repetidas (ex.: `222.222.222-22`) e calcula os dois dígitos verificadores (pesos 10→2 e 11→2, módulo 11).
- A comunicação usa `BufferedReader`/`PrintWriter` em **UTF-8**, e todos os recursos são fechados com *try-with-resources*.

## Como executar

Requer **Java 21+**. Em dois terminais, primeiro o servidor e depois o cliente:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=local.redes.Servidor   # terminal 1
mvn exec:java -Dexec.mainClass=local.redes.Cliente    # terminal 2
```
