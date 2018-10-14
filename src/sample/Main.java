package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      //  Parent root = FXMLLoader.load(getClass().getResource("Servidor.fxml"));
      //  primaryStage.setTitle("Servidor RMI");
      //  primaryStage.setScene(new Scene(root, 300, 275));
       // primaryStage.show();
        DBConnection db = new DBConnection();
        db.connect();

        try {
            Registry sn = LocateRegistry.createRegistry(1099);

            sn.bind("god", new ServeImpl());


            // TODO code application logic here
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}