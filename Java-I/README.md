# Java I

Atividades desenvolvidas na disciplina **Java I** da Pós-Graduação em Tecnologia Java (UTFPR).

A disciplina cobre os fundamentos da Orientação a Objetos em Java: classes e objetos, encapsulamento, composição, herança, classes abstratas, interfaces, exceções, Collections e interface gráfica (Swing). As atividades evoluem de forma incremental sobre um mesmo domínio — um **Sistema de Gestão de Veículos** — e a prova aplica os mesmos conceitos em um sistema bancário.

## Estrutura

| Pasta | Enunciado do curso | Conceitos trabalhados |
|---|---|---|
| [Atividade-1](./Atividade-1) | Modelo Motor/Veículo/Teste | Classes, objetos, encapsulamento, composição |
| [Atividade-2](./Atividade-2) | Herança | Herança, classe abstrata, polimorfismo, `final` |
| [Atividade-3](./Atividade-3) | Interface | Interfaces, menu interativo em console |
| [Atividade-4](./Atividade-4) | Exceções | Exceções verificadas customizadas |
| [Atividade-5](./Atividade-5) | Collections | `List`/`ArrayList`, exclusão por placa |
| [Atividade-6](./Atividade-6) | Interface Gráfica | Swing (JFrame, JTable, JOptionPane) |
| [Prova](./Prova) | Prova — Sistema Bancário | Todos os conceitos da disciplina menos GUI|

## Como executar

Todos os projetos usam **Maven** e **Java 17+**. Em cada pasta:

```bash
mvn compile
java -cp target/classes com.mss.Teste   # Atividade-1: com.utfpr.Teste | Prova: com.mss.TstConta
```
