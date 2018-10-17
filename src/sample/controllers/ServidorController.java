package sample.controllers;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.AppManager;
import sample.Voo;
import sample.core.models.ServicoNomes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static sample.Main.appManager;

public class ServidorController {

    @FXML
    TableView tabelaVoos,tabelaClientes;

    @FXML
    TableColumn con_nomes,nomeColumn, origemColumn, destinoColumn, capacidadeColumn, vendidosColumn,data_idaColumn,
            data_voltaColumn;

    @FXML
    void initialize() {
        try {
            iniciarCamposTabelas(appManager.getAeroManager().getAllVoos(),appManager.getSubsManager().getAllConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void iniciarCamposTabelas(List<Voo> voos,List<ServicoNomes> sn)
    {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        capacidadeColumn.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        vendidosColumn.setCellValueFactory(new PropertyValueFactory<>("vendidos"));
        data_idaColumn.setCellValueFactory(new PropertyValueFactory<>("data_ida"));
        data_voltaColumn.setCellValueFactory(new PropertyValueFactory<>("data_volta"));
        con_nomes.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaVoos.getItems().addAll(FXCollections.observableArrayList(voos));
        tabelaClientes.getItems().addAll(FXCollections.observableArrayList(sn));
    }

    @FXML
    public void atualizarTabelas() throws SQLException {
        iniciarCamposTabelas(appManager.getAeroManager().getAllVoos(),appManager.getSubsManager().getAllConn());
    }
}
