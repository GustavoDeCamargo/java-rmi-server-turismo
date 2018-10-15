package sample.core.models;

public class Interesse {

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
}
