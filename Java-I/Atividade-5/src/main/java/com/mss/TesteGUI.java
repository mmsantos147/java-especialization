package com.mss;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TesteGUI {
    private static BDVeiculos bdVeiculos = new BDVeiculos();
    private static Leitura leitura = new Leitura();

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
        menuBar.add(menu);

        JMenuItem itemCadastrarPasseio = new JMenuItem("Cadastrar Veículo de Passeio");
        itemCadastrarPasseio.addActionListener(e -> {
            try {
                cadastrarPasseio();
            } catch (VeicExistException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (VelocException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        menu.add(itemCadastrarPasseio);

        JMenuItem itemCadastrarCarga = new JMenuItem("Cadastrar Veículo de Carga");
        itemCadastrarCarga.addActionListener(e -> {
            try {
                cadastrarCarga();
            } catch (VeicExistException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (VelocException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        menu.add(itemCadastrarCarga);

        JMenuItem itemMostrarPasseio = new JMenuItem("Mostrar Todos os Veículos de Passeio");
        itemMostrarPasseio.addActionListener(e -> mostrarPasseio());
        menu.add(itemMostrarPasseio);

        JMenuItem itemMostrarCarga = new JMenuItem("Mostrar Todos os Veículos de Carga");
        itemMostrarCarga.addActionListener(e -> mostrarPasseio());
        menu.add(itemMostrarCarga);

        JMenuItem itemBuscarPasseio = new JMenuItem("Buscar Veículo de Passeio");
        itemBuscarPasseio.addActionListener(e -> buscarPasseioPorPlaca());
        menu.add(itemBuscarPasseio);

        JMenuItem itemBuscarCarga = new JMenuItem("Buscar Veículo de Carga");
        itemBuscarCarga.addActionListener(e -> buscarPasseioPorPlaca());
        menu.add(itemBuscarCarga);

        JMenuItem itemExcluirPasseio = new JMenuItem("Excluir Veículo de Passeio");
        itemExcluirPasseio.addActionListener(e -> excluirPasseioPorPlaca());
        menu.add(itemExcluirPasseio);

        JMenuItem itemExcluirCarga = new JMenuItem("Excluir Veículo de Carga");
        itemExcluirCarga.addActionListener(e -> excluirPasseioPorPlaca());
        menu.add(itemExcluirCarga);

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menu.add(itemSair);

        frame.setJMenuBar(menuBar);
    }

    private static void createTable() {
        String[] columnNames = {"{Placa", "Marca", "Modelo", "Cor", "Velocidade Máxima", "Quantidade de Rodas", "Quantidade de Pistões", "Potencia", "Quantidade de Passageiros"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
    }

    private static void cadastrarPasseio() throws VeicExistException, VelocException {
        List<Passeio> passeios = bdVeiculos.getListaPasseio();

        String placa = leitura.entDados("Digite a placa do Veículo: ");

        for(Passeio passeio : passeios) {
            if (passeio != null) {
                if (passeio.getPlaca().equals(placa)) {
                    throw new VeicExistException("Já existe veículo com essa placa");
                }   
            }
        }

        String marca = leitura.entDados("Digite a marca do Veículo: ");
        String modelo = leitura.entDados("Digite o modelo do Veículo: ");
        String cor = leitura.entDados("Digite a cor do Veículo: ");
        float velocMax = Float.parseFloat(leitura.entDados("Digite a velocMax do Veículo: "));
        int qtdRodas = Integer.parseInt(leitura.entDados("Digite a quantidade de rodas do Veículo: "));
        int qtdPist = Integer.parseInt(leitura.entDados("Digite a quantidade de pistões do Motor do Veículo: "));
        int potencia = Integer.parseInt(leitura.entDados("Digite a potencia do Motor do Veículo: "));
        int qtdPassageiros = Integer.parseInt(leitura.entDados("Digite a quantidade de passageiros do Veículo: "));

        Passeio passeio;
        try {
            passeio = new Passeio(
                placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia, qtdPassageiros
            );
        } catch (VelocException e) {
            System.out.println("Aviso: " + e.getMessage() + " valor alterado para padrões nacionais");
            passeio = new Passeio(
                placa, marca, modelo, cor, 100, qtdRodas, qtdPist, potencia, qtdPassageiros
            );
        }
        
        passeios.add(passeio);

        System.out.println("\nVeículo de passeio cadastrado com sucesso");
        System.out.println(passeio);

        String continuar = leitura.entDados("Deseja cadastrar outro veículo? (sim/não): ");
        if (!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("s")) {
            System.out.println("Voltando ao menu...");
        } else {
            cadastrarPasseio(); 
        }
    }
    
    private static void cadastrarCarga() throws VeicExistException, VelocException {
        List<Carga> cargas = bdVeiculos.getListaCarga();
 

        String placa = leitura.entDados("Digite a placa do Veículo: ");

        for(Carga carga : cargas) {
            if (carga != null) {
                if (carga.getPlaca().equals(placa)) {
                    throw new VeicExistException("Já existe veículo com essa placa");
                }   
            }
        }

        String marca = leitura.entDados("Digite a marca do Veículo: ");
        String modelo = leitura.entDados("Digite o modelo do Veículo: ");
        String cor = leitura.entDados("Digite a cor do Veículo: ");
        float velocMax = Float.parseFloat(leitura.entDados("Digite a velocMax do Veículo: "));
        int qtdRodas = Integer.parseInt(leitura.entDados("Digite a quantidade de rodas do Veículo: "));
        int qtdPist = Integer.parseInt(leitura.entDados("Digite a quantidade de pistões do Motor do Veículo: "));
        int potencia = Integer.parseInt(leitura.entDados("Digite a potencia do Motor do Veículo: "));
        int cargaMax = Integer.parseInt(leitura.entDados("Digite a carga máxima do Veículo: "));
        int tara = Integer.parseInt(leitura.entDados("Digite a tara do Veículo: "));

        Carga carga;
        try {
            carga = new Carga(
                placa, marca, modelo, cor, velocMax, qtdRodas, qtdPist, potencia, cargaMax, tara
            );
        } catch (VelocException e) {
            System.out.println("Aviso: " + e.getMessage() + " valor alterado para padrões nacionais");
            carga = new Carga(
                placa, marca, modelo, cor, 90, qtdRodas, qtdPist, potencia, cargaMax, tara
            );
        }

        cargas.add(carga);

        System.out.println("\nVeículo de carga cadastrado com sucesso");
        System.out.println(carga);

        String continuar = leitura.entDados("Deseja cadastrar outro veículo? (sim/não): ");
        if (!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("s")) {
            System.out.println("Voltando ao menu...");
        } else {
            cadastrarCarga();
        }
    }

    private static void mostrarPasseio() {
        List<Passeio> passeios = bdVeiculos.getListaPasseio();
        if (passeios.isEmpty()){
            System.out.println("Nenhum veículo cadastrado");
        } else {
            System.out.println("\n--- Lista de Veículos de Passeio ---");
            for(Passeio passeio : passeios) {
                if (passeio != null) {
                    System.out.println(passeio);
                    System.out.println("----------------------");
                }
            }
        }
    }

    private static void mostrarCarga() {
        List<Carga> cargas = bdVeiculos.getListaCarga();
        if (cargas.isEmpty()){
            System.out.println("Nenhum veículo cadastrado");
        } else {
            System.out.println("\n--- Lista de Veículos de Carga ---");
            for(Carga carga : cargas) {
                if (carga != null) {
                    System.out.println(carga);
                    System.out.println("----------------------");
                }
            }
        }
    }
    
    private static void buscarPasseioPorPlaca() {
        String placa = leitura.entDados("Digite a placa do Veículo: ");
        boolean encontrado = false;

        for(Passeio passeio : bdVeiculos.getListaPasseio()) {
            if (passeio.getPlaca().equals(placa)) {
                System.out.println("\nVeículo encontrado: ");
                System.out.println(passeio);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
    }

    private static void buscarCargaPorPlaca() {
        String placa = leitura.entDados("Digite a placa do Veículo: ");
        boolean encontrado = false;

        for(Carga carga : bdVeiculos.getListaCarga()) {
            if (carga.getPlaca().equals(placa)) {
                System.out.println("\nVeículo encontrado: ");
                System.out.println(carga);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
    }

    private static void excluirPasseioPorPlaca() {
        String placa = leitura.entDados("Digite a placa do Veículo: ");
        boolean encontrado = false;

        for(Passeio passeio: bdVeiculos.getListaPasseio()){
            if(passeio.getPlaca().equals(placa)) {
                encontrado = true;
                System.out.println("\nVeículo excluido: ");
                System.out.println(passeio);
                bdVeiculos.getListaPasseio().remove(passeio);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
    }

    private static void excluirCargaPorPlaca() {
        String placa = leitura.entDados("Digite a placa do Veículo: ");
        boolean encontrado = false;

        for(Carga carga: bdVeiculos.getListaCarga()){
            if(carga.getPlaca().equals(placa)) {
                encontrado = true;
                System.out.println("\nVeículo excluido: ");
                System.out.println(carga);
                bdVeiculos.getListaCarga().remove(carga);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
    }
}
