package sample.core;

import sample.Hospedagem;
import sample.Hotel;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static sample.Main.appManager;

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

    public void cadastrarHospedagem(Hotel hospedagem) throws SQLException, RemoteException, NotBoundException {
        Integer local = repository.executeQuery(mquery.getIDCidadePeloNome(hospedagem.getLocal())).getInt("id");
        String sql = mquery.cadastrarHospedagem(hospedagem.getNome(),local,hospedagem.getVagas(),hospedagem.getPreco());
        repository.executeUpdate(sql);
        appManager.getSubsManager().checarInteresses();

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
            h.setId(rs.getInt("id"));
            hoteis.add(h);
        }

        return hoteis;
    }

    public List<Hotel> consultarHoteis(Hospedagem hospedagem) throws SQLException {
        List<Hotel> hoteis = new ArrayList<>();

        if (hospedagem.getHotel().equals(""))
        {
            hoteis = getHoteisPeloLocal(hospedagem.getDestino());
        }
        else
        {
            hoteis.add(getHotelPeloNome(hospedagem.getHotel()));
        }

        for (int i = 0;i<hoteis.size();i++) {
            Hotel h = hoteis.get(i);
            if(h.getVagas() - h.getOcupacao() < hospedagem.getNumero_pessoas())
                hoteis.remove(i);

        }
        return hoteis;
    }

    public Hotel getHotelPeloNome(String nome) throws SQLException {
        String query = "SELECT * FROM hoteis WHERE nome = '"
                + nome + "';";
        System.out.println(query);
        ResultSet rs = repository.executeQuery(query);

        Hotel h = new Hotel(rs.getString("nome"),
                repository.executeQuery(mquery.getNomeCidadePeloId(rs.getInt("local"))).getString("nome"),
                rs.getInt("vagas"),
                rs.getDouble("preco"));
        h.setOcupacao(rs.getInt("ocupacao"));
        return h;
    }

    public void efetuarCompraHospedagem(Hospedagem hospedagem) throws SQLException {
        Integer id_hotel = repository.executeQuery("SELECT id from hoteis where nome = '" + hospedagem.getHotel() + "';").getInt("id");
        repository.executeUpdate(mquery.compraHospedagem(
                id_hotel,
                hospedagem.getData_entrada(),
                hospedagem.getData_saida(),
                hospedagem.getNumero_quartos(),
                hospedagem.getNumero_pessoas())
        );
        repository.executeUpdate(mquery.aumentarOcupacaoHoteis(hospedagem.getHotel(),
                repository.executeQuery(mquery.getOcupacaoPeloId(id_hotel)).getInt("ocupacao")+hospedagem.getNumero_pessoas()));

    }

}
