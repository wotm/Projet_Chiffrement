package iut.montreuil.projet.tuteure.easycrypt.modele;

public class ManualText {

	private static boolean isFrench = System.getProperty("user.language").equals("fr");
	
	public static String GetUseStartingManual() {
		if(isFrench) {
			return "\t /!\\ Utilisation du Widget /!\\\n\n" +
					"La fonctionnalité du widget vous permet de cacher discrètement vos fichiers en 1 seconde pour ne pas que vos proches n'ai accès à vos fichiers douteux (fonctionnement expliqué dans le manuel)\n\n" +
					"--> Allez dans la configuration et sélectionnez des fichiers que vous voules configurer pour être chiffrés et déchiffrés lors de l'appui sur le bouton du bureau";
		}else {
			return "\t /!\\ How to use the Widget (Desktop button) /!\\\n\n" +
					"The wigdet functionnality let you hide discreetly your suspicious files in a very short delay, just one click and it's done ! \n\n" +
					"--> Go to the configuration menu and select the files you want to be prepared to be crypted when you will click on the desktop widget.";
		}
	}
	
	
	
	public static String GetUseManual(){
		if(isFrench) {
			return "\t /!\\ Utilisation du Widget /!\\\n\n" +
					"La fonctionnalité du widget vous permet de cacher discrètement vos fichiers en 1 seconde pour ne pas que vos proches n'ai accès à vos fichiers douteux (fonctionnement expliqué dans le manuel)\n\n" +
					"--> Allez dans la configuration et sélectionnez des fichiers que vous voules configurer pour être chiffrés et déchiffrés lors de l'appui sur le bouton du bureau";
		}else {
			return "\t /!\\ How to use the Widget (Desktop button) /!\\\n\n" +
					"The wigdet functionnality let you hide discreetly your suspicious files in a very short delay, just one click and it's done ! \n\n" +
					"--> Go to the configuration menu and select the files you want to be prepared to be crypted when you will click on the desktop widget.";
		}
	}
	
}
