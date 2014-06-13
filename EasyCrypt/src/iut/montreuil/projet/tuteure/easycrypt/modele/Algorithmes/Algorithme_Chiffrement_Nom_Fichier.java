package iut.montreuil.projet.tuteure.easycrypt.modele.Algorithmes;


// L'algorithme consiste à ajouter un chiffre (+8) à la lettre et ajouter une lettre sur la droite de chaque lettre déjà présente. 
// Cette dernière lettre ajoutée vaut la lettre de gauche + le premier chiffre ajouté multiplié par 2 (chiffreAdditione*2)

public class Algorithme_Chiffrement_Nom_Fichier {

	
	private static final int chiffreAdditione = 8;
	private static final int limiteMinAscii = 32;
	private static final int limiteMaxAscii = 125;

	
	
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
		char charFinal;
		
		charFinal = (char) ((int) caractere + chiffreAdition);
		if(charFinal > limiteMaxAscii) {			
			charFinal = AdditionnerCaracteresSelonAsciiEtLimite((char)(limiteMinAscii-1), (int) charFinal - limiteMaxAscii);
			// pareil que charFinal = (char) (limiteMinDecimal + ((int) charFinal - limiteMaxDecimal));
			// sauf que la premiere est meilleure car elle gère les cas où chiffreAdition est très grand, ce que ne fait pas l'autre qui renverra peut etre une erreur (exception) et est indecryptable.
		}
		if((int) charFinal == 47) charFinal = (char) 126; // = ~
		
		return charFinal;
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
		char charFinal;
		
		charFinal = (char) ((int) caractere - chiffreAdition);
		
		if((int) charFinal == 126) return charFinal = (char) 47-chiffreAdditione;
		
		if(charFinal < limiteMinAscii) {			
			charFinal = SoustraireCaracteresSelonAsciiEtLimite((char)(limiteMaxAscii+1), (limiteMinAscii - (int) charFinal));
			// pareil que charFinal = (char) (limiteMinDecimal + ((int) charFinal - limiteMaxDecimal));
			// sauf que la premiere est meilleure car elle gère les cas où chiffreAdition est très grand, ce que ne fait pas l'autre qui renverra peut etre une erreur (exception) et est indecryptable.
		}
		
		
		return charFinal;
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
