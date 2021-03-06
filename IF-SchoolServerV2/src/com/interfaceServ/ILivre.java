package com.interfaceServ;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.interfaceClient.IEtudiant;
public interface ILivre extends Remote{
	
	public long getISBN() throws RemoteException;
	public String getAuteur() throws RemoteException;
	public String getTitre() throws RemoteException;
	public ArrayList<String> getCommentaires() throws RemoteException;
	public ArrayList<String> getResumes() throws RemoteException;
	public void setTitre(String titre) throws RemoteException;
	public void setISBN(long iSBN) throws RemoteException;
	public void setAuteur(String auteur)  throws RemoteException;
	public void addCommentaires(String commentaire) throws RemoteException;
	public void addResumes(String resume) throws RemoteException;
	public ArrayList<IEtudiant> getListeAttente()throws RemoteException;
	public void setListeAttente(ArrayList<IEtudiant> listeAttente)throws RemoteException;
	public void addListeAttente(IEtudiant obs) throws RemoteException;
	public void removeListeAttente() throws RemoteException;
	public void changeValue(IEtudiant obs,String action)throws RemoteException;
	public int getLivreEmpruntes()throws RemoteException;
	public void setLivreEmpruntes(int livreEmpruntes)throws RemoteException;
	public int getNombreExemplaires()throws RemoteException;
	public void setNombreExemplaires(int nombreExemplaires)throws RemoteException;
	public ArrayList<IEtudiant> getListeEmprunteurs() throws RemoteException;
	public void setListeEmprunteurs(ArrayList<IEtudiant> listeEmprunteurs) throws RemoteException;
	public void addListeEmprunteurs(IEtudiant e) throws RemoteException;
	public String affiche() throws RemoteException;
	public boolean isEmprunte();
	public void setEmprunte(boolean emprunte);
	public Calendar getDateAjout();
	public void setDateAjout(Calendar dateAjout);
	public double getPrix();
	public void setPrix(double prix);
}
