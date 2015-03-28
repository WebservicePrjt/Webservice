package com.interfaceClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.interfaceServ.ILivre;

public interface IEtudiant extends Remote {
	public void notify(ILivre l,String action) throws RemoteException;
	
}
