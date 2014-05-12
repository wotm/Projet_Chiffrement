import java.io.File;

// Module Fonctionnel -> EN PROD
// Améliorations : peut-etre ajouter un syteme de génération d'une map pour tous les caracteres

public class Main {

	/**
	 * @param args
	 */
	
	private static String cheminFichier ="/home/rayanox/Documents/Droit/cours.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fichier = new File(Main.cheminFichier);
		String nomFichierDÃ©part = fichier.getName(), nomFichierFinal;
		
		nomFichierFinal = Algorithme_Rayane_1.Algo_Rayane_1_Cryptage(nomFichierDÃ©part);
				
		
		System.out.println("Nom du fichier dÃ©part = "+nomFichierDÃ©part);
		System.out.println("Nom du fichier final = "+nomFichierFinal);
		System.out.println("Nom du fichier final decryptÃ© = "+Algorithme_Rayane_1.Algo_Rayane_1_Decryptage(nomFichierFinal));
		
		
	}

}
