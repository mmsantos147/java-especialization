package com.mss;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class Teste {
    private static BDVeiculos bdVeiculos = new BDVeiculos();

    private static JFrame frame;
    private static JTable table;
    private static DefaultTableModel tableModel;

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Cadastro de Veículos");
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            createMenu();
            createTable();
            frame.setVisible(true);
        });
    }

    private static void createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("SansSerif", Font.BOLD, 14));
        menu.setOpaque(true);
        menu.setBackground(new Color(30, 30, 60));
        menuBar.add(menu);

        JMenuItem itemCadastrarPasseio = new JMenuItem("Cadastrar Veículo de Passeio");
        itemCadastrarPasseio.addActionListener(e -> menuCadastroPasseio());
        menu.add(itemCadastrarPasseio);

        JMenuItem itemCadastrarCarga = new JMenuItem("Cadastrar Veículo de Carga");
        itemCadastrarCarga.addActionListener(e -> menuCadastroCarga());
        menu.add(itemCadastrarCarga);

        JMenuItem itemBuscarPasseio = new JMenuItem("Buscar Veículo de Passeio");
        itemBuscarPasseio.addActionListener(e -> buscarPasseioPorPlaca());
        menu.add(itemBuscarPasseio);

        JMenuItem itemBuscarCarga = new JMenuItem("Buscar Veículo de Carga");
        itemBuscarCarga.addActionListener(e -> buscarCargaPorPlaca());
        menu.add(itemBuscarCarga);

        JMenuItem itemExcluirPasseio = new JMenuItem("Excluir Veículo de Passeio por Placa");
        itemExcluirPasseio.addActionListener(e -> excluirPasseioPorPlaca());
        menu.add(itemExcluirPasseio);

        JMenuItem itemExcluirCarga = new JMenuItem("Excluir Veículo de Carga por Placa");
        itemExcluirCarga.addActionListener(e -> excluirCargaPorPlaca());
        menu.add(itemExcluirCarga);

        JMenuItem itemExcluirTodosPasseio = new JMenuItem("Excluir Todos os Veículos de Passeio");
        itemExcluirTodosPasseio.addActionListener(e -> {
            bdVeiculos.getListaPasseio().clear();
            updateTable();
            JOptionPane.showMessageDialog(frame, "Todos os veículos de passeio foram excluídos.");
        });

        menu.add(itemExcluirTodosPasseio);
        
        JMenuItem itemExcluirTodosCarga = new JMenuItem("Excluir Todos os Veículos de Carga");
        itemExcluirTodosCarga.addActionListener(e -> {
            bdVeiculos.getListaCarga().clear();
            updateTable();
            JOptionPane.showMessageDialog(frame, "Todos os veículos de Carga foram excluídos.");
        });
        menu.add(itemExcluirTodosCarga);        

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menu.add(itemSair);

        frame.setJMenuBar(menuBar);
    }

    private static void createTable() {
        JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnPasseio = new JButton("Veículos de Passeio");
        JButton btnCarga = new JButton("Veículos de Carga");

        btnPasseio.addActionListener(e -> mostrarTabela("passeio"));
        btnCarga.addActionListener(e -> mostrarTabela("carga"));

        switchPanel.add(btnPasseio);
        switchPanel.add(btnCarga);
        frame.add(switchPanel, BorderLayout.NORTH);

        String[] columnNames = {"Placa", "Marca", "Modelo", "Cor", "Velocidade Máxima", 
                                "Qtd. Rodas", "Qtd. Pistões", "Potência", "Passageiros/Carga Máx."};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private static String tabelaAtual = "passeio";

    private static void mostrarTabela(String tipo) {
        tabelaAtual = tipo;
        tableModel.setRowCount(0);

        if (tipo.equals("passeio")) {
            tableModel.setColumnIdentifiers(new String[]{
                "Placa", "Marca", "Modelo", "Cor", "Velocidade Máxima",
                "Qtd. Rodas", "Qtd. Pistões", "Potência", "Qtd. Passageiros"
            });
            for (Passeio p : bdVeiculos.getListaPasseio()) {
                tableModel.addRow(new String[]{
                    p.getPlaca(), p.getMarca(), p.getModelo(), p.getCor(),
                    String.valueOf(p.getVelocMax()), String.valueOf(p.getQtdRodas()),
                    String.valueOf(p.getMotor().getQtdPist()), String.valueOf(p.getMotor().getPotencia()),
                    String.valueOf(p.getQtdPassageiros())
                });
            }
        } else {
            tableModel.setColumnIdentifiers(new String[]{
                "Placa", "Marca", "Modelo", "Cor", "Velocidade Máxima",
                "Qtd. Rodas", "Qtd. Pistões", "Potência", "Carga Máx.", "Tara"
            });
            for (Carga c : bdVeiculos.getListaCarga()) {
                tableModel.addRow(new String[]{
                    c.getPlaca(), c.getMarca(), c.getModelo(), c.getCor(),
                    String.valueOf(c.getVelocMax()), String.valueOf(c.getQtdRodas()),
                    String.valueOf(c.getMotor().getQtdPist()), String.valueOf(c.getMotor().getPotencia()),
                    String.valueOf(c.getCargaMax()), String.valueOf(c.getTara())
                });
            }
        }
    }

    private static void menuCadastroPasseio() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JTextField placaField = new JTextField(20);
        JTextField marcaField = new JTextField(20);
        JTextField modeloField = new JTextField(20);
        JTextField corField = new JTextField(20);
        JTextField velocMaxField = new JTextField(20);
        JTextField qtdRodasField = new JTextField(20);
        JTextField qtdPistField = new JTextField(20);
        JTextField potenciaField = new JTextField(20);
        JTextField qtdPassageiroField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Placa:"), gbc);
        gbc.gridx = 1;
        panel.add(placaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        panel.add(marcaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        panel.add(modeloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Cor:"), gbc);
        gbc.gridx = 1;
        panel.add(corField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Velocidade Máxima:"), gbc);
        gbc.gridx = 1;
        panel.add(velocMaxField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Quantidade de Rodas:"), gbc);
        gbc.gridx = 1;
        panel.add(qtdRodasField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Quantidade de Pistões:"), gbc);
        gbc.gridx = 1;
        panel.add(qtdPistField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Potência:"), gbc);
        gbc.gridx = 1;
        panel.add(potenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Quantidade de Passageiros:"), gbc);
        gbc.gridx = 1;
        panel.add(qtdPassageiroField, gbc);

        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> {
            String placa = placaField.getText();
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            String cor = corField.getText();
            float velocMax = Float.parseFloat(velocMaxField.getText());
            int qtdRodas = Integer.parseInt(qtdRodasField.getText());
            int qtdPist = Integer.parseInt(qtdPistField.getText());
            int potencia = Integer.parseInt(potenciaField.getText());
            int qtdPassageiros = Integer.parseInt(qtdPassageiroField.getText());

            List<Passeio> passeios = bdVeiculos.getListaPasseio();
            for(Passeio passeio : passeios) {
                if (passeio != null) {
                    if (passeio.getPlaca().equals(placa)) {
                        JOptionPane.showMessageDialog(frame, "Já existe veículo com essa placa");
                        return;
                    }   
                }
            }

            try {
                Passeio passeio = new Passeio(
                    placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia, qtdPassageiros
                );
                bdVeiculos.getListaPasseio().add(passeio);
                JOptionPane.showMessageDialog(frame, "Veículo de passeio cadastrado com sucesso!");

                updateTable();
                limparCamposPasseio(placaField, marcaField, modeloField, corField, velocMaxField, qtdRodasField, qtdPistField, potenciaField, qtdPassageiroField);
            } catch (VelocException ex) {
                JOptionPane.showMessageDialog(frame, "Aviso: Velocilade além do limite nacional, valor alterado para padrões nacionais");
                Passeio passeio;
                try {
                    passeio = new Passeio(
                        placa, marca, modelo, cor, 100, qtdRodas, qtdPist, potencia, qtdPassageiros
                    );
                    bdVeiculos.getListaPasseio().add(passeio);
                JOptionPane.showMessageDialog(frame, "Veículo de passeio cadastrado com sucesso!");

                updateTable();
                limparCamposPasseio(placaField, marcaField, modeloField, corField, velocMaxField, qtdRodasField, qtdPistField, potenciaField, qtdPassageiroField);
                } catch (VelocException ignored) {
                }
            }

        });
        buttonPanel.add(cadastrarButton);

        JButton limparButton = new JButton("Limpar");
        limparButton.addActionListener(e -> {
            limparCamposPasseio(placaField, marcaField, modeloField, corField, velocMaxField, qtdRodasField, qtdPistField, potenciaField, qtdPassageiroField);
        });
        buttonPanel.add(limparButton);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        JDialog dialog = new JDialog(frame, "Cadastro de Veículo de Passeio", true);
        dialog.setContentPane(panel);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(fecharButton);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);    
    }

    private static void limparCamposPasseio(JTextField placaField, JTextField marcaField, JTextField modeloField, JTextField corField, JTextField velocMaxField, JTextField qtdRodasField, JTextField qtdPistField, JTextField potenciaField, JTextField qtdPassageiroField) {
        placaField.setText("");
        marcaField.setText("");
        modeloField.setText("");
        corField.setText("");
        velocMaxField.setText("");
        qtdRodasField.setText("");
        qtdPistField.setText("");
        potenciaField.setText("");
        qtdPassageiroField.setText("");    
    }

    private static void updateTable() {
        mostrarTabela(tabelaAtual);
    }
   
    
    private static void menuCadastroCarga() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        JTextField placaField = new JTextField(20);
        JTextField marcaField = new JTextField(20);
        JTextField modeloField = new JTextField(20);
        JTextField corField = new JTextField(20);
        JTextField velocMaxField = new JTextField(20);
        JTextField qtdRodasField = new JTextField(20);
        JTextField qtdPistField = new JTextField(20);
        JTextField potenciaField = new JTextField(20);
        JTextField cargaMaxField = new JTextField(20);
        JTextField taraField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Placa:"), gbc);
        gbc.gridx = 1;
        panel.add(placaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        panel.add(marcaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        panel.add(modeloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Cor:"), gbc);
        gbc.gridx = 1;
        panel.add(corField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Velocidade Máxima:"), gbc);
        gbc.gridx = 1;
        panel.add(velocMaxField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Quantidade de Rodas:"), gbc);
        gbc.gridx = 1;
        panel.add(qtdRodasField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Quantidade de Pistões:"), gbc);
        gbc.gridx = 1;
        panel.add(qtdPistField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Potência:"), gbc);
        gbc.gridx = 1;
        panel.add(potenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Carga máxima: "), gbc);
        gbc.gridx = 1;
        panel.add(cargaMaxField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Tara: "), gbc);
        gbc.gridx = 1;
        panel.add(taraField, gbc);

        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> {
            String placa = placaField.getText();
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            String cor = corField.getText();
            float velocMax = Float.parseFloat(velocMaxField.getText());
            int qtdRodas = Integer.parseInt(qtdRodasField.getText());
            int qtdPist = Integer.parseInt(qtdPistField.getText());
            int potencia = Integer.parseInt(potenciaField.getText());
            int cargaMax = Integer.parseInt(cargaMaxField.getText());
            int tara = Integer.parseInt(taraField.getText());

            List<Carga> cargas = bdVeiculos.getListaCarga();
            for(Carga carga : cargas) {
                if (carga != null) {
                    if (carga.getPlaca().equals(placa)) {
                        JOptionPane.showMessageDialog(frame, "Já existe veículo com essa placa");
                        return;
                    }   
                }
            }

            try {
                    Carga carga = new Carga(
                        placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia, cargaMax, tara
                    );
                    bdVeiculos.getListaCarga().add(carga);
                    JOptionPane.showMessageDialog(frame, "Veículo de passeio cadastrado com sucesso!");
                
                    updateTable();
                    limparCamposCarga(placaField, marcaField, modeloField, corField, velocMaxField, qtdRodasField, qtdPistField, potenciaField, cargaMaxField, taraField);
                } catch (VelocException ex) {
                JOptionPane.showMessageDialog(frame, "Aviso: Velocilade além do limite nacional, valor alterado para padrões nacionais");
                Carga carga;
                try {
                    carga = new Carga(
                        placa, marca, modelo, cor, 90, qtdRodas, qtdPist, potencia, cargaMax, tara
                    );
                    bdVeiculos.getListaCarga().add(carga);
                    JOptionPane.showMessageDialog(frame, "Veículo de passeio cadastrado com sucesso!");
                
                    updateTable();
                    limparCamposCarga(placaField, marcaField, modeloField, corField, velocMaxField, qtdRodasField, qtdPistField, potenciaField, cargaMaxField, taraField);
                } catch (VelocException ignored) {
                }
            }

        });
        buttonPanel.add(cadastrarButton);

        JButton limparButton = new JButton("Limpar");
        limparButton.addActionListener(e -> {
            limparCamposCarga(placaField, marcaField, modeloField, corField, velocMaxField, qtdRodasField, qtdPistField, potenciaField, cargaMaxField, taraField);
        });
        buttonPanel.add(limparButton);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        JDialog dialog = new JDialog(frame, "Cadastro de Veículo de Passeio", true);
        dialog.setContentPane(panel);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(fecharButton);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);    
    }

    private static void limparCamposCarga(JTextField placaField, JTextField marcaField, JTextField modeloField,
            JTextField corField, JTextField velocMaxField, JTextField qtdRodasField, JTextField qtdPistField,
            JTextField potenciaField, JTextField cargaMaxField, JTextField taraField) {
        placaField.setText("");
        marcaField.setText("");
        modeloField.setText("");
        corField.setText("");
        velocMaxField.setText("");
        qtdRodasField.setText("");
        qtdPistField.setText("");
        potenciaField.setText("");
        cargaMaxField.setText(""); 
        taraField.setText("");
    }
    
    private static void buscarPasseioPorPlaca() {
        String placa = JOptionPane.showInputDialog(frame, "Digite a placa do veículo: ");

        if (placa != null && !placa.trim().isEmpty()){
            for(Passeio passeio : bdVeiculos.getListaPasseio()) {
                if (passeio.getPlaca().equals(placa)) {
                    String passeioData = "Placa: " + passeio.getPlaca() + "\n" +
                        "Marca: " + passeio.getMarca() + "\n" +
                        "Modelo: " + passeio.getModelo() + "\n" + 
                        "Cor: " + passeio.getCor() + "\n" +
                        "Velocidade máxima: " + passeio.getVelocMax() + "\n" +
                        "Quantidade de rodas: " + passeio.getQtdRodas() + "\n" +
                        "Quantidade de pistões do motor: " + passeio.getMotor().getQtdPist() + "\n" +
                        "Potência do motor: " + passeio.getMotor().getPotencia() + "\n" +
                        "Quantidade de passageiros: " + passeio.getQtdPassageiros() + "\n" +
                        "Velocidade Máxima Convertida: " + passeio.calcVel(passeio.getVelocMax()) + "\n" +
                        "Calculo da quantidade de letras nos atributos Strings: " + passeio.calcular() + "\n";
                    JOptionPane.showMessageDialog(frame, passeioData);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Veículo não encontrado.");   
        }
    }

    private static void buscarCargaPorPlaca() {
        String placa = JOptionPane.showInputDialog(frame, "Digite a placa do veículo: ");

        if (placa != null && !placa.trim().isEmpty()){
            for(Carga carga : bdVeiculos.getListaCarga()) {
                if (carga.getPlaca().equals(placa)) {
                    String cargaData = "Placa: " + carga.getPlaca() + "\n" +
                        "Marca: " + carga.getMarca() + "\n" +
                        "Modelo: " + carga.getModelo() + "\n" + 
                        "Cor: " + carga.getCor() + "\n" +
                        "Velocidade máxima: " + carga.getVelocMax() + "\n" +
                        "Quantidade de rodas: " + carga.getQtdRodas() + "\n" +
                        "Quantidade de pistões do motor: " + carga.getMotor().getQtdPist() + "\n" +
                        "Potência do motor: " + carga.getMotor().getPotencia() + "\n" +
                        "Carga máxima do veículo: " + carga.getCargaMax() + "\n" +
                        "Tara do veículo: " + carga.getTara() + "\n" +
                        "Velocidade Máxima Convertida: " + carga.calcVel(carga.getVelocMax()) + "\n" +
                        "Calculo dos atributos numéricos: " + carga.calcular() + "\n";
                    JOptionPane.showMessageDialog(frame, cargaData);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Veículo não encontrado.");   
        }
    }

    private static void excluirPasseioPorPlaca() {
        String placa = JOptionPane.showInputDialog(frame, "Digite a placa do veículo: ");

        if (placa != null && !placa.trim().isEmpty()){
            for(Passeio passeio : bdVeiculos.getListaPasseio()) {
                if (passeio.getPlaca().equals(placa)) {
                    bdVeiculos.getListaPasseio().remove(passeio);
                    JOptionPane.showMessageDialog(frame, "Veículo excluído com sucesso!");
                    updateTable();
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Veículo não encontrado.");   
        }
    }

    private static void excluirCargaPorPlaca() {
        String placa = JOptionPane.showInputDialog(frame, "Digite a placa do veículo: ");

        if (placa != null && !placa.trim().isEmpty()){
            for(Carga carga : bdVeiculos.getListaCarga()) {
                if (carga.getPlaca().equals(placa)) {
                    bdVeiculos.getListaCarga().remove(carga);
                    JOptionPane.showMessageDialog(frame, "Veículo excluído com sucesso!");
                    updateTable();
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Veículo não encontrado.");   
        }
    }
}