package sample.database;


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

    public String cadastrarVoo(String nome,Integer origem,Integer destino,Integer capacidade)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO voos(nome,origem,destino,capacidade,vendidos) VALUES (");
        stringBuilder.append("'" + nome + "',");
        stringBuilder.append("'" + origem + "',");
        stringBuilder.append("'" + destino + "',");
        stringBuilder.append("'" + capacidade + "',");
        stringBuilder.append("'" + 0 + "');");

        return stringBuilder.toString();
    }

    public String getIDCidadePeloNome(String nome)
    {
        return "SELECT id FROM cidades WHERE nome = '" + nome + "';";
    }

}
