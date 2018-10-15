package sample;

import sample.core.AeroManager;
import sample.core.SubscribeManager;
import sample.core.TrivagoManager;
import sample.database.DBConnection;
import sample.rmi.RMIManager;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class AppManager {
    private final AeroManager aeroManager;
    private final SubscribeManager subsManager;
    private final TrivagoManager hotelManager;
    private final RMIManager rmiManager;

    DBConnection dbConnection;

    public AppManager() throws AlreadyBoundException, RemoteException {
        this.dbConnection = new DBConnection();
        dbConnection.connect();
        this.aeroManager = new AeroManager();
        this.subsManager = new SubscribeManager();
        this.hotelManager = new TrivagoManager();
        this.rmiManager = new RMIManager();
    }

    public AeroManager getAeroManager() {
        return aeroManager;
    }

    public SubscribeManager getSubsManager() {
        return subsManager;
    }

    public TrivagoManager getHotelManager() {
        return hotelManager;
    }

    public void start() throws SQLException {
        rmiManager.start_sn();
    }
}
