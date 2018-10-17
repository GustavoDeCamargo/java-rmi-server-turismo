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

    public String getNomeCidadePeloId(Integer id)
    {
        return "SELECT nome FROM cidades WHERE id = " + id + ";";
    }


    public String insertInteresse(String nome_cliente, Integer ref_cliente, Integer tipo_interesse,
                                  Integer origem, Integer destino, Double preco_maximo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO interesses(nome_cliente,ref_cliente," +
                "tipo_interesse,origem,destino,preco_maximo) VALUES (");
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

    public String getAllVoos(Integer origem,Integer destino,String data,Integer num_pessoas,Boolean volta)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM voos WHERE ");
        stringBuilder.append("origem = "+ origem + " AND ");
        stringBuilder.append("destino = " + destino + " AND ");
        if (volta)
            stringBuilder.append("data_volta = '" + data + "' AND ");
        else
            stringBuilder.append("data_ida = '" + data + "' AND ");
        stringBuilder.append("capacidade-vendidos >= " + num_pessoas);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getAllHoteisByDestino(Integer destino,Integer num_pessoas)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM hoteis WHERE ");
        stringBuilder.append("local = "+ destino + " AND ");
        stringBuilder.append("vagas-ocupacao >= " + num_pessoas);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getAllHoteisByNome(String nome,Integer num_pessoas)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM hoteis WHERE ");
        stringBuilder.append("nome = "+ nome + " AND ");
        stringBuilder.append("vagas-ocupacao >= " + num_pessoas);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String aumentarVendidosVoo(String nome,Integer novo_valor)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE voos SET vendidos = ");
        stringBuilder.append(novo_valor);
        stringBuilder.append(" WHERE nome = '" + nome + "'");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String compraPassagem(String data,Integer voo_id,String nome_cliente)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO passagens(data,voo,cliente) VALUES (");
        stringBuilder.append("'"+ data +"',");
        stringBuilder.append(""+ voo_id +",");
        stringBuilder.append("'"+ nome_cliente +"');");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String compraHospedagem(Integer hotel,String data_entrada,String data_saida,Integer num_quartos,Integer num_pessoas)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO hospedagem(hotel,data_entrada,data_saida,numero_quartos,numero_pessoas)" +
                " VALUES (");
        stringBuilder.append(""+ hotel +",");
        stringBuilder.append("'"+ data_entrada +"',");
        stringBuilder.append("'"+ data_saida +"',");
        stringBuilder.append(""+ num_quartos +",");
        stringBuilder.append(""+ num_pessoas +");");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String aumentarOcupacaoHoteis(String nome,Integer novo_valor)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE hoteis SET ocupacao = ");
        stringBuilder.append(novo_valor);
        stringBuilder.append(" WHERE nome = " + nome);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String getIdVooPeloNome(String nome)
    {
        return "SELECT id from voos WHERE nome = '" + nome + "'";
    }

    public String getVendidosPeloNome(String nome)
    {
        return "SELECT vendidos from voos WHERE nome = '" + nome + "'";
    }

    public String getAllVoos()
    {
        return "SELECT * FROM voos";
    }
}
