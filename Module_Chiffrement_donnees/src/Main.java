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
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*for (Object key : System.getProperties().keySet()) {
			System.out.println(key+" = "+((String)System.getProperty((String)key)));
		}*/
			
		
		byte [] fileDatas= FileFactory.extractionDonneesFichier(pathFichierDepart);		
		System.out.println("Taille de la liste de bytes = "+fileDatas.length);
		
		boolean reussi = FileFactory.ecritureDonneesFichier(pathFichierCrypte, fileDatas);
		System.out.println("Cryptage "+(reussi ? "reussi" : "echoue..."));
		
	}

	
	
	

}
