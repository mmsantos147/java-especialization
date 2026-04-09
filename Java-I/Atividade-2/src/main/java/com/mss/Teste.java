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

        System.out.println("Veiculo 1: Corolla\n" + veiculo1.toString() + "Quantidade de Passageiros " + veiculo1.getQtdPassageiros() + "\n");
        System.out.println("Veiculo 2: Mustang\n" + veiculo2.toString() + "Quantidade de Passageiros " + veiculo2.getQtdPassageiros() + "\n");
        System.out.println("Veiculo 3: Civic\n" + veiculo3.toString() + "Quantidade de Passageiros " + veiculo3.getQtdPassageiros() + "\n");
        System.out.println("Veiculo 4: Onix\n" + veiculo4.toString() + "Quantidade de Passageiros " + veiculo4.getQtdPassageiros() + "\n");
        System.out.println("Veiculo 5: M3\n" + veiculo5.toString() + "Quantidade de Passageiros " + veiculo5.getQtdPassageiros() + "\n");
    }
}