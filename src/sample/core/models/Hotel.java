package sample.core.models;

import java.io.Serializable;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 8367908553994431734L;
    private Integer id;
    private String nome;
    private String local;
    private Integer vagas;
    private Integer ocupacao;
    private Double preco;

    public Hotel(String nome, String local, Integer vagas, Double preco) {
        this.nome = nome;
        this.local = local;
        this.vagas = vagas;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Integer getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Integer ocupacao) {
        this.ocupacao = ocupacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
