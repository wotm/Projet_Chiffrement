package iut.montreuil.projet.tuteure.easycrypt.modele;

import iut.montreuil.projet.tuteure.easycrypt.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;

//Vider le fichier de log après la lecture
public class FilePathConfigurationFactory {

	public static boolean WriteInConfigPathsListFile(Collection<String> paths,
			boolean append, boolean widgetConfigFile, Context c) {
		
		File fileConfig = widgetConfigFile ?
				new File(c.getFilesDir() + MainActivity.PathConfigPathsWidgetPlannifCrypt) :
					new File(c.getFilesDir() + MainActivity.PathConfigFilesToDecrypt);
		
		try {
			FileWriter writer = new FileWriter(fileConfig, append);

			for (String path : paths) {
				writer.write(path+"\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false; // I don't throw the exception because this
			// case should never arrive
		}

		return true;
	}
	
	public static Collection<String> ReadFromConfigPathsListFile(boolean widgetConfigFile, boolean thenWipe, Context c) {
		File fileConfig = widgetConfigFile ?
				new File(c.getFilesDir() + MainActivity.PathConfigPathsWidgetPlannifCrypt) :
					new File(c.getFilesDir() + MainActivity.PathConfigFilesToDecrypt);
				
		BufferedReader reader;
		Collection<String> pathsList = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader(fileConfig));

			String path = null;
			while ((path = reader.readLine()) != null) {
				pathsList.add(path);
			}
			reader.close();
			
			if(thenWipe) {
				fileConfig.delete();
				new File(fileConfig.getAbsolutePath()).createNewFile();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pathsList;
	}
	
	
	
	
	
	
	
	public static boolean WriteInFileWidgetMode(boolean cryptStatus, Context c) {
		File fileConfig = new File(c.getFilesDir() + MainActivity.PathConfigWidgetModeCrypt);
		try {
			FileWriter writer = new FileWriter(fileConfig);
			writer.write(cryptStatus ? "cryptMode" : "uncryptMode");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false; // I don't throw the exception because this
			// case should never arrive
		}
		return true;
	}
	
	
	//Return true if the mode is "Crypt", false if it is "uncrypt"
	public static boolean ExtractWidgetModeCrypt(Context c) throws Exception {
		File fileConfig = new File(c.getFilesDir() + MainActivity.PathConfigWidgetModeCrypt);
		BufferedReader reader;
		boolean cryptMode = false;

		if(!fileConfig.exists()) {
			WriteInWidgetModeFile(true, c);
		}
			
		
		try {
			reader = new BufferedReader(new FileReader(fileConfig));
			String read = reader.readLine();
			cryptMode = read.equals("cryptMode");
			if(!cryptMode) {
				if(!read.equals("uncryptMode")) {
					reader.close();
					throw new Exception("Bad informations in crypt file ! Should be only \"cryptMode\" or \"uncryptMode\"");
				}
			}
			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Probleme when extracting le widget mode in file :\n");
		}

		return cryptMode;
	}

	public static void SwitchWidgetMode(Context c) {
		try {
			WriteInFileWidgetMode(!ExtractWidgetModeCrypt(c), c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void WriteInWidgetModeFile(boolean cryptMode, Context c) {
		try {
			FileWriter writer = new FileWriter(c.getFilesDir() + MainActivity.PathConfigWidgetModeCrypt);
			writer.write(cryptMode ? "cryptMode" : "uncryptMode");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
