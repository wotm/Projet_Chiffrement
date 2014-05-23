package iut.montreuil.projet.tuteure.easycrypt;

import java.io.File;

import iut.montreuil.projet.tuteure.easycrypt.R;
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
		new File(MainActivity.PathDossierConfigEasyCrypt).mkdirs();
		
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