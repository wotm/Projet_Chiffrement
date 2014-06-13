package iut.montreuil.projet.tuteure.easycrypt.modele;

import iut.montreuil.projet.tuteure.easycrypt.modele.Algorithmes.Algorithme_Chiffrement_Nom_Fichier;
import iut.montreuil.projet.tuteure.easycrypt.modele.Algorithmes.Algorithme_cryptageDonnees;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.provider.MediaStore.Files;

//OK
public class EncryptionFactory {

	//return the main paths with encrypted name, after encrypting reccursivly all the files in the pathlist 
	public static Collection<String> Encrypt(Collection<String> listePaths) {

		ArrayList<String> listMainPathEncrypted = new ArrayList<String>();
		
		for (String path : listePaths) {
			File fichier = new File(path);
				
			if (fichier.isDirectory()) {

				Encrypt((Collection<String>) Arrays.asList(fichier.list()));

			} else {

				// Crypting the bytes
				byte[] oldBytes = FileFactory.extractionDonneesFichier(path);
				byte[] newBytes = Algorithme_cryptageDonnees
						.Chiffrement_datas(oldBytes);
				try {
					FileFactory.ecritureDonneesFichier(
							fichier.getAbsolutePath(), newBytes);
				} catch (Exception e) {
					e.printStackTrace();
					return new ArrayList<String>(); // I don't throw the exception because this
									// case should never arrive
				}

				// Renaming File
				String FileNameEncrypted = Algorithme_Chiffrement_Nom_Fichier
						.Algo_Cryptage(fichier.getName());
				File encryptedFile = new File(fichier.getParent() + "/"
						+ FileNameEncrypted);
				fichier.renameTo(encryptedFile);
			
				listMainPathEncrypted.add(encryptedFile.getAbsolutePath());
			}
		}
		
		return listMainPathEncrypted;
	}

	//return the main paths with normal name (uncrypted), after uncrypting reccursivly all the files in the pathlist
	public static Collection<String> Decrypt(Collection<String> listePaths) {

		ArrayList<String> listMainPathUncrypted = new ArrayList<String>();
		
		for (String path : listePaths) {
			File fichier = new File(path);

			if (fichier.isDirectory()) {

				Decrypt((Collection<String>) Arrays.asList(fichier.list()));

			} else {

				// Crypting the bytes
				byte[] oldBytes = FileFactory.extractionDonneesFichier(path);
				byte[] newBytes = Algorithme_cryptageDonnees
						.Dechiffrement_datas(oldBytes);
				try {
					FileFactory.ecritureDonneesFichier(
							new BufferedOutputStream(new FileOutputStream(
									fichier)), newBytes);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return new ArrayList<String>(); // I don't throw the exception because this
									// case should never arrive
				}

				// Renaming File
				String FileNameUncrypted = Algorithme_Chiffrement_Nom_Fichier
						.Algo_Decryptage(fichier.getName());
				File encryptedFile = new File(fichier.getParent() + "/"
						+ FileNameUncrypted);
				fichier.renameTo(encryptedFile);

				listMainPathUncrypted.add(encryptedFile.getAbsolutePath());
				
			}
		}

		return listMainPathUncrypted;
	}

}
