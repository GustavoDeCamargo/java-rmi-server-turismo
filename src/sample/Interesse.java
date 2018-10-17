package sample;

import java.io.Serializable;

public class Interesse implements Serializable {
    private static final long serialVersionUID = 8367908553994431734L;

    private Integer id;
    private String nome_cliente;
    private String ref_cliente;
    // 0 sem interesse
    // 1 passagens aereas
    // 2 hoteis
    // 3 pacotes
    private Integer tipo_interesse;
    private String origem;
    private String destino;
    private Double preco_maximo;
    private Integer id_checado;


    public Interesse(String nome_cliente, String ref_cliente, Integer tipo_interesse, String origem, String destino, Double preco_maximo, Integer id_checado) {
        this.nome_cliente = nome_cliente;
        this.ref_cliente = ref_cliente;
        this.tipo_interesse = tipo_interesse;
        this.origem = origem;
        this.destino = destino;
        this.preco_maximo = preco_maximo;
        this.id_checado = id_checado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getRef_cliente() {
        return ref_cliente;
    }

    public void setRef_cliente(String ref_cliente) {
        this.ref_cliente = ref_cliente;
    }

    public Integer getTipo_interesse() {
        return tipo_interesse;
    }

    public void setTipo_interesse(Integer tipo_interesse) {
        this.tipo_interesse = tipo_interesse;
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

    public Double getPreco_maximo() {
        return preco_maximo;
    }

    public void setPreco_maximo(Double preco_maximo) {
        this.preco_maximo = preco_maximo;
    }

    public Integer getId_checado() {
        return id_checado;
    }

    public void setId_checado(Integer id_checado) {
        this.id_checado = id_checado;
    }
}
