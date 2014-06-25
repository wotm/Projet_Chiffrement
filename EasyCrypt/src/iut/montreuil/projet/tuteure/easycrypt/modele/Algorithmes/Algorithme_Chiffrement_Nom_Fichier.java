package iut.montreuil.projet.tuteure.easycrypt.modele.Algorithmes;

import java.util.ArrayList;
import java.util.HashMap;


// L'algorithme consiste à ajouter un chiffre (+8) à la lettre et ajouter une lettre sur la droite de chaque lettre déjà présente. 
// Cette dernière lettre ajoutée vaut la lettre de gauche + le premier chiffre ajouté multiplié par 2 (chiffreAdditione*2)

public class Algorithme_Chiffrement_Nom_Fichier {

	private static ArrayList<Character> ASCII = MyASCII.asciiCorrectChars();
	
	private static final int chiffreAdditione = 8;
	private static final int limiteMinAscii = 0;
	private static final int limiteMaxAscii = ASCII.size()-1;

	
	
	//------- Encryptage -------
	
	public static String Algo_Cryptage(String mot) {
		
		String motFinal = "";
		
		for(int i = 0; i<mot.length(); i++) {
			if(i< mot.length()) {
				motFinal += AdditionnerCaracteresSelonAsciiEtLimite(mot.charAt(i), chiffreAdditione);
				motFinal = ajouterLettreADroiteDunChar(motFinal, motFinal.length()-1);
			}
			
		}
		return motFinal;
	}
		
	private static char AdditionnerCaracteresSelonAsciiEtLimite(char caractere, int chiffreAdition) {
		int indexCharFinal;
		
		int currentCaracterIndex = caractere < 0 ? -1 : ASCII.indexOf(caractere );
		
		indexCharFinal = (currentCaracterIndex + chiffreAdition);
		if(indexCharFinal > limiteMaxAscii) {			
			indexCharFinal = AdditionnerCaracteresSelonAsciiEtLimite((char)(limiteMinAscii-1), (int) indexCharFinal - limiteMaxAscii);
			// pareil que charFinal = (char) (limiteMinDecimal + ((int) charFinal - limiteMaxDecimal));
			// sauf que la premiere est meilleure car elle gère les cas où chiffreAdition est très grand, ce que ne fait pas l'autre qui renverra peut etre une erreur (exception) et est indecryptable.
			
		}else {
			indexCharFinal = ASCII.get(indexCharFinal);
		}
		return (char) indexCharFinal;
	}
	
	private static String ajouterLettreADroiteDunChar(String mot, int indiceChar) {
		
		String motFinalString, moitie1, moitie2;
		moitie1 = mot.substring(0, indiceChar+1);
		moitie2 = mot.substring(indiceChar+1, mot.length());
		moitie1+=(char) AdditionnerCaracteresSelonAsciiEtLimite(moitie1.charAt(moitie1.length()-1),chiffreAdditione*2);//Ajout du chiffre
		motFinalString = moitie1.concat(moitie2);
		
		return motFinalString;
	}
	
	
	//------- Décryptage -------
	
	
	public static String Algo_Decryptage(String mot) {
		String motFinal = mot;
		int i = 0;
		while(i < motFinal.length()-1) {
			motFinal = retirerLettreADroiteDunChar(motFinal, i);
			char [] tabMot = motFinal.toCharArray();
			tabMot[i] = SoustraireCaracteresSelonAsciiEtLimite(tabMot[i], chiffreAdditione);
			motFinal = String.valueOf(tabMot);
			i++;
			
		}
		return motFinal;
	}
	
	private static char SoustraireCaracteresSelonAsciiEtLimite(char caractere, int chiffreAdition) {
		int indexCharFinal;
		
		int currentCaracterIndex = ASCII.contains(caractere) ? ASCII.indexOf(caractere ) : ASCII.size();
		
		indexCharFinal = ((int) currentCaracterIndex - chiffreAdition);
				
		if(indexCharFinal < limiteMinAscii) {			
			indexCharFinal = SoustraireCaracteresSelonAsciiEtLimite((char)(-1), (limiteMinAscii - (int) indexCharFinal));
			// pareil que charFinal = (char) (limiteMinDecimal + ((int) charFinal - limiteMaxDecimal));
			// sauf que la premiere est meilleure car elle gère les cas où chiffreAdition est très grand, ce que ne fait pas l'autre qui renverra peut etre une erreur (exception) et est indecryptable.
		}else
			indexCharFinal = ASCII.get(indexCharFinal);
		
		
		return (char) indexCharFinal;
	}
	
	private static String retirerLettreADroiteDunChar(String mot, int indiceChar) {
		
		if(indiceChar <mot.length()-1) {
			String motFinalString, moitie1, moitie2;
			moitie1 = mot.substring(0, indiceChar+1);
			moitie2 = mot.substring(indiceChar+1, mot.length());
			
			if(moitie2.length() > 0) {
				moitie2 = moitie2.substring(1, moitie2.length());// Retrait du chiffre			
			}
			motFinalString = moitie1.concat(moitie2);
			
			return motFinalString;
		} 
		
		
		return mot;
	}
	
}
