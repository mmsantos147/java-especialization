package com.mss;

import java.util.Scanner;

public class Leitura {
    private static Scanner scanner = new Scanner(System.in);

    public static String entDados(String mensagem) {
        System.out.print(mensagem);
        return scanner.next();
    }
}
