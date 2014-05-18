package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class EncryptionActivity extends Activity {
	private Button btn_encrypt;
	private Button btn_cancel;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.btn_encrypt = (Button) findViewById(id.btn_encrypt);
	
        this.initialiserBoutons();
	}
	
	private void initialiserBoutons() {
		
	}
}
