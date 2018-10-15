package sample.core.models;

import java.util.Date;

public class Voo {
    private String nome;
    private String origem;
    private String destino;
    private Integer capacidade;
    private Date data_ida;
    private Date data_volta;

    public Voo(String nome, String origem, String destino, Integer capacidade, Date data_ida, Date data_volta) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.capacidade = capacidade;
        this.data_ida = data_ida;
        this.data_volta = data_volta;
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

    public Date getData_ida() {
        return data_ida;
    }

    public void setData_ida(Date data_ida) {
        this.data_ida = data_ida;
    }

    public Date getData_volta() {
        return data_volta;
    }

    public void setData_volta(Date data_volta) {
        this.data_volta = data_volta;
    }
}
