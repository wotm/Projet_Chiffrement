package iut.montreuil.projet.tuteure.easycrypt.modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import iut.montreuil.projet.tuteure.easycrypt.MainActivity;
import android.content.Context;
import android.os.AsyncTask;

public class TacheChiffrement extends AsyncTask<String, String, Boolean>{

	
	public static enum TypeTache {
		ByWidget, ByGUI
	}
	private Context context;
	private TypeTache typeTache;
	private boolean modeCrypt;
	
	
	public TacheChiffrement(Context con, TypeTache type) {
		context = con;
		this.typeTache = type;
	}
	
	public TacheChiffrement(Context con, TypeTache type, boolean modeCrypt) {
		context = con;
		this.typeTache = type;
		this.modeCrypt = modeCrypt;
	}
	
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
/*		System.out.println("Chiffrement en Cours...");*/
		MainActivity.AfficherToast(context, "Chiffrement en cours...");
		System.out.println("Pre-execution tache");
		
		super.onPreExecute();
	}

	
	@Override
	protected Boolean doInBackground(String... optionalPaths) {
		Boolean reussi = true;
		
		try {
			if(typeTache.equals(TypeTache.ByWidget)){
				boolean ToCrypt = FilePathConfigurationFactory.ExtractWidgetModeCrypt(context);
				System.out.println("Dans background : Widget");
				
				System.out.println("DANS LE SERVICE !");
								
				
				Collection<String> pathsToCryptOrDecrypt = FilePathConfigurationFactory.ReadFromConfigPathsListFile(true, true, context);
				Collection<String> pathsEncryptedList = ToCrypt ? EncryptionFactory.Encrypt(pathsToCryptOrDecrypt) : EncryptionFactory.Decrypt(pathsToCryptOrDecrypt);
				FilePathConfigurationFactory.WriteInConfigPathsListFile(pathsEncryptedList, false, true, context);
				FilePathConfigurationFactory.SwitchWidgetMode(context);
				
				
			}else if (typeTache.equals(TypeTache.ByGUI)) {
				//TODO A TESTER !!! => Pas encore testé
				System.out.println("Dans background : GUI");
				Collection<String> pathsToCryptOrDecrypt = Arrays.asList(optionalPaths);
				Collection<String> pathsEncryptedList = modeCrypt ? EncryptionFactory.Encrypt(pathsToCryptOrDecrypt) : EncryptionFactory.Decrypt(pathsToCryptOrDecrypt);
				if(modeCrypt)
					FilePathConfigurationFactory.WriteInConfigPathsListFile(pathsEncryptedList, true, false, context);
				else {
					RemoveFromNormalConfigFile(pathsToCryptOrDecrypt);
				}
				
				
			}
		}catch(Exception e) {
			System.out.println("Une erreur");
			reussi = false;
			MainActivity.AfficherToast(context.getMainLooper(), context, "Problem : Stopping the encrypting !\n\n"+e.getMessage());
		}
		
		
		return reussi;
	}
	
	
	private void RemoveFromNormalConfigFile(Collection<String> pathsEncryptedList) {
		
		Collection<String> pathsInFileConfig = FilePathConfigurationFactory.ReadFromConfigPathsListFile(false, false, context);
		pathsInFileConfig.removeAll(pathsEncryptedList);
		FilePathConfigurationFactory.WriteInConfigPathsListFile(pathsInFileConfig, false, false, context);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		MainActivity.AfficherToast(context, "Chiffrement terminé...");
		System.out.println("Fini !");
		WidgetLanceur.ChiffrementEnCours = false;
		super.onPostExecute(result);
	}

}
