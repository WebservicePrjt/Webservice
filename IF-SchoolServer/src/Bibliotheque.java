import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Bibliotheque extends UnicastRemoteObject implements IBibliotheque  {
	private HashMap<Long, Livre> maBibliotheque;
	
	public Bibliotheque() throws RemoteException {
		super();
		maBibliotheque = new HashMap<Long, Livre>();
	}	
		
	public void ajouterLivre (long ISBN, String titre,String auteur) throws RemoteException{
		Livre livre = new Livre(ISBN,titre,auteur);		
		maBibliotheque.put(new Long(ISBN),livre);
		System.out.println("Livre ajouté");
	}
	
	public void supprimerLivre (long ISBN) throws RemoteException{
		Livre livre = new Livre(ISBN);
		maBibliotheque.remove(new Long(ISBN));
		System.out.println("Livre supprimé");
	}
	
	public ArrayList<ILivre> rechercherLivre (String critere) throws RemoteException{
		ArrayList<ILivre> res = new ArrayList<ILivre>();
		for (Livre l : maBibliotheque.values()) {
			if(l.getAuteur().contains(critere))
				res.add(l);
			if(l.getTitre().contains(critere))
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
		}
		else{
			System.out.println("Livre indisponible");
			l.addListeAttente(e);
		}
	}
	public void rendreLiver(ILivre l) throws RemoteException{
		System.out.println(l.toString()+" rendu");
		if(l.getListeAttente().size()>0){
			l.removeListeAttente();
		}
		l.setLivreEmpruntes(l.getLivreEmpruntes()-1);
	}
}
