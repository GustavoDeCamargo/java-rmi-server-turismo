package sample.rmi;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sample.Hospedagem;
import sample.Retorno;
import sample.Passagem;
import sample.Interesse;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author a1834240
 */
public interface InterfaceServ extends Remote  {


    String registrarInteresse(Interesse interesse, InterfaceCli cli,String id) throws RemoteException, AlreadyBoundException, NotBoundException;

    Retorno consultar(String tipoConsulta,Passagem passagem, Hospedagem hospedagem) throws RemoteException;

    public void comprarPassagem(Passagem p) throws RemoteException;

    public void comprarHospedagem(Hospedagem hospedagem) throws RemoteException;

    List<String> GetCidades() throws RemoteException;

    List<Interesse> getInteresses(String cliente) throws RemoteException;

    void deletarInteresse(Integer id_interesse) throws  RemoteException;

    List<String> getHoteis() throws RemoteException;
}
