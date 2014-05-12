import java.io.File;

// Module Fonctionnel -> EN PROD
// Am�liorations : peut-etre ajouter un syteme de g�n�ration d'une map pour tous les caracteres

public class Main {

	/**
	 * @param args
	 */
	
	private static String cheminFichier ="/home/rayanox/Documents/Droit/cours.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fichier = new File(Main.cheminFichier);
		String nomFichierDépart = fichier.getName(), nomFichierFinal;
		
		nomFichierFinal = Algorithme_Rayane_1.Algo_Rayane_1_Cryptage(nomFichierDépart);
				
		
		System.out.println("Nom du fichier départ = "+nomFichierDépart);
		System.out.println("Nom du fichier final = "+nomFichierFinal);
		System.out.println("Nom du fichier final decrypté = "+Algorithme_Rayane_1.Algo_Rayane_1_Decryptage(nomFichierFinal));
		
		
	}

}
