package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.modele.ManualText;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ManualActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual);
		
		TextView textView = (TextView) findViewById(R.id.viewManual);
		textView.setText(ManualText.GetUseManual());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manual, menu);
		return true;
	}

}
