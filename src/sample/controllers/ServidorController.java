package sample.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Voo;

public class ServidorController {

    @FXML
    TableView tabelaVoos;

    @FXML
    TableColumn nomeColumn, origemColumn, destinoColumn, capacidadeColumn, vendidosColumn,data_idaColumn,
            data_voltaColumn;

    @FXML
    void initialize() {

      //  tabelaVoos.getItems().addAll(new Voo("teste",null,null,null,null,null));
    }

    private void iniciarCamposTabelas()
    {
        Voo v = new Voo("teste","São Paulo","Curitiba",2,"2018-10-11","2018-10-11");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        capacidadeColumn.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        vendidosColumn.setCellValueFactory(new PropertyValueFactory<>("vendidos"));
        data_idaColumn.setCellValueFactory(new PropertyValueFactory<>("data_ida"));
        data_voltaColumn.setCellValueFactory(new PropertyValueFactory<>("data_volta"));
        v.setVendidos(200);
        tabelaVoos.getItems().addAll(v);

    }

    @FXML
    public void atualizarTabelas()
    {
        iniciarCamposTabelas();
    }
}
