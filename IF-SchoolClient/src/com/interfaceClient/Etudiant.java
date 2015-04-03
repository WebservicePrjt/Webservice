package com.interfaceClient;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import com.gui.JDialogAddLivre;
import com.gui.JDialogNotification;
import com.interfaceServ.ILivre;

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
		if (action.equals("remove")){
			System.out.println("Le livre que vous avez demande est maintenant disponible");
			new JDialogNotification("Le livre que vous avez demande est maintenant disponible").setVisible(true);;
			//new JOptionPane("Le livre que vous avez demand est maintenant disponible").setVisible(true);
			//JOptionPane.showMessageDialog(null, );
			//new JDialogAddLivre().setVisible(true);
			
			
		}
		
		else {
			System.out.println("Le livre que vous avez demande est indisponible, vous etes ajoute la liste d'attente");
			//new JDialogAddLivre().setVisible(true);
			new JDialogNotification("Le livre que vous avez demande est indisponible, vous etes ajoute la liste d'attente").setVisible(true);
			//new JOptionPane("Le livre que vous avez demand est maintenant disponible").setVisible(true);
			//JOptionPane.showMessageDialog(null,"Le livre que vous avez demande est indisponible, vous etes ajoute a la liste d'attente");
		}
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
