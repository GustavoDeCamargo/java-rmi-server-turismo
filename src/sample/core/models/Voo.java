package sample.core.models;

public class Voo {
    private String nome;
    private String origem;
    private String destino;
    private Integer capacidade;


    public Voo(String nome, String origem, String destino, Integer capacidade) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}
