package com.interfaceServ;
import java.rmi.*;
import java.util.*;

import com.interfaceClient.IEtudiant;
public interface IBibliotheque extends Remote {
	public void ajouterLivre (long ISBN, String titre,String auteur,int nbExemplaires) throws RemoteException;
	public void supprimerLivre (long ISBN) throws RemoteException;
	public ArrayList<ILivre> rechercherLivre (String critere) throws RemoteException;
	public void ajouterCommentaireLivre(ILivre l,String commentaire) throws RemoteException;
	public void ajouterResumeLivre(ILivre l,String resume) throws RemoteException;
	public void emprunterLivre(ILivre l, IEtudiant e) throws RemoteException;
	public void rendreLiver(long ISBN) throws RemoteException;
	//public ArrayList<ILivre> livresEmpruntes(IEtudiant e) throws RemoteException;
	public ArrayList<ILivre> getMaBibliotheque() throws RemoteException;
}
