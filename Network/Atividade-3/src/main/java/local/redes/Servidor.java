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

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor aguardando conexões na porta: " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(new TratadorCliente(socket)).start();
            }
        }
    }

    private static class TratadorCliente implements Runnable {

        private final Socket socket;

        TratadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (socket;
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                out.flush();

                try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    Pessoa pessoa = (Pessoa) in.readObject();
                    System.out.println("Nome: " + pessoa.getNome());
                    System.out.println("Idade: " + pessoa.getIdade());

                    out.writeObject("Dados recebidos corretamente!");
                    out.flush();
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao atender cliente: " + e.getMessage());
            }
        }
    }
}
