/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.rmi;

import sample.database.ManagerQuery;
import sample.database.Repository;
import sample.rmi.InterfaceCli;
import sample.rmi.InterfaceServ;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Main.SERVICONOMES;

/**
 *
 * @author a1834240
 */
public class ServeImpl extends UnicastRemoteObject implements InterfaceServ {

    private Repository repository;
    private ManagerQuery mquery;

    public ServeImpl() throws RemoteException{
        this.repository = new Repository();
        this.mquery = new ManagerQuery();
    }


    @Override
    public void registrarInteresse(String string, InterfaceCli cli) throws RemoteException, AlreadyBoundException, NotBoundException {
        System.out.println("Servidor recebeu o request = " + string);
        ResultSet rs = null;
        String ref_cliente = null;
        try {
            rs = repository.executeQuery("SELECT max(id) as id from servico_nomes;");
            ref_cliente = "Cliente" + rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        registrarObjetoRemoto(ref_cliente,cli);
        notificarCliente(ref_cliente," gostariamos de notificar o senhor a respeito do seu interesse");
    }

    public void notificarCliente(String ref_client, String msg) throws RemoteException, NotBoundException {
        InterfaceCli com = (InterfaceCli)SERVICONOMES.lookup(ref_client);
        com.notificar(ref_client+msg);
    }

    private void registrarObjetoRemoto(String ref_client, InterfaceCli cliente) throws AlreadyBoundException, RemoteException {
        SERVICONOMES.bind(ref_client, cliente);
        try {
            repository.executeUpdate(mquery.registrar_SN(ref_client));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
