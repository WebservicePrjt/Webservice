import java.rmi.*;
import java.util.ArrayList;
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
	public void changeValue(IEtudiant obs)throws RemoteException;
	public int getLivreEmpruntes()throws RemoteException;
	public void setLivreEmpruntes(int livreEmpruntes)throws RemoteException;
	public int getNombreExemplaires()throws RemoteException;
	public void setNombreExemplaires(int nombreExemplaires)throws RemoteException;
}
