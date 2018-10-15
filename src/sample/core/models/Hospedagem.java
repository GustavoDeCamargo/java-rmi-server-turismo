package sample.core.models;

import java.util.Date;

public class Hospedagem {
    private Integer id;
    private String hotel;
    private String destino;
    private Date data_entrada;
    private Date data_saida;
    private Integer numero_quartos;
    private Integer numero_pessoas;
    private Integer preco;

    public Hospedagem(String hotel, String destino, Integer numero_quartos, Integer numero_pessoas){

        this.hotel = hotel;
        this.destino = destino;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.numero_quartos = numero_quartos;
        this.numero_pessoas = numero_pessoas;
        this.preco = preco;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco_diaria) {
        this.preco = preco;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public Integer getNumero_quartos() {
        return numero_quartos;
    }

    public void setNumero_quartos(Integer numero_quartos) {
        this.numero_quartos = numero_quartos;
    }

    public Integer getNumero_pessoas() {
        return numero_pessoas;
    }

    public void setNumero_pessoas(Integer numero_pessoas) {
        this.numero_pessoas = numero_pessoas;
    }

}
