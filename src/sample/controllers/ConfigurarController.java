package sample.controllers; /**
 * Sample Skeleton for 'Configurar.fxml' Controller Class
 */

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import sample.Main;
import sample.Hotel;
import sample.Voo;

import static sample.Main.appManager;

public class ConfigurarController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private JFXComboBox<String> voo_origem_box,hotel_destino_box,voo_destino_box;

    @FXML
    private JFXTextField precoVoo,precoDiaria,hotel,capacidadeVoo,capacidadeHotel,nomeVoo;

    @FXML
    private JFXDatePicker dataIdaPicker,dataVoltaPicker;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        List<String> cidades;
        try {
            cidades = appManager.getHotelManager().getAllCidades();
            voo_destino_box.setItems(FXCollections.observableArrayList(cidades));
            voo_origem_box.setItems(FXCollections.observableArrayList(cidades));
            hotel_destino_box.setItems(FXCollections.observableArrayList(cidades));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void cadastrarVoo () throws SQLException, RemoteException, NotBoundException {
        Voo voo = new Voo(nomeVoo.getText(),
                voo_origem_box.getValue(),
                voo_destino_box.getValue(),
                Integer.parseInt(capacidadeVoo.getText()),
                dataIdaPicker.getValue().toString(),
                dataVoltaPicker.getValue().toString());
        synchronized(this) {
            appManager.getAeroManager().cadastrarVoo(voo);
        }
    }

    public void cadastrarHospedagem () throws SQLException, RemoteException, NotBoundException {
        Hotel hotel1 = new Hotel(
                hotel.getText(),
                hotel_destino_box.getValue(),
                Integer.parseInt(capacidadeHotel.getText()),
                Double.parseDouble(precoDiaria.getText()));

        synchronized(this) {
            appManager.getHotelManager().cadastrarHospedagem(hotel1);
        }
        }

}

