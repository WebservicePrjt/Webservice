import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservee extends Remote{
	
	public void addListeAttente(IEtudiant obs) throws RemoteException;
	public void removeListeAttente() throws RemoteException;
}
