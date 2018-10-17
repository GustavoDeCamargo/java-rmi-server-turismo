package sample.controllers;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.AppManager;
import sample.Voo;

import java.sql.SQLException;
import java.util.List;

import static sample.Main.appManager;

public class ServidorController {

    @FXML
    TableView tabelaVoos;

    @FXML
    TableColumn nomeColumn, origemColumn, destinoColumn, capacidadeColumn, vendidosColumn,data_idaColumn,
            data_voltaColumn;

    @FXML
    void initialize() {
        try {
            iniciarCamposTabelas(appManager.getAeroManager().getAllVoos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //  tabelaVoos.getItems().addAll(new Voo("teste",null,null,null,null,null));
    }

    private void iniciarCamposTabelas(List<Voo> voos)
    {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        capacidadeColumn.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        vendidosColumn.setCellValueFactory(new PropertyValueFactory<>("vendidos"));
        data_idaColumn.setCellValueFactory(new PropertyValueFactory<>("data_ida"));
        data_voltaColumn.setCellValueFactory(new PropertyValueFactory<>("data_volta"));
        tabelaVoos.getItems().addAll(FXCollections.observableArrayList(voos));

    }

    @FXML
    public void atualizarTabelas()
    {

    }
}
