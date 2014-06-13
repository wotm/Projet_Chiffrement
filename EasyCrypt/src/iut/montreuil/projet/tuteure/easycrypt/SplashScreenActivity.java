package iut.montreuil.projet.tuteure.easycrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import iut.montreuil.projet.tuteure.easycrypt.R;
import iut.montreuil.projet.tuteure.easycrypt.modele.FilePathConfigurationFactory;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement.TypeTache;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {
	// Dur�e d'affichage du SplashScreen
	protected int _splashTime = 5000;
	private Thread splashTread;

	// Chargement de l'Activity
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen_activity);

		
		final SplashScreenActivity sPlashScreen = this;
		
		

		//Auto-creation of the application folder
		new File(getFilesDir() + MainActivity.PathConfigWidgetModeCrypt).getParentFile().mkdirs();
		new File(getFilesDir() + MainActivity.PathConfigFilesToDecrypt).getParentFile().mkdirs();
		
		//Tests
		
		TacheChiffrement tache = new TacheChiffrement(this, TypeTache.ByGUI);
		tache.execute("R1","R2", "et R3 loool");
		
//		
//		try {
//			
//			File fe = new File(getFilesDir() + "/testFichier.txt");
//			FileWriter wr = new FileWriter(fe);
//			wr.write("Hey baby !! ");
//			wr.flush();
//			wr.close();
//			
//			File f = new File(getFilesDir() + MainActivity.PathConfigPathsWidgetPlannifCrypt);
//			FileWriter writer = new FileWriter(f);
//			writer.write(new File( getFilesDir() + "/testFichier.txt").getAbsolutePath());
//			writer.flush();
//			writer.close();
//			
//			if(!FilePathConfigurationFactory.ExtractWidgetModeCrypt(this))
//				FilePathConfigurationFactory.SwitchWidgetMode(this);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
		
		//Fin du test
		
		
		
		
		// Thread pour l'affichage du SplashScreen
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(_splashTime);
					}
				} catch (InterruptedException e) {
				} finally {
					finish();
					Intent i = new Intent();
					i.setClass(sPlashScreen, StartingManuel.class);
					startActivity(i);
					// stop();
				}
			}
		};
		splashTread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Si l'utilisateur fait un mouvement de haut en bas on passe �
		// l'Activity principale
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (splashTread) {
				splashTread.notifyAll();
			}
		}

		return true;
	}
}