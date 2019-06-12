package estruturas;

import processo.Serializador;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteEnvio {
    private int idCliente;
    private String[] musica;
    private String modo;

    public ClienteEnvio(int idCliente, String[] musica, String modo){
        this.musica = musica;
        this.idCliente = idCliente;
        this.modo = modo;
    }

    public void comecar() {
        try {
            enviarDados(musica, modo);
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void enviarDados(String[] musica, String modo) throws Exception {
        DatagramSocket socketCliente = new DatagramSocket();
        InetAddress enderecoIP = InetAddress.getByName("localhost");

        DadosPacote objetoEnvio;
        byte[] dadosEnvia;
        byte[] dadosRecebe;
        DatagramPacket pacoteEnvia;

        for (int i = 0; i < musica.length ; i = incrementaContador(i, modo)) {
            int numeroSequencia = Integer.parseInt(musica[i].substring(0,2)) - 1;
            objetoEnvio = new DadosPacote(musica[i], numeroSequencia, idCliente);
            dadosEnvia = Serializador.convertToBytes(objetoEnvio);
            dadosRecebe = new byte[objetoEnvio.getMensagem().length()];
            pacoteEnvia = new DatagramPacket(dadosEnvia, dadosEnvia.length, enderecoIP, 9876);
            socketCliente.send(pacoteEnvia);

            System.out.print("Cliente: " + idCliente + ". Verso enviado: " + musica[i]);

            DatagramPacket receivePacket = new DatagramPacket(dadosRecebe, dadosRecebe.length);
            socketCliente.receive(receivePacket);
            String resposta = new String(receivePacket.getData());

            System.out.println(resposta);

        }

        socketCliente.close();
    }

    private int incrementaContador(int contador, String modo) throws Exception {
        switch(modo){
            case "1":
                return contador + 1;

            case "2":
                if(Math.random() > 0.80 && contador < musica.length - 2) {
                    String aux = musica[contador + 1];
                    musica[contador + 1] = musica[contador + 2];
                    musica[contador + 2] = aux;
                }
                return contador + 1;

            case "3":
                return Math.random() > 0.80 ? contador+2 : contador+1;

            case "4":
                return Math.random() > 0.80 ? contador : contador+1;

            case "5":
                String novoModo = "" + ((int)(Math.random()*4) + 1);
                return incrementaContador(contador, novoModo);

            default:
                throw new Exception("Modo inválido");
        }
    }
}
