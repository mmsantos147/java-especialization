package com.mss;

import java.util.ArrayList;
import java.util.List;

public class Teste {

    private static Passeio[] passeios = new Passeio[5];
    private static int contadorPasseio = 0;
    private static int contadorCarga = 0;
    private static Carga[] cargas = new Carga[5];
    private static Leitura leitura = new Leitura();

    public static void main(String[] args) {
        int opcao = 0;
        
        while (opcao!= 7) {
            System.out.println("\nSistema de Gestão de Veículos - Menu Inicial");
            System.out.println("1. Cadastrar Veículo de Passeio");
            System.out.println("2. Cadastrar Veículo de Carga");
            System.out.println("3. Imprimir Todos os Veículos de Passeio");
            System.out.println("4. Imprimir Todos os Veículos de Carga");
            System.out.println("5. Imprimir Veículo de Passeio pela Placa");
            System.out.println("6. Imprimir Veículo de Carga pela Placa");
            System.out.println("7. Sair do Sistema");

            opcao = Integer.parseInt(leitura.entDados("Escolha uma opção:"));

            switch (opcao) {
                case 1:
                    cadastrarPasseio();
                    break;
                
                case 2: 
                    cadastrarCarga();
                    break;
                case 3: 
                    mostrarPasseio();
                    break;
                case 4: 
                    mostrarCarga();
                    break;
                case 5:
                    buscarPasseioPorPlaca();
                    break;
                case 6:
                    buscarCargaPorPlaca();
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }

        Passeio veiculo1 = new Passeio();
        veiculo1.setPlaca("ABC1D23");
        veiculo1.setMarca("Toyota");
        veiculo1.setModelo("Corolla");
        veiculo1.setCor("Prata");
        veiculo1.setVelocMax(180.0f);
        veiculo1.setQtdRodas(4);
        veiculo1.getMotor().setQtdPist(4);
        veiculo1.getMotor().setPotencia(132);
        veiculo1.setQtdPassageiros(5);
                
        Carga carga1 = new Carga();
        carga1.setPlaca("BRA2A11");
        carga1.setMarca("Volvo");
        carga1.setModelo("FH 460");
        carga1.setCor("Branco");
        carga1.setVelocMax(130.0f);
        carga1.setQtdRodas(6);
        carga1.getMotor().setQtdPist(6);
        carga1.getMotor().setPotencia(460);
        carga1.setCargaMax(25000);
        carga1.setTara(8500);

        System.out.println("Veiculo de Passeio 1: Corolla\n" + veiculo1.toString() + "Quantidade de Passageiros: " + veiculo1.getQtdPassageiros() + "\n");
        
        System.out.println("Veiculo de Carga 1: FH 460\n" + carga1.toString() + "Carga Máxima: " + carga1.getCargaMax() + "\n" + "Tara: " + carga1.getTara() + "\n");
    }

    private static void cadastrarPasseio() {
        if (contadorPasseio >= passeios.length) {
            System.out.println("Não há espaço para mais veículos de passeio");
            return;
        }

        String placa = leitura.entDados("Digite a placa do Veículo: ");

        for(int i = 0; i < contadorPasseio; i++) {
            if (passeios[i].getPlaca().equals(placa)) {
                System.out.println("Erro: já existe veículo com essa placa");
                return;
            }
        }

        Passeio passeio = new Passeio(
            placa,
            leitura.entDados("Digite a marca do Veículo: "),
            leitura.entDados("Digite o modelo do Veículo: "),
            leitura.entDados("Digite a cor do Veículo: "),
            Float.parseFloat(leitura.entDados("Digite a velocMax do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a quantidade de rodas do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a quantidade de pistões do Motor do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a potencia do Motor do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a quantidade de passageiros do Veículo: "))
        );

        passeios[contadorPasseio] = passeio;
        contadorPasseio++;

        System.out.println("\nVeículo de passeio cadastrado com sucesso");
        System.out.println(passeio);

        String continuar = leitura.entDados("Deseja cadastrar outro veículo? (sim/não): ");
        if (!continuar.equalsIgnoreCase("sim")) {
            System.out.println("Voltando ao menu...");
        } else {
            cadastrarPasseio(); 
        }
    }
    
    private static void cadastrarCarga() {
        if (contadorCarga >= cargas.length) {
            System.out.println("Não há espaço para mais veículos de carga");
            return;
        }

        String placa = leitura.entDados("Digite a placa do Veículo: ");

        for(int i = 0; i < contadorCarga; i++) {
            if (cargas[i].getPlaca().equals(placa)) {
                System.out.println("Erro: já existe veículo com essa placa");
                return;
            }
        }

        Carga carga = new Carga(
            placa,
            leitura.entDados("Digite a marca do Veículo: "),
            leitura.entDados("Digite o modelo do Veículo: "),
            leitura.entDados("Digite a cor do Veículo: "),
            Float.parseFloat(leitura.entDados("Digite a velocMax do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a quantidade de rodas do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a quantidade de pistões do Motor do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a potencia do Motor do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a carga máxima do Veículo: ")),
            Integer.parseInt(leitura.entDados("Digite a tara do Veículo: "))
        );

        cargas[contadorCarga] = carga;
        contadorCarga++;

        System.out.println("\nVeículo de carga cadastrado com sucesso");
        System.out.println(carga);

        String continuar = leitura.entDados("Deseja cadastrar outro veículo? (sim/não): ");
        if (!continuar.equalsIgnoreCase("sim")) {
            System.out.println("Voltando ao menu...");
        } else {
            cadastrarCarga();
        }
    }

    private static void mostrarPasseio() {
        if (passeios.length == 0){
            System.out.println("Nenhum veículo cadastrado");
        } else {
            System.out.println("\n--- Lista de Veículos de Passeio ---");
            for(int i = 0; i < contadorPasseio; i++) {
                System.out.println(passeios[i]);
                System.out.println("----------------------");
            }
        }
    }

    private static void mostrarCarga() {
        if (passeios.length == 0){
            System.out.println("Nenhum veículo cadastrado");
        } else {
            System.out.println("\n--- Lista de Veículos de Carga ---");
            for(int i = 0; i < contadorCarga; i++) {
                System.out.println(cargas[i]);
                System.out.println("----------------------");
            }
        }
    }
    
    private static void buscarPasseioPorPlaca() {
        String placa = leitura.entDados("Digite a placa do Veículo: ");
        boolean encontrado = true;

        for(int i = 0; i < contadorPasseio; i++) {
            if (passeios[i].getPlaca().equals(placa)) {
                System.out.println("\nVeículo encontrado: ");
                System.out.println(passeios[i]);
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
        boolean encontrado = true;

        for(int i = 0; i < contadorCarga; i++) {
            if (cargas[i].getPlaca().equals(placa)) {
                System.out.println("\nVeículo encontrado: ");
                System.out.println(cargas[i]);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
    }
}