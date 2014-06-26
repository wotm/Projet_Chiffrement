package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.modele.ManualText;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartingManuel extends Activity {

	public static boolean start = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_manuel);
		
		//Text instanciation 
		TextView manualText = (TextView) findViewById(R.id.textStartingManual);
		manualText.setText(ManualText.GetUseStartingManual());
		
				
		//Button instanciation
		Button configurationButton = (Button) findViewById(R.id.btn_settings_from_start);
		configurationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getThis(), SettingsActivity.class);
                startActivity(i);
                finish();
			}
		});
		
		
		
		//TODO a skip button disabled for the first time (configuration file presente)
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		start = true;
	}

	
	private Context getThis() {
		return this;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_manuel, menu);
		return true;
	}

}
