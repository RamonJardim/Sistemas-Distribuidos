package entidades;

import constantes.Musicas;
import estruturas.ClienteEnvio;

public class ClienteUDP2 {

	/* MODO DO CLIENTE - args[0] */
	//Modo 1 = Mensagens ordenadas sequencialmente
	//Modo 2 = Mensagens fora de ordem
	//Modo 3 = Simula mensagens perdidas
	//Modo 4 = Mensagens duplicadas
	//Modo 5 = Aleatório

	public static void main(String args[]) {
		ClienteEnvio cliente = new ClienteEnvio(1, Musicas.WONDERWALL, args[0]);
		cliente.comecar();
	}

}
