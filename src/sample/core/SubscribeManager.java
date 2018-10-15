package sample.core;

import sample.core.models.Interesse;
import sample.database.ManagerQuery;
import sample.database.Repository;
import sample.rmi.InterfaceCli;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.rmi.RMIManager.SERVICONOMES;

public class SubscribeManager {
    private Repository repository;
    private ManagerQuery mquery;

    public SubscribeManager() {
        this.repository = new Repository();
        this.mquery = new ManagerQuery();
    }

    public void cadastrarInteresse(String string, InterfaceCli cli) throws AlreadyBoundException, RemoteException, SQLException {
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
                3,1,2,500.0));
    }

    private void notificarCliente(String ref_client, String msg) throws RemoteException, NotBoundException {
        InterfaceCli com = (InterfaceCli)SERVICONOMES.lookup(ref_client);
        com.notificar(ref_client+msg);
    }

    private void registrarObjetoRemoto(String ref_client, InterfaceCli cliente) throws AlreadyBoundException, RemoteException {
        SERVICONOMES.bind(ref_client, cliente);
        try {
            repository.executeUpdate(mquery.registrar_SN(ref_client));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
