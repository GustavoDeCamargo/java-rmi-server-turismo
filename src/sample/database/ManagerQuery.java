package sample.database;


import sample.core.models.Hotel;

import java.util.Date;

// Implementa as funções que montam as querys
public class ManagerQuery {

    public String registrar_SN(String nome)
    {
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT INTO servico_nomes(nome) VALUES('");
        strb.append(nome);
        strb.append("');");
        return strb.toString();
    }

    public String getAllCidades()
    {
        return "SELECT nome from cidades;";
    }

    public String cadastrarVoo(String nome, Integer origem, Integer destino, Integer capacidade, Date data_ida, Date data_volta)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO voos(nome,origem,destino,capacidade,vendidos,data_ida,data_volta) VALUES (");
        stringBuilder.append("'" + nome + "',");
        stringBuilder.append("'" + origem + "',");
        stringBuilder.append("'" + destino + "',");
        stringBuilder.append("" + capacidade + ",");
        stringBuilder.append("" + 0 + ",");
        stringBuilder.append("'" + data_ida.toString() + "',");
        stringBuilder.append("'" + data_volta.toString() + "');");

        return stringBuilder.toString();
    }

    public String getIDCidadePeloNome(String nome)
    {
        return "SELECT id FROM cidades WHERE nome = '" + nome + "';";
    }


    public String insertInteresse(String nome_cliente, Integer ref_cliente, Integer tipo_interesse,
                                  Integer origem, Integer destino, Double preco_maximo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO interesses(nome_cliente,ref_cliente," +
                "tipo_interesse,destino,origem,preco_maximo) VALUES (");
        stringBuilder.append("'" + nome_cliente + "',");
        stringBuilder.append("" + ref_cliente + ",");
        stringBuilder.append("" + tipo_interesse + ",");
        stringBuilder.append("" + origem + ",");
        stringBuilder.append("" + destino + ",");
        stringBuilder.append("" + preco_maximo + ");");
        return stringBuilder.toString();
    }

    public String getIDServicoNomesPeloNome(String nome)
    {
        return "SELECT id FROM servico_nomes WHERE nome = '" + nome + "';";
    }
    public String cadastrarHospedagem(String hotel, Integer destino, Integer numero_pessoas, Integer preco) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO hoteis(nome,local,vagas,ocupacao) VALUES (");
        stringBuilder.append("'" + hotel + "',");
        stringBuilder.append("'" + destino + "',");
        stringBuilder.append("" + numero_pessoas + ",");
        stringBuilder.append("" + preco + ");");

        return stringBuilder.toString();
    }
}
