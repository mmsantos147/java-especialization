package local.redes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Matheus Martins dos Santos
 */
public class Servidor {

    private static final int PORTA = 50000;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor aguardando conexões na porta: " + PORTA);

            try (Socket socket = serverSocket.accept();
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                out.flush();

                try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    Pessoa pessoa = (Pessoa) in.readObject();
                    System.out.println("Nome: " + pessoa.getNome());
                    System.out.println("Idade: " + pessoa.getIdade());

                    System.out.println("Dados recebidos corretamente!");
                    out.writeObject("Dados recebidos corretamente!");
                    out.flush();
                }
            }

            System.out.println("Conexão encerrada, desconectando...");
        }
    }
}
