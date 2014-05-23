package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
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
    }
}
