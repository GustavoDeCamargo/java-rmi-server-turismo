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


}
