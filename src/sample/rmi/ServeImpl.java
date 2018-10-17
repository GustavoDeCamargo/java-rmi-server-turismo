/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.rmi;

import sample.Retorno;
import sample.Passagem;
import sample.Voo;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import static sample.Main.appManager;


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
    public void registrarInteresse(String string, InterfaceCli cli) throws AlreadyBoundException, RemoteException  {
        try {
            appManager.getSubsManager().cadastrarInteresse(null,cli);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Retorno consultar(String tipoConsulta,Passagem passagem) throws RemoteException {
        Retorno r = new Retorno();
        if(tipoConsulta.equals("Passagem")) {
            // Origem,Destino e data ida do Voo
            // Voo e numeroPessoas da Passagem
            Passagem p = new Passagem();
            Voo v = new Voo(null, "São Paulo", "Curitiba", null, "2018-10-31", null);
            p.setVoo(v);
            p.setNumero_pessoas(3);
            try {
                r.setVoos(appManager.getAeroManager().consultarVoos(p));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(tipoConsulta);
        }
        return r;
    }

    @Override
    public List<String> GetCidades() throws RemoteException {
        List<String> cidades = null;
        try {
            cidades = appManager.getHotelManager().getAllCidades();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidades;
    }
}
