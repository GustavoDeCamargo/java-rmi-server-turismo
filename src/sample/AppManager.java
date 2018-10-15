package sample;

import sample.core.AeroManager;
import sample.core.SubscribeManager;
import sample.core.TrivagoManager;
import sample.database.DBConnection;

public class AppManager {
    private final AeroManager aeroManager;
    private final SubscribeManager subsManager;
    private final TrivagoManager hotelManager;

    DBConnection dbConnection;

    public AppManager() {
        this.dbConnection = new DBConnection();
        dbConnection.connect();
        this.aeroManager = new AeroManager();
        this.subsManager = new SubscribeManager();
        this.hotelManager = new TrivagoManager();
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

}
