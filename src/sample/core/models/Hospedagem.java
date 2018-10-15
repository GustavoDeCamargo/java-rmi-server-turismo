package sample.core.models;

import java.util.Date;

public class Hospedagem {
    private Integer id;
    private Hotel hotel;
    private Date data_entrada;
    private Date data_saida;
    private Integer numero_quartos;
    private Integer numero_pessoas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
