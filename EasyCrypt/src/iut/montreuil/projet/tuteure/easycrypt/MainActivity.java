package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import iut.montreuil.projet.tuteure.easycrypt.view.Lancement;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private Button startButton;
	private Button Configuration;
	private Button manual;
	private Button exit;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.startButton = (Button) findViewById(id.btn_start);
        this.Configuration = (Button) findViewById(id.btn_settings);
        this.manual = (Button) findViewById(id.btn_usr_guide);
        this.exit = (Button) findViewById(id.btn_close);
    
        this.initialiserBoutons();
    }

    public Context getContext() {
    	return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void initialiserBoutons() {
    	this.startButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getContext(), Lancement.class);
				startActivity(intent);
			}
		});
    }
    
}
