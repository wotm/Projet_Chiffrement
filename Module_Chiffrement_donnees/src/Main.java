import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sun.management.FileSystem;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	private static boolean osWin = System.getProperty("os.name").toLowerCase().contains("win");
	private static String pathFichiers = System.getProperty("user.dir")+(osWin ? "\\" : "/");
	
	public static String pathFichierDepart = pathFichiers + "BEN-HMIDANE_Rayane_test.jpg";
	public static String pathFichierCrypte = pathFichiers + "fichierCrypte.jpg";
	public static String pathFichierDecrypte = pathFichiers + "fichierDeCrypte.jpg";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*for (Object key : System.getProperties().keySet()) {
			System.out.println(key+" = "+((String)System.getProperty((String)key)));
		}*/
			
		
		//Extraction d'un fichier (de test)
		byte [] fileDatas= FileFactory.extractionDonneesFichier(pathFichierDepart);		
		System.out.println("Taille de la liste de bytes = "+fileDatas.length);
		
		//Cryptage du fichier et ecriture dans un fichier
		byte [] fichierDonneesCrypte = Algorithme_cryptageDonnees.Chiffrement_datas(fileDatas);
		boolean reussi = FileFactory.ecritureDonneesFichier(pathFichierCrypte, fichierDonneesCrypte);
		System.out.println("Cryptage "+(reussi ? "reussi" : "echoue..."));
		
		//Decryptage du fichier et ecriture dans un fichier
		byte [] fichierDonneesDecryptees = Algorithme_cryptageDonnees.Dechiffrement_datas(fichierDonneesCrypte);
		reussi = FileFactory.ecritureDonneesFichier(pathFichierDecrypte, fichierDonneesDecryptees);
		System.out.println("Dechiffrement "+(reussi ? "reussi" : "echoue..."));
		
		verificationEgaliteBytes(fileDatas, fichierDonneesDecryptees);
		
	}

	
	
	
	
	private static void verificationEgaliteBytes(byte[] fileDatas,
			byte[] fichierDonneesDecryptees) {
		for(int i = 0; i<fileDatas.length; i++) {
			if(fileDatas[i] != fichierDonneesDecryptees[i]) {
				System.out.println("Byte no "+i+"non egaux\nbyte depart = "+fileDatas[i]+"\nByte arrivee = "+fichierDonneesDecryptees[i]);
				return;
			}
		}
		System.out.println("Les deux tableaux sont bien egaux");
	}

	
	
	

}
