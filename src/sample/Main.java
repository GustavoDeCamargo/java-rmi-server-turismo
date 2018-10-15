package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.core.models.Voo;


public class Main extends Application {
    private static Stage stage;

    private static Scene Servidor;
    private static Scene Configurar;
    private static Scene sample;

    public static AppManager appManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        appManager = new AppManager();
        appManager.start();
        Voo voo = new Voo("Voo 001","São Paulo","Curitiba",200);
        appManager.getAeroManager().cadastrarVoo(voo);
        stage = primaryStage;

        primaryStage.setTitle("Configuração SD01");

        Parent fxmlConfigurar = FXMLLoader.load(getClass().getResource("resources/Configurar.fxml"));
        Configurar = new Scene(fxmlConfigurar);

        Parent fxmlServidor = FXMLLoader.load(getClass().getResource("resources/Servidor.fxml"));
        Servidor = new Scene(fxmlServidor);

        primaryStage.setTitle("Sistemas Recursos Compartilhados");
        primaryStage.setScene(Configurar);
        primaryStage.show();
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