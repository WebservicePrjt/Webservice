package com.interfaceServ;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import com.interfaceClient.IEtudiant;


public class Bibliotheque extends UnicastRemoteObject implements IBibliotheque  {

	private HashMap<Long, Livre> maBibliotheque;
	
	public Bibliotheque() throws RemoteException {
		super();
		maBibliotheque = new HashMap<Long, Livre>();
	}	
	public ArrayList<ILivre> getMaBibliotheque() throws RemoteException{
		ArrayList<ILivre> res = new ArrayList<ILivre>();
		for (Livre l : maBibliotheque.values()) {
			res.add(l);
		}
		return res;
	}
	public void setMaBibliotheque(HashMap<Long, Livre> maBibliotheque) {
		this.maBibliotheque = maBibliotheque;
	}
	public void ajouterLivre (long ISBN, String titre,String auteur,int nbExemplaires) throws RemoteException{
		Livre livre = new Livre(ISBN,titre,auteur,nbExemplaires);	
		livre.setEmprunte(false);
		maBibliotheque.put(new Long(ISBN),livre);
		livre.setDateAjout(Calendar.getInstance());
		System.out.println("Livre ajouté");
	}
	public void supprimerLivre (long ISBN) throws RemoteException{
		
		maBibliotheque.remove(new Long(ISBN));
		System.out.println("Livre supprimé");
	}
	public ArrayList<ILivre> rechercherLivre (String critere) throws RemoteException{
		ArrayList<ILivre> res = new ArrayList<ILivre>();
		for (Livre l : maBibliotheque.values()) {
			if(l.getAuteur().contains(critere) || l.getTitre().contains(critere))
				res.add(l);
		}
		return res;
	}
	public void ajouterCommentaireLivre(ILivre l,String commentaire) throws RemoteException{
		l.addCommentaires(commentaire);
	}
	public void ajouterResumeLivre(ILivre l,String resume) throws RemoteException{
		l.addResumes(resume);
	}
	public void emprunterLivre(ILivre l, IEtudiant e) throws RemoteException{
		if(l.getNombreExemplaires() > l.getLivreEmpruntes()){
			System.out.println("Vous avez 3 semaines pour rendre le livre");
			l.setLivreEmpruntes(l.getLivreEmpruntes()+1);
			l.setEmprunte(true);
			//l.addListeEmprunteurs(e);
		}
		else{
			System.out.println("Livre indisponible");
			l.addListeAttente(e);
		}
	}
	public void rendreLiver(ILivre l,IEtudiant e) throws RemoteException{
		System.out.println(l.affiche()+" rendu");
		if(l.getListeAttente().size()>0){
			l.removeListeAttente();
		}
		//l.getListeEmprunteurs().remove(e);
		l.setLivreEmpruntes(l.getLivreEmpruntes()-1);
	}
	public ArrayList<Livre> aVendre() throws RemoteException{
		ArrayList<Livre> liste = new ArrayList<Livre>();
		
		int year = Calendar.YEAR;
		for (Livre l : maBibliotheque.values()) {
			int yearAjout = l.getDateAjout().YEAR;
			if( l.isEmprunte() && l.getNombreExemplaires() > 0 && yearAjout+2 <= year )
				liste.add(l);
		}
		return liste;
	}
	public void vendreLivre(ILivre l) throws RemoteException{
		l.setNombreExemplaires(l.getNombreExemplaires()-1);
		if(l.getNombreExemplaires() == 0 ){
			maBibliotheque.remove(l);
		}
		System.out.println("Livre vendu");
	}
	
	
	
	/*public ArrayList<ILivre> livresEmpruntes(IEtudiant e) throws RemoteException {
		ArrayList<ILivre> livres = new ArrayList<ILivre>();
		for (ILivre l : maBibliotheque.values()) {
			if(l.getListeEmprunteurs().contains(e))
				livres.add(l);
		}
		return livres;
	}*/
}
