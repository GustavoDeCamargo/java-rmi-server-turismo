package sample.core;

import sample.Passagem;
import sample.Voo;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AeroManager {
    private Repository repository;
    private ManagerQuery mquery;

    public AeroManager() {
        this.repository = new Repository();
        this.mquery = new ManagerQuery();
    }

    public void cadastrarVoo(Voo voo) throws SQLException {
        Integer origem = repository.executeQuery(mquery.getIDCidadePeloNome(voo.getOrigem())).getInt("id");
        Integer destino = repository.executeQuery(mquery.getIDCidadePeloNome(voo.getDestino())).getInt("id");
        String sql = mquery.cadastrarVoo(voo.getNome(),origem,destino,voo.getCapacidade(),voo.getData_ida(),voo.getData_volta());
        repository.executeUpdate(sql);
    }

    public List<Voo> consultarVoos(Passagem passagem) throws SQLException {
        List<Voo> voos;
        Integer origem = repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getOrigem())).getInt("id");
        Integer destino = repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getDestino())).getInt("id");
        Integer volta_origem = destino;
        Integer volta_destino = origem;
        Passagem volta_passagem = new Passagem();
        volta_passagem.setVoo(new Voo(null,passagem.getVoo().getDestino(),passagem.getVoo().getOrigem(),null,null,null));
        if (passagem.getVoo().getData_volta() == null)
        {
           ResultSet rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),false));
           voos = preencheVoos(rs,passagem);
           rs = repository.executeQuery(mquery.getAllVoos(volta_origem,volta_destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),true));
           voos.addAll(preencheVoos(rs,volta_passagem));
        }
        else
        {
            ResultSet rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),false));
            voos = preencheVoos(rs,passagem);
            rs = repository.executeQuery(mquery.getAllVoos(volta_origem,volta_destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas(),true));
            voos.addAll(preencheVoos(rs,volta_passagem));
            ResultSet volta_rs = repository.executeQuery(mquery.getAllVoos(volta_origem,volta_destino,passagem.getVoo().getData_volta(),passagem.getNumero_pessoas(),false));
            voos.addAll(preencheVoos(volta_rs,volta_passagem));
            volta_rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_volta(),passagem.getNumero_pessoas(),true));
            voos.addAll(preencheVoos(volta_rs,passagem));
        }
        return voos;
    }

    public Integer efetuarCompraPassagem(Passagem passagem) throws SQLException {
        ResultSet rs = repository.executeQuery(mquery.getIdVooPeloNome(passagem.getVoo().getNome()));
        Integer voo_id = rs.getInt("id");
        return 0;
    }

    private List<Voo> preencheVoos(ResultSet rs, Passagem passagem) throws SQLException {
        List<Voo> voos = new ArrayList<>();
        while(rs.next())
        {
            Voo v = new Voo(null,null,null,null,null,null);
            v.setNome(rs.getString("nome"));
            v.setCapacidade(rs.getInt("capacidade"));
            v.setVendidos(rs.getInt("vendidos"));
            v.setData_ida(rs.getString("data_ida"));
            v.setData_volta(rs.getString("data_volta"));
            v.setOrigem(passagem.getVoo().getOrigem());
            v.setDestino(passagem.getVoo().getDestino());
            voos.add(v);
        }
        return voos;
    }


}
