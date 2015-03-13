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

	public void notify(ILivre l) throws RemoteException{
		System.out.println("Le livre que vous avez demandé est disponible");
	}
	
}
