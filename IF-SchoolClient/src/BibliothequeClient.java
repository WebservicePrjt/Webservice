import java.rmi.*;
import java.util.*;
public class BibliothequeClient{
	
	public static void main(String[] args){	
		try{
			IBibliotheque b = (IBibliotheque) Naming.lookup("rmi://localhost:1099/IF_School");
			System.out.println("Bienvenue sur IF-School, votre bibliothèque en ligne");
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez saisir votre nom et prenom s'il vous plait");
			System.out.println("Nom : ");
			String nom = sc.next();
			System.out.println("Prenom :");
			String prenom = sc.next();
			Etudiant e = new Etudiant(nom,prenom);
			boolean sortie = false;
			while(!sortie){
				System.out.println("Liste des fonction : ");
				System.out.println("1 - Ajouter un livre");
				System.out.println("2 - Supprimer un livre");
				System.out.println("3 - Rechercher un livre");
				System.out.println("4 - Ajouter un commentaire à un livre");
				System.out.println("5 - Ajouter un résumé à un livre");
				System.out.println("6 - Emprunter un livre");
				System.out.println("7 - Retourner un livre");
				System.out.println("0 - Quitter");
				int choix = sc.nextInt();
				switch (choix) {
					case 1:
						ajouterLivre(b,sc);
						break;
					case 2:
						supprimerLivre(b,sc);
						break;
					case 3:
						rechercherLivre(b,sc);
						break;
					case 4:
						ajouterCommentaire(b, sc, e);
						break;
					case 5:
						ajouterResume(b, sc, e);
						break;
					case 6:
						emprunterLivre(b,sc,e);
						break;
					/*case 7:
						retournerLiver(b,sc,e);
						break;*/
					case 0:
						System.out.println("A bientot sur IF-School");
						sortie = true;
						break;
					default:
						System.out.println("Choix incorrect, veuillez réessayer");
						break;
				}
			}
			sc.close();
			
		} catch (Exception e){
			System.out.println("trouble : "+e);
		}
	}

	private static void retournerLiver(IBibliotheque b, Scanner sc,Etudiant e) throws RemoteException {
		/*ArrayList<ILivre> livres = b.livresEmpruntes(e);
		for (int i=0;i<livres.size();i++) {
			System.out.println((i+1)+" - "+livres.get(i).affiche());
		}*/
		System.out.println("ISBN du livre voulez vous remettre ?");
		long choix = sc.nextLong();
		
		//b.rendreLiver(choix, e);
	}

	private static void emprunterLivre(IBibliotheque b, Scanner sc,Etudiant e) throws RemoteException {
		ArrayList<ILivre> livres = rechercherLivre(b, sc);
		System.out.println("Veuillez saisir le numero du livre que vous voulez emprunter");
		int choix = sc.nextInt();
		b.emprunterLivre(livres.get(choix-1),e);
	}
	
	private static void ajouterCommentaire(IBibliotheque b, Scanner sc,Etudiant e) throws RemoteException {
		ArrayList<ILivre> livres = rechercherLivre(b, sc);
		System.out.println("Veuillez saisir le numero du livre que vous voulez commenter");
		int choix = sc.nextInt();
		System.out.println("Commentaire : ");
		String comm = sc.next();
		comm = e.getNom()+" "+ e.getPrenom() +" : "+ comm;
		b.ajouterCommentaireLivre(livres.get(choix-1), comm);
	}
	
	private static void ajouterResume(IBibliotheque b, Scanner sc,Etudiant e) throws RemoteException {
		ArrayList<ILivre> livres = rechercherLivre(b, sc);
		System.out.println("Veuillez saisir le numero du livre que vous voulez emprunter");
		int choix = sc.nextInt();
		System.out.println("Resumé : ");
		String comm = sc.next();
		comm = e.getNom()+" "+ e.getPrenom() +" : "+ comm;
		b.ajouterCommentaireLivre(livres.get(choix-1), comm);
	}

	private static ArrayList<ILivre> rechercherLivre(IBibliotheque b, Scanner sc) throws RemoteException {
		System.out.println("Veuillez saisir le titre ou l'auteur du livre");
		String critere = sc.next();
		ArrayList<ILivre> livres = b.rechercherLivre(critere);
		System.out.println("Résultats de la recherche ("+livres.size()+" livre(s) trouvés)");
		for (int i=0;i<livres.size();i++) {
			System.out.println((i+1)+" - "+livres.get(i).affiche());
		}
		return livres;
	}

	private static void supprimerLivre(IBibliotheque b, Scanner sc) throws RemoteException {
		System.out.println("Veuillez saisir le ISBN du livre");
		long isbn = sc.nextLong();
		b.supprimerLivre(isbn);
	}
	
	private static void ajouterLivre(IBibliotheque b,Scanner sc) throws RemoteException {
		System.out.println("Veuillez saisir le ISBN du livre");
		long isbn = sc.nextLong();
		System.out.println("Veuillez saisir le titre du livre");
		String titre = sc.next();
		System.out.println("Veuillez saisir l'auteur de ce livre");
		String auteur = sc.next();
		System.out.println("Combien d'exemplaire en avez vous ?");
		int nbExemplaire = sc.nextInt();
		b.ajouterLivre(isbn, titre, auteur,nbExemplaire);
		System.out.println("Votre livre a bien été ajouté");
	}
	
}
