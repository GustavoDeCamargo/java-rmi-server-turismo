/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author a1834240
 */
public class ServeImpl extends UnicastRemoteObject implements InterfaceServ{

    public ServeImpl() throws RemoteException{
        
    }

    
    
    @Override
    public void chamar(String string, InterfaceCli cli)throws RemoteException {
        System.out.println("Servidor recebeu o request = " + string);
        cli.echo(string);
    }
    
}
