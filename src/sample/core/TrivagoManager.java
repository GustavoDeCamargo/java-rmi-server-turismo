package sample.core;

import sample.core.models.Hospedagem;
import sample.core.models.Voo;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrivagoManager {

    private Repository repository;
    private ManagerQuery mquery;

    public TrivagoManager() {
        this.repository = new Repository();
        this.mquery = new ManagerQuery();
    }


    public List<String> getAllCidades() throws SQLException {
        ResultSet rs = repository.executeQuery(mquery.getAllCidades());
        List<String> cidades = new ArrayList<>();
        while(rs.next())
        {
            cidades.add(rs.getString("nome"));
        }

        return cidades;
    }

    public void cadastrarHospedagem(Hospedagem hospedagem) throws SQLException {
        Integer destino = repository.executeQuery(mquery.getIDCidadePeloNome(hospedagem.getDestino())).getInt("id");
        String sql = mquery.cadastrarHospedagem(hospedagem.getHotel(),destino,hospedagem.getNumero_pessoas(),hospedagem.getPreco());
        repository.executeUpdate(sql);
    }
}
