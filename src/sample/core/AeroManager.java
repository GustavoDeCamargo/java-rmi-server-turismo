package sample.core;

import sample.Passagem;
import sample.Voo;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<Voo> voos = new ArrayList<>();
        if (passagem.getVoo().getData_volta() == null)
        {
            Integer origem = repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getOrigem())).getInt("id");
            Integer destino = repository.executeQuery(mquery.getIDCidadePeloNome(passagem.getVoo().getDestino())).getInt("id");
            ResultSet rs = repository.executeQuery(mquery.getAllVoos(origem,destino,passagem.getVoo().getData_ida(),passagem.getNumero_pessoas()));

            while(rs.next())
            {
                Voo v = new Voo(null,null,null,null,null,null);
                v.setNome(rs.getString("nome"));
                voos.add(v);
            }
        }
        return voos;
    }
}
