package sample.controllers; /**
 * Sample Skeleton for 'Configurar.fxml' Controller Class
 */

import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.Main;

import javax.swing.*;

public class ConfigurarController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    public void changeScreen() throws GeneralSecurityException {
//        if(tempoResposta.getText().isEmpty())
//            JOptionPane.showMessageDialog(null, "Favor inserir o tempo");
//        else{
            Main.changeScreen("Servidor");
//            TIMEOUT = Integer.parseInt(tempoResposta.getText());
//            new Simulador(false).start();
//        }
    }
}

