package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private static Stage stage;

    private static Scene Servidor;
    private static Scene Configurar;
    private static Scene sample;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        primaryStage.setTitle("Configuração SD01");

        Parent fxmlConfigurar = FXMLLoader.load(getClass().getResource("resources/Configurar.fxml"));
        Configurar = new Scene(fxmlConfigurar);

        Parent fxmlServidor = FXMLLoader.load(getClass().getResource("resources/Servidor.fxml"));
        Servidor = new Scene(fxmlServidor);

        primaryStage.setTitle("Sistemas Recursos Compartilhados");
        primaryStage.setScene(Configurar);
        primaryStage.show();
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

    public static void changeScreen(String scr) {
        switch (scr) {
            case "Servidor":
                stage.setScene(Servidor);
                break;
            case "sample":
                stage.setScene(sample);
                break;
        }
    }
}