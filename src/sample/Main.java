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

    private static Stage stage;

    private static Scene Servidor;
    private static Scene Configurar;
    private static Scene sample;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        primaryStage.setTitle("Configuração SD01");

        Parent fxmlConfigurar = FXMLLoader.load(getClass().getResource("/sample/Configurar.fxml"));
        Configurar = new Scene(fxmlConfigurar);

        Parent fxmlServidor = FXMLLoader.load(getClass().getResource("/sample/Servidor.fxml"));
        Servidor = new Scene(fxmlServidor);


        primaryStage.setTitle("Sistemas Recursos Compartilhados");
        primaryStage.setScene(Configurar);
        primaryStage.show();


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

    public static void main(String[] args) {
        launch(args);
    }
}