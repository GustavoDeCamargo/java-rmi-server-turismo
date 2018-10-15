package sample.core;

import sample.core.models.Voo;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.sql.SQLException;

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
}
