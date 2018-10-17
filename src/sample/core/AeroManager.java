package sample.core;

import sample.Passagem;
import sample.Voo;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static sample.Main.appManager;

public class AeroManager {
    private Repository repository;
    private ManagerQuery mquery;

    public AeroManager() {
        this.repository = new Repository();
        this.mquery = new ManagerQuery();
    }

    public void cadastrarVoo(Voo voo) throws SQLException, RemoteException, NotBoundException {
        Integer origem = repository.executeQuery(mquery.getIDCidadePeloNome(voo.getOrigem())).getInt("id");
        Integer destino = repository.executeQuery(mquery.getIDCidadePeloNome(voo.getDestino())).getInt("id");
        String sql = mquery.cadastrarVoo(voo.getNome(),origem,destino,voo.getCapacidade(),voo.getData_ida(),voo.getData_volta(),voo.getPreco());
        repository.executeUpdate(sql);
        appManager.getSubsManager().checarInteresses();
    }

    public List<Voo> consultarVoos(Passagem passagem) throws SQLException {
        List<Voo> voos = new ArrayList<>();
        Integer origem = repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getOrigem())).getInt("id");
        Integer destino = repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getDestino())).getInt("id");
        Integer volta_origem = destino;
        Integer volta_destino = origem;
        Passagem volta_passagem = new Passagem();
        volta_passagem.setVoo(new Voo(null,passagem.getVoo().getDestino(),passagem.getVoo().getOrigem(),null,null,null));
        if (passagem.getVoo().getData_volta() == null && passagem.getVoo().getData_ida() != null)
        {
           ResultSet rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),false));
           voos = preencheVoos(rs,passagem,false);
           rs = repository.executeQuery(mquery.getAllVoos(volta_origem,volta_destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),true));
           voos.addAll(preencheVoos(rs,volta_passagem,false));
        }
        else if(passagem.getVoo().getData_volta() != null && passagem.getVoo().getData_ida() != null)
        {
            ResultSet rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),false));
            voos = preencheVoos(rs,passagem,false);
            rs = repository.executeQuery(mquery.getAllVoos(volta_origem,volta_destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),true));
            voos.addAll(preencheVoos(rs,volta_passagem,false));
            ResultSet volta_rs = repository.executeQuery(mquery.getAllVoos(volta_origem,volta_destino,passagem.getVoo().getData_volta(),passagem.getNumero_pessoas(),false));
            voos.addAll(preencheVoos(volta_rs,volta_passagem,false));
            volta_rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_volta(),passagem.getNumero_pessoas(),true));
            voos.addAll(preencheVoos(volta_rs,passagem,false));
        }
        // Todos são nulls retorna todos voos de origem para destino
        else
        {
            String query = "Select * from voos where origem = " +
                    repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getOrigem())) .getInt("id")
                    + " AND destino = " +
                    repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getDestino())) .getInt("id")
                    + " AND " +
                    "preco <= " + passagem.getPreco() + ";";
            ResultSet rs = repository.executeQuery(query);

            voos = preencheVoos(rs,passagem,true);
            System.out.println(query);
        }

        return voos;
    }

    public void efetuarCompraPassagem(Passagem passagem) throws SQLException {
        ResultSet rs = repository.executeQuery(mquery.getIdVooPeloNome(passagem.getVoo().getNome()));
        Integer voo_id = rs.getInt("id");
        repository.executeUpdate(mquery.compraPassagem(new Date().toString(),voo_id,passagem.getCliente()));
        Integer vendidos = repository.executeQuery(mquery.getVendidosPeloNome(passagem.getVoo().getNome())).getInt("vendidos");
        repository.executeUpdate(mquery.aumentarVendidosVoo(passagem.getVoo().getNome(),vendidos+1));
    }

    private List<Voo> preencheVoos(ResultSet rs, Passagem passagem,boolean get_all) throws SQLException {
        List<Voo> voos = new ArrayList<>();
        while(rs.next())
        {
            Voo v = new Voo(null,null,null,null,null,null);
            v.setId(rs.getInt("id"));
            v.setNome(rs.getString("nome"));
            v.setCapacidade(rs.getInt("capacidade"));
            v.setVendidos(rs.getInt("vendidos"));
            v.setData_ida(rs.getString("data_ida"));
            v.setData_volta(rs.getString("data_volta"));
            v.setPreco(rs.getDouble("preco"));
            if (!get_all) {
                v.setOrigem(passagem.getVoo().getOrigem());
                v.setDestino(passagem.getVoo().getDestino());
            }
            else
            {
                v.setOrigem(repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("origem"))).getString("nome"));
                v.setDestino(repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("destino"))).getString("nome"));
            }
            voos.add(v);
        }
        return voos;
    }

    public List<Voo> getAllVoos() throws SQLException {
        ResultSet rs = repository.executeQuery(mquery.getAllVoos());

       return preencheVoos(rs,null,true);
    }


}
