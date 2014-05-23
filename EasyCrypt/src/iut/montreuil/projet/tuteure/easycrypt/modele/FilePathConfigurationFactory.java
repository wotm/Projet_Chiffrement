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

		} catch (IOException e) {
			e.printStackTrace();
			return false; // I don't throw the exception because this
			// case should never arrive
		}

		return true;
	}
	
	public static Collection<String> ReadFromConfigFile() {
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pathsList;
	}

}
