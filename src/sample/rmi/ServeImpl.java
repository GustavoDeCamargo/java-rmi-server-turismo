/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.rmi;

import sample.core.SubscribeManager;
import sample.core.models.Interesse;
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

import static sample.Main.appManager;
import static sample.rmi.RMIManager.SERVICONOMES;


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
    public void registrarInteresse(String string, InterfaceCli cli) throws RemoteException, AlreadyBoundException {
        try {
            appManager.getSubsManager().cadastrarInteresse(null,cli);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
