# Atividade 6 — Interface Gráfica

Versão final do Sistema de Gestão de Veículos, agora com **interface gráfica Swing** construída manualmente (sem os editores visuais de IDE, conforme exigido pelo enunciado).

## O que foi feito

- A classe `Leitura` foi removida: toda a interação com o usuário passou a ser feita por **janelas** (`JOptionPane` para entradas e mensagens).
- A classe **`Teste`** constrói a interface via código:
  - **`JFrame`** principal com **`JMenuBar`** contendo as operações do sistema;
  - **`JTable`** (`DefaultTableModel`) para listar os veículos cadastrados;
  - diálogos de cadastro, busca e confirmação.
- Funcionalidades disponíveis no menu: cadastrar veículo de passeio/carga, buscar pela placa, excluir pela placa, **excluir todos** (de cada tipo) e sair.
- Tratamento de erros integrado à GUI: as exceções `VeicExistException` (placa duplicada) e `VelocException` (velocidade fora de 80–110 km/h, assumindo 100/90 km/h) são capturadas e exibidas em caixas de diálogo.
- Armazenamento mantido em `BDVeiculos` com `List`/`ArrayList`, além de todas as regras de herança, interface `Calcular` e conversões de velocidade das atividades anteriores.

## Como executar

```bash
mvn compile
java -cp target/classes com.mss.Teste
```
