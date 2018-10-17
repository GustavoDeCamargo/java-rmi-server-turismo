/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.rmi;

import sample.Hospedagem;
import sample.Retorno;
import sample.Passagem;
import sample.Interesse;
import sample.database.ManagerQuery;
import sample.database.Repository;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
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
    public String registrarInteresse(Interesse interesse, InterfaceCli cli,String id) throws AlreadyBoundException, RemoteException  {
        String retorno = null;
        synchronized(this) {
            try {
                retorno = appManager.getSubsManager().cadastrarInteresse(interesse, cli, id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            return retorno;
        }
        }

    @Override
    public Retorno consultar(String tipoConsulta, Passagem passagem, Hospedagem hospedagem) throws RemoteException {
        Retorno r = new Retorno();
        synchronized(this) {
            if (tipoConsulta.equals("Passagem")) {
                try {
                    r.setVoos(appManager.getAeroManager().consultarVoos(passagem));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (tipoConsulta.equals("Hospedagem")) {
                try {
                    r.setHoteis(appManager.getHotelManager().consultarHoteis(hospedagem));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return r;
    }

    @Override
    public List<String> GetCidades() throws RemoteException {
        List<String> cidades = null;
        synchronized(this) {
            try {
                cidades = appManager.getHotelManager().getAllCidades();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cidades;
    }

    @Override
    public void comprarPassagem(Passagem p) throws RemoteException {
        synchronized(this) {
            try {
                appManager.getAeroManager().efetuarCompraPassagem(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void comprarHospedagem(Hospedagem hospedagem) throws RemoteException {
        synchronized(this) {
            try {
                appManager.getHotelManager().efetuarCompraHospedagem(hospedagem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Interesse> getInteresses(String cliente) throws RemoteException {
        List<Interesse> interesses = null;
        synchronized(this) {
            try {
                interesses = appManager.getSubsManager().getInteressesPeloCliente(cliente);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return interesses;
    }

    @Override
    public void deletarInteresse(Integer id_interesse) throws RemoteException {
        synchronized(this) {
            try {
                appManager.getSubsManager().deleteInteresse(id_interesse);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
