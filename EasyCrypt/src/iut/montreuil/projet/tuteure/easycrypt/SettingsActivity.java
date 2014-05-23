package iut.montreuil.projet.tuteure.easycrypt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		if(StartingManuel.start == true) {
			StartingManuel.start = false;
			Intent i = new  Intent(GetThis(), MainActivity.class);
			startActivity(i);
		}else  {
			super.onBackPressed();
		}
	}
	
	private Context GetThis() {
		return this;
	}
}
