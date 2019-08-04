package client;

import communication.*;
import data.DAO;
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
            System.out.println("Console cliente " + DAO.getClientID() + ": Pesquisando por arquivo " + searchFile + " no peer " + peer);
            if(!Messenger.beginFlood(searchFile, peer).isEmpty()) {
                System.out.println("Console cliente " + DAO.getClientID() + ": Baixando arquivo " + searchFile + " do peer " + peer + ".");
                Downloader.download(searchFile, peer);
                System.out.println("Console cliente " + DAO.getClientID() + ": arquivo " + searchFile + " baixado");
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Console cliente " + DAO.getClientID() + ": Tempo esgotado para consulta por arquivo " + searchFile);
        } catch (Exception e){
            System.out.println("Erro: ");
            e.printStackTrace();
        }
    }

    private static void showErrorToUser(String error) {
        System.out.println("Erro: " + error);
    }

    private static String getFileNameFromUser(){
        System.out.println("Console cliente " + DAO.getClientID() + ": Digite o nome do arquivo que deseja buscar: ");
        return sc.nextLine();
    }

}