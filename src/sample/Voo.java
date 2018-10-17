package sample;

import java.io.Serializable;
import java.util.Date;

public class Voo implements Serializable {
    private static final long serialVersionUID = 8367908553994431734L;
    private String nome;
    private String origem;
    private String destino;
    private Integer capacidade;
    private String data_ida;
    private String data_volta;
    private Integer vendidos;

    public Voo(String nome, String origem, String destino, Integer capacidade, String data_ida, String data_volta) {
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

    public String getData_ida() {
        return data_ida;
    }

    public void setData_ida(String data_ida) {
        this.data_ida = data_ida;
    }

    public String getData_volta() {
        return data_volta;
    }

    public void setData_volta(String data_volta) {
        this.data_volta = data_volta;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }
}
