package estruturas;

import java.io.Serializable;

public class DadosPacote implements Serializable {

    private String mensagem;
    private int numeroSequencia;
    private int idCliente;

    public DadosPacote(){}

    public DadosPacote(String mensagem, int numeroSequencia, int idCliente){
        this.mensagem = mensagem;
        this.numeroSequencia = numeroSequencia;
        this.idCliente = idCliente;
    }

    public String getMensagem(){
        return this.mensagem;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public int getNumeroSequencia(){
        return numeroSequencia;
    }

    public void setNumeroSequencia(int numeroSequencia){
        this.numeroSequencia = numeroSequencia;
    }

    public int getIdCliente(){
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
