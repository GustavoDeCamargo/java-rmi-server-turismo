package sample;

import java.io.Serializable;
import java.util.Date;

public class Hospedagem implements Serializable {
    private static final long serialVersionUID = 8367908553994431734L;
    private Integer id;
    private String hotel;
    private String destino;
    private String data_entrada;
    private String data_saida;
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

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
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
