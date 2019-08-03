package client;

import communication.*;
import data.Randomizer;

import java.net.SocketTimeoutException;
import java.util.Scanner;


public abstract class UserInteraction {
    private static Scanner sc = new Scanner(System.in);

    public static void begin(){
        while(true)
            search();
    }

    private static void search() {
        String searchFile = getFileNameFromUser();
        try {
            String peer = Randomizer.getRandomPeer();
            System.out.println("Pesquisando por arquivo " + searchFile + " no peer " + peer);
            Messenger.beginFlood(searchFile, peer);

        } catch (SocketTimeoutException e) {
            System.out.println("Tempo esgotado para consulta por arquivo " + searchFile);
        } catch (Exception e){
            System.out.println("Erro: ");
            e.printStackTrace();
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