
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEtudiant extends Remote {
	public void notify(ILivre l) throws RemoteException;
}
