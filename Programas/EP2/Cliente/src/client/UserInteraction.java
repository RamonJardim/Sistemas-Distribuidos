package client;

import communication.*;

import java.net.SocketTimeoutException;
import java.util.Scanner;


public class UserInteraction {
    private static Scanner sc = new Scanner(System.in);

    public static void begin(){
        search();
    }

    private static void search() {
        String searchFile;
        searchFile = getFileNameFromUser();
        try {
            Messenger.beginFlood(searchFile);
        } catch (SocketTimeoutException e) {
            showErrorToUser("Tempo esgotado.");
        }
    }

    private static void showErrorToUser(String error) {
        System.out.println("Erro: " + error);
    }

    private static String getFileNameFromUser(){
        System.out.println("Digite o nome do arquivo que deseja buscar: ");
        return sc.nextLine();
    }

}