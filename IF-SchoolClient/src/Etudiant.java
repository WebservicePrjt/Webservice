import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//observateur
public class Etudiant extends UnicastRemoteObject implements IEtudiant{
	
	private String nom;
	private String prenom;
	
	public Etudiant() throws RemoteException {
		super();
	}
	
	public Etudiant(String nom, String prenom) throws RemoteException{
		this.nom = nom;
		this.prenom = prenom;
	}

	public void notify(ILivre l,String action) throws RemoteException{
		if (action.equals("remove"))
			System.out.println("Le livre que vous avez demandé est maintenant disponible");
		else
			System.out.println("Le livre que vous avez demandé est indisponible, vous etes ajouté à la liste d'attente");
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	
	
}
