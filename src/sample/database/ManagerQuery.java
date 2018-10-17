package sample.database;


import sample.core.models.Hotel;

import java.text.SimpleDateFormat;
import java.util.Date;

// Implementa as funções que montam as querys
public class ManagerQuery {

    public String registrar_SN(String nome)
    {
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT INTO servico_nomes(nome) VALUES('");
        strb.append(nome);
        strb.append("');");
        System.out.println(strb.toString());

        return strb.toString();
    }

    public String getAllCidades()
    {
        return "SELECT nome from cidades;";
    }

    public String cadastrarVoo(String nome, Integer origem, Integer destino, Integer capacidade, String data_ida, String data_volta)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO voos(nome,origem,destino,capacidade,vendidos,data_ida,data_volta) VALUES (");
        stringBuilder.append("'" + nome + "',");
        stringBuilder.append("'" + origem + "',");
        stringBuilder.append("'" + destino + "',");
        stringBuilder.append("" + capacidade + ",");
        stringBuilder.append("" + 0 + ",");
        stringBuilder.append("'" + data_ida + "',");
        stringBuilder.append("'" + data_volta + "');");
        System.out.println(stringBuilder.toString());

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

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getIDServicoNomesPeloNome(String nome)
    {
        return "SELECT id FROM servico_nomes WHERE nome = '" + nome + "';";
    }
    public String cadastrarHospedagem(String hotel, Integer destino, Integer numero_pessoas, Double preco) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO hoteis(nome,local,vagas,ocupacao,preco) VALUES (");
        stringBuilder.append("'" + hotel + "',");
        stringBuilder.append("'" + destino + "',");
        stringBuilder.append("" + numero_pessoas + ",");
        stringBuilder.append("" + 0 + ",");
        stringBuilder.append("" + preco + ");");

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getAllVoos(Integer origem,Integer destino,String data)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM voos WHERE ");
        stringBuilder.append("origem = "+ origem + " AND ");
        stringBuilder.append("destino = " + destino + " AND ");
        stringBuilder.append("data_ida = '" + data + "' AND ");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getAllHoteisByDestino(Integer destino)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM hoteis WHERE ");
        stringBuilder.append("local = "+ destino + " AND ");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getAllHoteisByNome(String nome)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM hoteis WHERE ");
        stringBuilder.append("nome = "+ nome + " AND ");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String aumentarVendidosVoo(String nome,Integer novo_valor)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE voos SET vendidos = ");
        stringBuilder.append(novo_valor);
        stringBuilder.append(" WHERE nome = " + nome);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
