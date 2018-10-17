package sample.core;

import sample.Interesse;
import sample.Passagem;
import sample.Voo;
import sample.Hotel;
import sample.core.models.ServicoNomes;
import sample.database.ManagerQuery;
import sample.database.Repository;
import sample.rmi.InterfaceCli;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static sample.Main.appManager;
import static sample.rmi.RMIManager.SERVICONOMES;

public class SubscribeManager {
    private Repository repository;
    private ManagerQuery mquery;

    public SubscribeManager() {
        this.repository = new Repository();
        this.mquery = new ManagerQuery();

        try {
            clearInteresses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearInteresses() throws SQLException {
        repository.executeUpdate("DELETE FROM interesses");
    }

    public String cadastrarInteresse(Interesse interesse, InterfaceCli cli,String id) throws AlreadyBoundException, RemoteException, SQLException, NotBoundException {
        ResultSet rs;
        String ref_cliente = id;
        if(id == null)
        {
            try {
                rs = repository.executeQuery("SELECT max(id) as id from servico_nomes;");
                ref_cliente = "Cliente" + rs.getInt("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            registrarObjetoRemoto(ref_cliente,cli);
        }
        Integer ref_id = repository.executeQuery(mquery.getIDServicoNomesPeloNome(ref_cliente)).getInt("id");
        repository.executeUpdate(mquery.insertInteresse(ref_cliente,ref_id,
                interesse.getTipo_interesse(),
                repository.executeQuery(mquery.getIDCidadePeloNome(interesse.getOrigem())).getInt("id"),
                repository.executeQuery(mquery.getIDCidadePeloNome(interesse.getDestino())).getInt("id"),
                interesse.getPreco_maximo()));
        notificarCliente(ref_cliente,"Interesse cadastrado com sucesso!");
        checarInteresses();
        return ref_cliente;
    }

    private void notificarCliente(String ref_client, String msg) throws RemoteException, NotBoundException {
        InterfaceCli com = (InterfaceCli)SERVICONOMES.lookup(ref_client);
        com.notificar(ref_client+ " " + msg);
    }

    private void registrarObjetoRemoto(String ref_client, InterfaceCli cliente) throws AlreadyBoundException, RemoteException {
        SERVICONOMES.bind(ref_client, cliente);
        try {
            repository.executeUpdate(mquery.registrar_SN(ref_client));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ServicoNomes> getAllConn() throws SQLException {
        ResultSet rs = repository.executeQuery("SELECT nome from servico_nomes");
        List<ServicoNomes> nomes = new ArrayList<>();

        while(rs.next())
        {
            nomes.add(new ServicoNomes(rs.getString("nome")));
        }
        return nomes;
    }

    public List<Interesse> getAllInteresses() throws SQLException {
        ResultSet rs = repository.executeQuery("SELECT * FROM interesses");
        List<Interesse> interesses = new ArrayList<>();

        while(rs.next())
        {
            Interesse i = new Interesse(rs.getString("nome_cliente"),
                    String.valueOf(rs.getInt("ref_cliente")),
                    rs.getInt("tipo_interesse"),
                    repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("origem"))).getString("nome"),
                    repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("destino"))).getString("nome"),
                    rs.getDouble("preco_maximo"),
                    rs.getInt("id_checado")
                    );
            i.setId(rs.getInt("id"));

            interesses.add(i);

        }

        return interesses;
    }


    public void checarInteresses() throws SQLException, RemoteException, NotBoundException {
        List<Interesse> interesses = getAllInteresses();
        double min;
        int j=0;
        for (Interesse i: interesses)
        {
            Integer tipo_interesse = i.getTipo_interesse();
            List<Voo> voos = new ArrayList<>();

            switch(tipo_interesse)
            {
                case 1:
                    //Passagens
                    Passagem p = new Passagem();
                    Voo v = new Voo(null, i.getOrigem(), i.getDestino(), null, null, null);
                    p.setVoo(v);
                    p.setPreco(i.getPreco_maximo());
                    voos = appManager.getAeroManager().consultarVoos(p);

                    int max=-1,count=0;
                    System.out.println(voos);
                    for (j = 0; j<voos.size();j++) {
                        Voo voo = voos.get(j);
                        if(voo.getId() <= i.getId_checado()) {
                            count++;
                        }
                        if (voo.getId() > max)
                            max = voo.getId();
                        // TODO PRECO DO VOO NO RETORNO
                    }

                    this.atualizarIdChecadoInteresse(i,max);
                    System.out.println(voos.get(0).getNome());
                    if(voos.size()-count > 0)
                    {
                        notificarCliente(i.getNome_cliente(),
                                "Temos disponível um voo para " + i.getDestino());
                    }
                    break;
                case 2:
                    // Hospedagens
                    List<Hotel> hoteis = appManager.getHotelManager().getHoteisPeloLocal(i.getDestino());
                    min = 999999;
                    int counth=0;
                    max = -1;
                    for (Hotel h:hoteis)
                    {
                        if(h.getId() <= i.getId_checado())
                        {
                            counth++;
                        }
                        if(h.getPreco() < min)
                            min = h.getPreco();
                        if (h.getId() > max)
                            max = h.getId();
                    }

                    this.atualizarIdChecadoInteresse(i,max);

                    if(hoteis.size()-counth > 0)
                    {
                        notificarCliente(i.getNome_cliente(),
                                "Temos disponível um hotel para " + i.getDestino() +
                        " por apenas R$" + min);
                    }
                    break;
                case 3:

            }
        }
    }

    private void atualizarIdChecadoInteresse(Interesse i,Integer valor) throws SQLException {
        repository.executeUpdate("UPDATE interesses SET id_checado = " + valor + " WHERE id = " + i.getId());
    }

    public List<Interesse> getInteressesPeloCliente(String cliente) throws SQLException {
        String query = "SELECT * FROM interesses WHERE nome_cliente = '" + cliente+ "';";
       ResultSet rs = repository.executeQuery(query);
       List<Interesse> interesses = new ArrayList<>();

       while(rs.next())
       {
           Interesse i = new Interesse(rs.getString("nome_cliente"),
                   String.valueOf(rs.getInt("ref_cliente")),
                   rs.getInt("tipo_interesse"),
                   repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("origem"))).getString("nome"),
                   repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("destino"))).getString("nome"),
                   rs.getDouble("preco_maximo"),
                   rs.getInt("id_checado"));
           i.setId(rs.getInt("id"));

           interesses.add(i);
       }

       return interesses;

    }

    public void deleteInteresse(Integer id) throws SQLException {
        repository.executeUpdate("DELETE FROM interesses WHERE id = " + id);
    }
}
