package com.interfaceServ;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.interfaceClient.IEtudiant;

public class Livre extends UnicastRemoteObject implements ILivre,IObservee{
	private String titre;
	private	long ISBN;
	private String auteur;
	private ArrayList<String> commentaires;
	private ArrayList<String> resumes;
	private int nombreExemplaires;
	private int livreEmpruntes;
	private boolean emprunte;
	private Calendar dateAjout;
	private double prix;
	private ArrayList<IEtudiant> listeAttente;
	private ArrayList<IEtudiant> listeEmprunteurs;

	public Livre() throws RemoteException{
		super();
		listeAttente = new ArrayList<IEtudiant>();
	}
	
	public Livre(long ISBN) throws RemoteException{
		this.ISBN = ISBN;
	}
	
	public Livre( long ISBN, String titre, String auteur,int nbExemplaires) throws RemoteException{
		this.titre = titre;
		this.ISBN = ISBN;
		this.auteur = auteur;
		this.commentaires = new ArrayList<String>();
		this.resumes = new ArrayList<String>();
		this.listeAttente = new ArrayList<IEtudiant>();
		this.nombreExemplaires = nbExemplaires;
		this.livreEmpruntes = 0;
	}
	
	public ArrayList<String> getCommentaires() throws RemoteException{
		return commentaires;
	}
	
	public void addCommentaires(String commentaire) throws RemoteException{
		this.commentaires.add(commentaire);
	}
	
	public ArrayList<String> getResumes() throws RemoteException{
		return resumes;
	}
	
	public void addResumes(String resume) throws RemoteException{
		this.resumes.add(resume);
	}
	
	public long getISBN() throws RemoteException{
		return ISBN;
	}
	
	public String getAuteur() throws RemoteException{
		return auteur;
	}
	
	public String getTitre() throws RemoteException{
		return titre;
	}
	
	public void setTitre(String titre) throws RemoteException{
		this.titre = titre;
	}
	
	public void setISBN(long iSBN) throws RemoteException{
		ISBN = iSBN;
	}
	
	public void setAuteur(String auteur) throws RemoteException{
		this.auteur = auteur;
	}
	
	public void addListeAttente(IEtudiant obs) throws RemoteException{
		listeAttente.add(obs);
		System.out.println("Etudiant rajouté a la liste d'attente");
		changeValue(obs,"add");
	}

	public void removeListeAttente() throws RemoteException {
		IEtudiant obs = listeAttente.get(0);
		listeAttente.remove(obs);
		System.out.println("Etudiant supprime a la liste d'attente");
		changeValue(obs,"remove");
	}
	
	public void changeValue(IEtudiant obs,String action){
		try {
			switch (action){
			case "add" :
				obs.notify(this,action);
				break;
			case "remove" :
				obs.notify(this,action);
				break;
			default:
				break;	
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public int getLivreEmpruntes() throws RemoteException{
		return livreEmpruntes;
	}
	
	public void setLivreEmpruntes(int livreEmpruntes) throws RemoteException{
		this.livreEmpruntes = livreEmpruntes;
	}
	
	public int getNombreExemplaires() throws RemoteException{
		return nombreExemplaires;
	}
	
	public void setNombreExemplaires(int nombreExemplaires) throws RemoteException{
		this.nombreExemplaires = nombreExemplaires;
	}
	
	public ArrayList<IEtudiant> getListeAttente() throws RemoteException{
		return listeAttente;
	}
	
	public void setListeAttente(ArrayList<IEtudiant> listeAttente) throws RemoteException{
		this.listeAttente = listeAttente;
	}
	
	public ArrayList<IEtudiant> getListeEmprunteurs() throws RemoteException{
		return listeEmprunteurs;
	}
	
	public void setListeEmprunteurs(ArrayList<IEtudiant> listeEmprunteurs) throws RemoteException{
		this.listeEmprunteurs = listeEmprunteurs;
	}
	
	public void addListeEmprunteurs(IEtudiant e) throws RemoteException{
		this.listeEmprunteurs.add(e);
	}
	
	public String affiche(){
		return "Livre [ Titre = " + this.titre + ", ISBN = " + this.ISBN + ", Auteur = "
				+ this.auteur + " ]";
	}

	public boolean isEmprunte() {
		return emprunte;
	}

	public void setEmprunte(boolean emprunte) {
		this.emprunte = emprunte;
	}

	public Calendar getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Calendar dateAjout) {
		this.dateAjout = dateAjout;
	}

	@Override
	public double getPrix() {
		
		return prix;
	}

	@Override
	public void setPrix(double prix) {
		this.prix = prix;	
	}
}
