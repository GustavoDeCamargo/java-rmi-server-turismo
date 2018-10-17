package sample.core;

import sample.Interesse;
import sample.Passagem;
import sample.Voo;
import sample.core.models.Hotel;
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

    public void cadastrarInteresse(Interesse interesse, InterfaceCli cli) throws AlreadyBoundException, RemoteException, SQLException, NotBoundException {
        ResultSet rs;
        String ref_cliente = null;
        try {
            rs = repository.executeQuery("SELECT max(id) as id from servico_nomes;");
            ref_cliente = "Cliente" + rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        registrarObjetoRemoto(ref_cliente,cli);
        Integer ref_id = repository.executeQuery(mquery.getIDServicoNomesPeloNome(ref_cliente)).getInt("id");
        repository.executeUpdate(mquery.insertInteresse(ref_cliente,ref_id,
                interesse.getTipo_interesse(),
                repository.executeQuery(mquery.getIDCidadePeloNome(interesse.getOrigem())).getInt("id"),
                repository.executeQuery(mquery.getIDCidadePeloNome(interesse.getDestino())).getInt("id"),
                interesse.getPreco_maximo()));
        notificarCliente(ref_cliente,"Interesse cadastrado com sucesso!");
        checarInteresses();
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
                    rs.getDouble("preco_maximo")
                    );

            interesses.add(i);

        }

        return interesses;
    }


    public void checarInteresses() throws SQLException, RemoteException, NotBoundException {
        List<Interesse> interesses = getAllInteresses();

        for (Interesse i: interesses)
        {
            Integer tipo_interesse = i.getTipo_interesse();

            switch(tipo_interesse)
            {
                case 1:
                    //Passagens
                    Passagem p = new Passagem();
                    Voo v = new Voo(null, i.getOrigem(), i.getDestino(), null, null, null);
                    p.setVoo(v);
                    p.setPreco(i.getPreco_maximo());
                    System.out.println(v.getOrigem() + v.getDestino());
                    List<Voo> voos = appManager.getAeroManager().consultarVoos(p);
                    double min;
                    for (Voo voo:voos) {
                        System.out.println(voo.getNome());
                        // TODO PRECO DO VOO NO RETORNO
                    }
                    if(voos.size() > 0)
                    {
                        notificarCliente(i.getNome_cliente(),
                                "Temos disponível um voo para " + i.getDestino());
                    }
                    break;
                case 2:
                    // Hospedagens
                    List<Hotel> hoteis = appManager.getHotelManager().getHoteisPeloLocal(i.getDestino());
                    System.out.println(hoteis);
                    break;

            }
        }
    }
}
