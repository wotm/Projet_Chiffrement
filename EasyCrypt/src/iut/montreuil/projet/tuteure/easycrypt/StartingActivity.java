package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartingActivity extends Activity {
	private Button btn_encrypt;
	private Button btn_decrypt;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.starting_activity);
	        this.btn_encrypt = (Button) findViewById(id.btn_encrypt);
//	        this.btn_decrypt = (Button) findViewById(id.btn_decrypt);
	        
	        initialiserBoutons();
	    }
	 
	 private void initialiserBoutons() {
	    	this.btn_encrypt.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent(StartingActivity.this, AndroidFilesExplorerActivity.class);
					startActivity(intent);
				}
			});
	    }
}
