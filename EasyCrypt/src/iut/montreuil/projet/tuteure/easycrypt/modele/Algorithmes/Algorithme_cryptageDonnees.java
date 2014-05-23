package iut.montreuil.projet.tuteure.easycrypt.modele.Algorithmes;

public class Algorithme_cryptageDonnees {
	
	//On modifie chaque byte de sorte à ce qu'il y ait un algo pour un byte puis un autre pour le suivant, puis on reprend le premier algo pour le suivant.
	// Il y a en fait deux algo qui s'execute chacun un byte sur deux.
	// Exemple : Si A est le premier algo, et B le second, et que AAAA, correspond à 4 bytes chiffrés par l'algo A,
	// 			 alors l'algo ici est : ABABAB ... 
	// L'algo A est le suivant : Soit x un byte non chiffré, et y le byte chiffré par la transformation A(x), 
	// y = A(x) = x + 154.
	// Pour l'algo B : y = B(x) = x-36.
	
	// Important : On sait qu'un byte peut avoir une valeur allant de 0 à 255. Ainsi, lorsque l'on dépasse cette valeur,
	//             on doit revenir par l'autre extrémité (une méthode d'ajout automatique le permet).
	//      	   Exemple : Soit b un byte avec b = 21. On utilise l'algo B.
	//						 B(b) = b - 36 = 21 - 36 = 255 - 15 = 240.
	
	// (Optionnel) => On a un indice i qui commence au premier byte et qui sera incrémenté de 1 à chaque nouveau byte.
	//                on additionnera cette valeur aux algo déjà cités. Par exemple
	//                Par exemple, pour l'algo A : A(x) = x + 154 + i.
								// pour l'algo B : B(x) = x - 36 + i 
	public static byte [] Chiffrement_datas(byte[] datas) {
		
		byte[] donnesFinales = new byte[datas.length];
		for (int i = 0; i< datas.length; i++) {
			byte b = datas[i];
			byte b_chiffre;
			if(i%2 == 0) {
				b_chiffre = Algo_A_chiffrement(b);
			}else {
				b_chiffre = Algo_B_chiffrement(b);
			}
			donnesFinales[i] = b_chiffre;
		}
		return donnesFinales;
	}
	
	// Pour l'algo B : y = B(x) = x-36.
	private static byte Algo_B_chiffrement(byte b) {
		return additionnerByte(b, -36);
	}

	// Pour l'algo A : y = A(x) = x + 154.
	private static byte Algo_A_chiffrement(byte b) {
		return additionnerByte(b, 154);
	}
	
			

	public static byte [] Dechiffrement_datas(byte[] datas) {
		byte[] donnesFinales = new byte[datas.length];
		for (int i = 0; i< datas.length; i++) {
			byte b = datas[i];
			byte b_chiffre;
			if(i%2 == 0) {
				b_chiffre = Algo_A_Dechiffrement(b);
			}else {
				b_chiffre = Algo_B_Dechiffrement(b);
			}
			donnesFinales[i] = b_chiffre;	
		}
		return donnesFinales;
	}
		
	//Inverse de l'algo de chiffrement B
	private static byte Algo_B_Dechiffrement(byte b) {
		return additionnerByte(b, 36);
	}

	//Inverse de l'algo de chiffrement A
	private static byte Algo_A_Dechiffrement(byte b) {
		return additionnerByte(b, -154);
	}

	
	
	
	
	//******** HELPERS *******
	
	
	public static byte additionnerByte(byte b, int nombre) {
		int result = b + nombre;
		if(result<-128) {
			return additionnerByte((byte)127, result+129);
		}else if(result>127) {
			return additionnerByte((byte)-128, result-128);
		}
		return (byte) result;
	}
	
	
}
