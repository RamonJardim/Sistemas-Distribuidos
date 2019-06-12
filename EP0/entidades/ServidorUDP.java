package entidades;

import estruturas.ClienteBuffer;
import processo.Serializador;
import estruturas.DadosPacote;

import java.net.*;

public class ServidorUDP {
	public static void main(String args[]) throws Exception {
		ClienteBuffer clientes[] = new ClienteBuffer[2];

		DatagramSocket serverSocket = new DatagramSocket(9876);

		while(true) {
			byte[] dadosRecebidos = new byte[1024];
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);


			//Recebe o pacote
			serverSocket.receive(pacoteRecebido);

			InetAddress IPAddress = pacoteRecebido.getAddress();
			int port = pacoteRecebido.getPort();

			DadosPacote objetoRecebido = (DadosPacote)(Serializador.convertFromBytes(pacoteRecebido.getData()));
			String mensagem = objetoRecebido.getMensagem();
			int numeroSequencia = objetoRecebido.getNumeroSequencia();
			int idCliente = objetoRecebido.getIdCliente();

			if(clientes[idCliente] == null) {
                clientes[idCliente] = new ClienteBuffer(1000, idCliente);
                clientes[idCliente].start();

			}

			clientes[idCliente].adicionaBuffer(mensagem, numeroSequencia);

			String dadosPacote = "IP: "+IPAddress.getHostAddress()+
					" SequenceNumber: " + numeroSequencia +
					" ClientID: " + idCliente +
					" Message: " + mensagem;

			System.out.println(dadosPacote);


			byte[] sendData;
			sendData = ("OK! Cliente: " + idCliente + " Seq: " + numeroSequencia).getBytes();

			DatagramPacket resposta = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(resposta);
		}
	}
}
