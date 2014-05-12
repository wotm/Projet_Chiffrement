import java.io.File;

// Module Fonctionnel -> EN PROD
// Am�liorations : peut-etre ajouter un syteme de g�n�ration d'une map pour tous les caracteres

public class Main {

	/**
	 * @param args
	 */
	
	private static boolean osWin = System.getProperty("os.name").toLowerCase().contains("win");
	private static String pathFichiers = System.getProperty("user.dir")+(osWin ? "\\" : "/");
	
	public static String pathFichierDepart = pathFichiers + "BEN-HMIDANE_Rayane_test.jpg";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fichier = new File(pathFichierDepart);
		String nomFichierDépart = fichier.getName(), nomFichierFinal;
		
		nomFichierFinal = Algorithme_Chiffrement_Nom_Fichier.Algo_Cryptage(nomFichierDépart);
				
		
		System.out.println("Nom du fichier départ = "+nomFichierDépart);
		System.out.println("Nom du fichier final = "+nomFichierFinal);
		System.out.println("Nom du fichier final decrypté = "+Algorithme_Chiffrement_Nom_Fichier.Algo_Decryptage(nomFichierFinal));
		
		
	}

}
