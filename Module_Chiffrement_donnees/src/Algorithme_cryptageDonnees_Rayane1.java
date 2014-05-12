
public class Algorithme_cryptageDonnees_Rayane1 {
	
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
	public static String Chiffrement_datas(byte[] datas) {
		int i = 0;
		byte[] donnesFinales = new byte[datas.length];
		for (byte b : datas) {
			if(i%2 == 0) {
				byte b_chiffre = Algo_A_chiffrement(b);
			}else {
				byte b_chiffre = Algo_B_chiffrement(b);
			}
			i++;			
		}
		
		return null;
	}
	
	// Pour l'algo B : y = B(x) = x-36.
	private static byte Algo_B_chiffrement(byte b) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Pour l'algo A : y = A(x) = x + 154.
	private static byte Algo_A_chiffrement(byte b) {
		// TODO Auto-generated method stub
		return 0;
	}
	
			

	public static String Dechiffrement_datas(byte[] datas) {
		
		return null;
	}
	
	
	
	
	
	
	
	//******** HELPERS *******
	
	
	public static byte additionnerByte(byte b, int nombre) {
		int result = b + nombre;
		if(result<-127) {
			return additionnerByte(127, result+127);
		}else if(result>127) {
			return additionnerByte(b, nombre);
		}
		return (byte) result;
	}
	
	
}
