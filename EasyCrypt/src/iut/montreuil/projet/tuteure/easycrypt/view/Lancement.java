package iut.montreuil.projet.tuteure.easycrypt.view;

import iut.montreuil.projet.tuteure.easycrypt.R;
import iut.montreuil.projet.tuteure.easycrypt.R.id;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Lancement extends Activity{

	private Button Chiffrer;
	private Button Dechiffrer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.Chiffrer= (Button) findViewById(id.btn_chiffrer);
        this.Dechiffrer= (Button) findViewById(id.btn_dechiffrer);
    
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
    	/*sthis.Chiffrer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getContext(), Chiffrement.class);
				startActivity(intent);
			}
		});*/
    	
    	this.Dechiffrer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getContext(), Dechiffrement.class);
				startActivity(intent);
			}
		});
    }
}
