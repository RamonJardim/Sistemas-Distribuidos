package estruturas;

import java.io.FileWriter;

public class ClienteBuffer extends Thread {
    private String[] buffer;
    private int indiceBuffer;
    private int indiceConsumo;


    private int idCliente;

    public ClienteBuffer(int tamanhoBuffer, int idCliente){
        this.buffer = new String[tamanhoBuffer];
        this.indiceBuffer = -1;
        this.idCliente = idCliente;
    }

    @Override
    public void run(){
		consumir();
    }
	
	public void consumir(){
		int ultimoIndice = -1;
        long fim;
        int timeoutMS = 1000;

        try(FileWriter fw = new FileWriter("Musica - Cliente " + idCliente + ".txt")) {
            for (int timeouts = 0 ; timeouts < 5 ; timeouts ++) {
                fim =  System.currentTimeMillis() + timeoutMS;
                while (System.currentTimeMillis() < fim) {
                    if (buffer[ultimoIndice + 1] != null) {
                        fw.write(buffer[++ultimoIndice]);

                        System.out.println("Consumiu: " + buffer[ultimoIndice]);
                        fim = System.currentTimeMillis() + timeoutMS;
                        timeouts = 0;
                    }
                }
				System.out.println("##MENSAGEM PERDIDA##\n");
                fw.write("##MENSAGEM PERDIDA##\n");
                ultimoIndice++;
            }
            System.out.println("Finalizou com Cliente " + idCliente + "\n");
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
	}

    public void adicionaBuffer(String mensagem, int numeroSequencia){
        if(buffer[numeroSequencia] == null) {
            indiceBuffer++;
        }
        buffer[numeroSequencia] = mensagem;
    }

    public String consomeBuffer(){
        return buffer[indiceConsumo++];
    }

    public int getIdCliente(){
        return this.idCliente;
    }
}
