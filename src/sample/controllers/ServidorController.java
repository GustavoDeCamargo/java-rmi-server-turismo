package sample.controllers;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Voo;
import sample.core.models.Hotel;
import sample.Interesse;
import sample.core.models.ServicoNomes;

import java.sql.SQLException;
import java.util.List;

import static sample.Main.appManager;

public class ServidorController {

    @FXML
    TableView tabelaVoos,tabelaClientes,tabelaHospedagens,tabelaNotificacoes;

    @FXML
    TableColumn con_nomes,nomeColumn, origemColumn, destinoColumn, capacidadeColumn, vendidosColumn,data_idaColumn,
            data_voltaColumn,h_nomeColumn,h_localColumn,h_vagasColumn,h_ocupacaoColumn,h_precoColumn;
    @FXML
    TableColumn i_destinoColumn,i_origemColumn,i_tipoColumn,i_refColumn,i_nomeColumn,i_precomaxColumn;
    ;

    @FXML
    void initialize() {
        try {
            iniciarCamposTabelas(appManager.getAeroManager().getAllVoos(),
                    appManager.getSubsManager().getAllConn(),
                    appManager.getHotelManager().getAllHoteis(),
                    appManager.getSubsManager().getAllInteresses());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void iniciarCamposTabelas(List<Voo> voos, List<ServicoNomes> sn, List<Hotel> hoteis, List<Interesse> interesses)
    {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        capacidadeColumn.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        vendidosColumn.setCellValueFactory(new PropertyValueFactory<>("vendidos"));
        data_idaColumn.setCellValueFactory(new PropertyValueFactory<>("data_ida"));
        data_voltaColumn.setCellValueFactory(new PropertyValueFactory<>("data_volta"));
        h_nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        h_localColumn.setCellValueFactory(new PropertyValueFactory<>("local"));
        h_vagasColumn.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        h_ocupacaoColumn.setCellValueFactory(new PropertyValueFactory<>("ocupacao"));
        h_precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
        con_nomes.setCellValueFactory(new PropertyValueFactory<>("nome"));
        i_nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        i_refColumn.setCellValueFactory(new PropertyValueFactory<>("ref_cliente"));
        i_tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_interesse"));
        i_origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        i_destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        i_precomaxColumn.setCellValueFactory(new PropertyValueFactory<>("preco_maximo"));
        tabelaVoos.getItems().addAll(FXCollections.observableArrayList(voos));
        tabelaClientes.getItems().addAll(FXCollections.observableArrayList(sn));
        tabelaHospedagens.getItems().addAll(FXCollections.observableArrayList(hoteis));
        tabelaNotificacoes.getItems().addAll(FXCollections.observableArrayList(interesses));
    }

    @FXML
    public void atualizarTabelas() throws SQLException {
        tabelaVoos.getItems().clear();
        tabelaClientes.getItems().clear();
        tabelaHospedagens.getItems().clear();
        tabelaNotificacoes.getItems().clear();
        iniciarCamposTabelas(appManager.getAeroManager().getAllVoos(),
                appManager.getSubsManager().getAllConn(),
                appManager.getHotelManager().getAllHoteis(),
                appManager.getSubsManager().getAllInteresses());
    }
}
