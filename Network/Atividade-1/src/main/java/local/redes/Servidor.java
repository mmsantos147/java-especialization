package local.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Matheus Martins dos Santos
 */
public class Servidor {

    private static final int PORTA = 50000;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORTA);
        ) {
            System.out.println("Servidor aguardando conexões na porta: " + PORTA);
    
            try (Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter printWriter = new PrintWriter(
                         new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                String cpfCliente = bufferedReader.readLine();
                System.out.println("Cpf recebido: " + cpfCliente);

                boolean valido = ValidadorCPF.ehValido(cpfCliente);
                String resposta = valido ? "Este CPF é válido." : "Este CPF é inválido.";  

                System.out.println(resposta);
                printWriter.println(resposta);
                
                System.out.println("Conexão encerrada, desconectando...");
            }
        } 
   }
}
