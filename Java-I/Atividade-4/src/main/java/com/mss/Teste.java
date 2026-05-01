package com.mss;

public class Teste {

    private static int contadorPasseio = 0;
    private static int contadorCarga = 0;
    private static BDVeiculos bdVeiculos = new BDVeiculos();
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
    }

    private static void cadastrarPasseio() {
        Passeio[] passeios = bdVeiculos.getListaPasseio();
        if (contadorPasseio >= passeios.length) {
            System.out.println("Não há espaço para mais veículos de passeio");
            return;
        }

        String placa = leitura.entDados("Digite a placa do Veículo: ");

        for(Passeio passeio : passeios) {
            if (passeio != null) {
                if (passeio.getPlaca().equals(placa)) {
                    System.out.println("Erro: já existe veículo com essa placa");
                    return;
                }   
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

        for(int i = 0; i < passeios.length; i++) {
            if (passeios[i] == null) {
                passeios[i] = passeio;
                break;
            }
        }

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
        Carga[] cargas = bdVeiculos.getListaCarga();
        if (contadorCarga >= cargas.length) {
            System.out.println("Não há espaço para mais veículos de carga");
            return;
        }

        String placa = leitura.entDados("Digite a placa do Veículo: ");

        for(Carga carga : cargas) {
            if (carga != null) {
                if (carga.getPlaca().equals(placa)) {
                    System.out.println("Erro: já existe veículo com essa placa");
                    return;
                }   
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

        for(int i = 0; i < cargas.length; i++) {
            if (cargas[i] == null) {
                cargas[i] = carga;
                break;
            }
        }

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
        Passeio[] passeios = bdVeiculos.getListaPasseio();
        if (passeios.length == 0){
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
        Carga[] cargas = bdVeiculos.getListaCarga();
        if (cargas.length == 0){
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
        boolean encontrado = true;

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
}