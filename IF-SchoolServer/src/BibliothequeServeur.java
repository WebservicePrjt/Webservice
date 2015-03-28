import java.rmi.Naming;
public class BibliothequeServeur{

	public static void main(String[] args){
		try{
			Bibliotheque b = new Bibliotheque();
			Naming.rebind("rmi://localhost:1099/IF_School",b);
			b.ajouterLivre(1000, "L'alchimiste", "Paulo Cuelho",3);
			b.ajouterLivre(2000, "L'enfant noir", "blabla",1);
		} catch (Exception e){
			System.out.println("trouble : "+e);
		}
	}
}
