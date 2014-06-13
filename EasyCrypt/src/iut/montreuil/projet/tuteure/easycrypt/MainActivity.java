package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


	private static final String PathDossierConfigEasyCrypt = "/EasyCrypt"; //On définit à l'initialisation (dans screen splash) le dossier de l'appli selon le contexte 
	
	private static final String PathFolderConfigWidget = PathDossierConfigEasyCrypt + "/" + "Widget-config";
	private static final String PathFolderConfigNormalCrypt = PathDossierConfigEasyCrypt + "/" + "NormalCrypt-config"; 
	
	//Widget config files.
	public static final String PathConfigPathsWidgetPlannifCrypt = PathFolderConfigWidget+"/EasyCrypt-plannif-Crypts";
	public static final String PathConfigWidgetModeCrypt = PathFolderConfigWidget+"/EasyCrypt-Widget-Mode-Crypt";
	
	//Normal crypt config files
	public static final String PathConfigFilesToDecrypt = PathFolderConfigNormalCrypt +"/EasyCrypt-ToDecrypt";
	
	
	private Button btn_start;
	private Button btn_settings;
	private Button btn_manual;
	private Button btn_exit;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        this.btn_start = (Button) findViewById(id.btn_start);
        this.btn_settings = (Button) findViewById(id.btn_settings);
        this.btn_manual = (Button) findViewById(id.btn_usr_guide);
        this.btn_exit = (Button) findViewById(id.btn_close);
    
        this.initialiserBoutons();
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void initialiserBoutons() {
    	this.btn_start.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, StartingActivity.class);
				startActivity(intent);
			}
		});
    	
    	this.btn_settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(intent);
			}
		});
    	
    	
    	this.btn_manual.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ManualActivity.class);
				startActivity(intent);
			}
		});
    }
    
    //Pour le DEV
    public static void printEnvironmentsVariable() {

        for (Object key : System.getenv().keySet()) {
			System.out.println(key+" => "+System.getProperty((String)key));
		}
        
        for (Object key : System.getProperties().keySet()) {
			System.out.println(key+" => "+System.getProperty((String)key));
		}
        
        
    }
    
    public static void printEnvironmentsVariable(Context c) {
    	printEnvironmentsVariable();
    	System.out.println("\nGetPathFiles = "+c.getFilesDir());
    }
    
    public static void AfficherToast(Looper loo, Context con, String text) {
		final Context context = con;
		final String t = text;
		
		Handler h = new Handler(loo);
		h.post(new Runnable() {
		   @Override
		   public void run() {
		        Toast.makeText(context,t,Toast.LENGTH_LONG).show();
		   }
		});
	}
	
	public static void AfficherToast(Context con, String text) {
		
		Toast.makeText(con,text,Toast.LENGTH_LONG).show();
		
	}
}
