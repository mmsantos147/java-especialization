package local.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Matheus Martins dos Santos
 */
public class Cliente {

    private static final String HOST = "127.0.0.1";

    private static final int PORTA = 50000;

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite um CPF para verificação: ");
            String cpf = scanner.nextLine();
            try (Socket socket = new Socket(HOST, PORTA); 
                    BufferedReader bufferedReader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                    PrintWriter printWriter = new PrintWriter(
                         new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
                System.out.println("Conectado ao servidor " + HOST+ ":" + socket.getPort());
                
                printWriter.println(cpf);
                System.out.println("Cpf enviado aguardando validação...");

                String resposta = bufferedReader.readLine();
                System.out.println("Resposta do servidor: " + resposta);
                
                System.out.println("Cliente Desconectando...");
            } 

        } 
    }
}
