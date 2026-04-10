package com.mss;

public class Teste {

    public static void main(String[] args) {
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
                
        Passeio veiculo2 = new Passeio();
        veiculo2.setPlaca("XYZ9E87");
        veiculo2.setMarca("Ford");
        veiculo2.setModelo("Mustang");
        veiculo2.setCor("Vermelho");
        veiculo2.setVelocMax(250.0f);
        veiculo2.setQtdRodas(4);
        veiculo2.getMotor().setQtdPist(8);
        veiculo2.getMotor().setPotencia(450);
        veiculo2.setQtdPassageiros(2);

        Passeio veiculo3 = new Passeio();
        veiculo3.setPlaca("QWE4F56");
        veiculo3.setMarca("Honda");
        veiculo3.setModelo("Civic");
        veiculo3.setCor("Preto");
        veiculo3.setVelocMax(195.0f);
        veiculo3.setQtdRodas(4);
        veiculo3.getMotor().setQtdPist(4);
        veiculo3.getMotor().setPotencia(158);
        veiculo3.setQtdPassageiros(5);
        
        Passeio veiculo4 = new Passeio();
        veiculo4.setPlaca("RTY7G12");
        veiculo4.setMarca("Chevrolet");
        veiculo4.setModelo("Onix");
        veiculo4.setCor("Branco");
        veiculo4.setVelocMax(180.0f);
        veiculo4.setQtdRodas(4);
        veiculo4.getMotor().setQtdPist(3);
        veiculo4.getMotor().setPotencia(116);
        veiculo4.setQtdPassageiros(5);

        Passeio veiculo5 = new Passeio();
        veiculo5.setPlaca("POI3H44");
        veiculo5.setMarca("BMW");
        veiculo5.setModelo("M3");
        veiculo5.setCor("Azul");
        veiculo5.setVelocMax(290.0f);
        veiculo5.setQtdRodas(4);
        veiculo5.getMotor().setQtdPist(6);
        veiculo5.getMotor().setPotencia(510);
        veiculo5.setQtdPassageiros(5);

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

        Carga carga2 = new Carga();
        carga2.setPlaca("MER5B22");
        carga2.setMarca("Mercedes-Benz");
        carga2.setModelo("Actros 2651");
        carga2.setCor("Prata");
        carga2.setVelocMax(120.0f);
        carga2.setQtdRodas(10);
        carga2.getMotor().setQtdPist(6);
        carga2.getMotor().setPotencia(510);
        carga2.setCargaMax(30000);
        carga2.setTara(9200);

        Carga carga3 = new Carga();
        carga3.setPlaca("SCN8C33");
        carga3.setMarca("Scania");
        carga3.setModelo("R 450");
        carga3.setCor("Vermelho");
        carga3.setVelocMax(125.0f);
        carga3.setQtdRodas(6);
        carga3.getMotor().setQtdPist(6);
        carga3.getMotor().setPotencia(450);
        carga3.setCargaMax(27000);
        carga3.setTara(8800);

        Carga carga4 = new Carga();
        carga4.setPlaca("IVE3D44");
        carga4.setMarca("Iveco");
        carga4.setModelo("Tector 240E28");
        carga4.setCor("Azul");
        carga4.setVelocMax(110.0f);
        carga4.setQtdRodas(6);
        carga4.getMotor().setQtdPist(4);
        carga4.getMotor().setPotencia(280);
        carga4.setCargaMax(16000);
        carga4.setTara(6500);

        Carga carga5 = new Carga();
        carga5.setPlaca("FRD6E55");
        carga5.setMarca("Ford");
        carga5.setModelo("Cargo 2429");
        carga5.setCor("Amarelo");
        carga5.setVelocMax(115.0f);
        carga5.setQtdRodas(6);
        carga5.getMotor().setQtdPist(6);
        carga5.getMotor().setPotencia(290);
        carga5.setCargaMax(18000);
        carga5.setTara(7000);

        System.out.println("Veiculo de Passeio 1: Corolla\n" + veiculo1.toString() + "Quantidade de Passageiros: " + veiculo1.getQtdPassageiros() + "\n");
        System.out.println("Veiculo de Passeio 2: Mustang\n" + veiculo2.toString() + "Quantidade de Passageiros: " + veiculo2.getQtdPassageiros() + "\n");
        System.out.println("Veiculo de Passeio 3: Civic\n" + veiculo3.toString() + "Quantidade de Passageiros: " + veiculo3.getQtdPassageiros() + "\n");
        System.out.println("Veiculo de Passeio 4: Onix\n" + veiculo4.toString() + "Quantidade de Passageiros: " + veiculo4.getQtdPassageiros() + "\n");
        System.out.println("Veiculo de Passeio 5: M3\n" + veiculo5.toString() + "Quantidade de Passageiros: " + veiculo5.getQtdPassageiros() + "\n");
    
        System.out.println("Veiculo de Carga 1: FH 460\n" + carga1.toString() + "Carga Máxima: " + carga1.getCargaMax() + "\n" + "Tara: " + carga1.getTara() + "\n");
        System.out.println("Veiculo de Carga 2: Actros 2651\n" + carga2.toString() + "Carga Máxima: " + carga2.getCargaMax() + "\n" + "Tara: " + carga2.getTara() + "\n");
        System.out.println("Veiculo de Carga 3: R 450\n" + carga3.toString() + "Carga Máxima: " + carga3.getCargaMax() + "\n" + "Tara: " + carga3.getTara() + "\n");
        System.out.println("Veiculo de Carga 4: Tector 240E28\n" + carga4.toString() + "Carga Máxima: " + carga4.getCargaMax() + "\n" + "Tara: " + carga4.getTara() + "\n");
        System.out.println("Veiculo de Carga 5: Cargo 2429\n" + carga5.toString() + "Carga Máxima: " + carga5.getCargaMax() + "\n" + "Tara: " + carga5.getTara() + "\n");
    }
}