package sample.rmi;

import sample.database.ManagerQuery;
import sample.database.Repository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class RMIManager {
    public static Registry SERVICONOMES;

    Repository repository;
    ManagerQuery mquery;


    public RMIManager() throws RemoteException, AlreadyBoundException {
        SERVICONOMES = LocateRegistry.createRegistry(1099);
        SERVICONOMES.bind("Servidor", new ServeImpl());
        this.repository = new Repository();
        this.mquery = new ManagerQuery();
    }

    public void start_sn() throws SQLException {
        repository.start_sn();
        repository.executeUpdate(mquery.registrar_SN("Servidor"));
    }
}
