package sample.core;

import sample.core.models.Hotel;
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

    public void cadastrarHospedagem(Hotel hospedagem) throws SQLException {
        Integer local = repository.executeQuery(mquery.getIDCidadePeloNome(hospedagem.getLocal())).getInt("id");
        String sql = mquery.cadastrarHospedagem(hospedagem.getNome(),local,hospedagem.getVagas(),hospedagem.getPreco());
        repository.executeUpdate(sql);
    }

    public List<Hotel> getAllHoteis() throws SQLException {
        ResultSet rs = repository.executeQuery("SELECT * from hoteis");
        List<Hotel> hoteis = new ArrayList<>();

        while(rs.next())
        {
            Hotel h = new Hotel(rs.getString("nome"),
                    repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("local"))).getString("nome"),
                    rs.getInt("vagas"),
                    rs.getDouble("preco"));
            h.setOcupacao(rs.getInt("ocupacao"));
            hoteis.add(h);
        }
        return hoteis;
    }

    public List<Hotel> getHoteisPeloLocal(String local) throws SQLException {
        String query = "SELECT * FROM hoteis WHERE local = "
                + repository.executeQuery(mquery.getIDCidadePeloNome(local)).getInt("id");
        System.out.println(query);
        ResultSet rs = repository.executeQuery(query);
        List<Hotel> hoteis = new ArrayList<>();

        while(rs.next())
        {
            Hotel h = new Hotel(rs.getString("nome"),
                    repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("local"))).getString("nome"),
                    rs.getInt("vagas"),
                    rs.getDouble("preco"));
            h.setOcupacao(rs.getInt("ocupacao"));
            hoteis.add(h);
        }

        return hoteis;
    }
}
