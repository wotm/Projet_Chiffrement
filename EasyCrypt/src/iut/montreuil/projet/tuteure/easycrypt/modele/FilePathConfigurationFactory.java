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

//Vider le fichier de log après la lecture
public class FilePathConfigurationFactory {

	public static boolean WriteInConfigFile(Collection<String> paths,
			boolean append) {
		File fileConfig = new File(MainActivity.PathConfigurationPathsFiles);
		try {
			FileWriter writer = new FileWriter(fileConfig, append);

			for (String path : paths) {
				writer.write(path);
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
	
	public static Collection<String> ReadFromConfigFile(boolean thenWipe) {
		File fileConfig = new File(MainActivity.PathConfigurationPathsFiles);
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
				new File(MainActivity.PathConfigurationPathsFiles).createNewFile();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pathsList;
	}
	
	public static boolean WriteInFilePlannifConfig(Collection<String> paths,
			boolean append) {
		File fileConfig = new File(MainActivity.PathConfigPathsPlannifCrypt);
		try {
			FileWriter writer = new FileWriter(fileConfig, append);
			
			for (String path : paths) {
				writer.write(path);
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
	
	public static Collection<String> ReadFromFilePlannifConfig() {
		File fileConfig = new File(MainActivity.PathConfigPathsPlannifCrypt);
		BufferedReader reader;
		Collection<String> pathsList = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader(fileConfig));

			String path = null;
			while ((path = reader.readLine()) != null) {
				pathsList.add(path);
			}
			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pathsList;
	}
	
	public static boolean WriteInFileWidgetMode(boolean cryptStatus) {
		File fileConfig = new File(MainActivity.PathConfigWidgetModeCrypt);
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
	public static boolean ExtractWidgetModeCrypt() throws Exception {
		File fileConfig = new File(MainActivity.PathConfigWidgetModeCrypt);
		BufferedReader reader;
		boolean cryptMode = false;

		try {
			reader = new BufferedReader(new FileReader(fileConfig));
			String read = reader.readLine();
			cryptMode = read.equals("cryptMode");
			if(!cryptMode) {
				if(!read.equals("uncryptMode")) {
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

}
