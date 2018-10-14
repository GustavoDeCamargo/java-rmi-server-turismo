package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.database.DBConnection;
import sample.database.ManagerQuery;
import sample.database.Repository;
import sample.rmi.ServeImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static Registry SERVICONOMES;

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("resources/Servidor.fxml"));
       // primaryStage.setTitle("Servidor RMI");
       // primaryStage.setScene(new Scene(root, 300, 275));
       // primaryStage.show();
        DBConnection db = new DBConnection();
        db.connect();
        Repository repository = new Repository();
        ManagerQuery mquery = new ManagerQuery();
        System.out.println(mquery.registrar_SN("batata"));
      //  ResultSet rs = repository.executeQuery();
       // System.out.println(rs.getString("nome"));
        try {
            SERVICONOMES = LocateRegistry.createRegistry(1099);
            SERVICONOMES.bind("Servidor", new ServeImpl());
            repository.start_sn();
            repository.executeUpdate(mquery.registrar_SN("Servidor"));
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}