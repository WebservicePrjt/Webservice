package com.interfaceServ;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.interfaceClient.IEtudiant;

public interface IObservee extends Remote{
	
	public void addListeAttente(IEtudiant obs) throws RemoteException;
	public void removeListeAttente() throws RemoteException;
}
