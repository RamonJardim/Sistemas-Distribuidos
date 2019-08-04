package client;

import data.DAO;

public class Client{

    public static void main(String[] args) {
        DAO.setPeerID(Integer.parseInt(args[0]));
        UserInteraction.begin();
    }
}
