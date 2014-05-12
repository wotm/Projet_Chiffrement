package iut.montreuil.projet.tuteure.easycrypt.view;

import iut.montreuil.projet.tuteure.easycrypt.R;
import iut.montreuil.projet.tuteure.easycrypt.R.id;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Dechiffrement extends Activity{

	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private CheckBox checkBox3;
	private CheckBox checkBox4;
	private CheckBox checkBox5;
	private CheckBox checkBox6;
	private Button decrypt;
	private Button cancel;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checkBox1 = (CheckBox) findViewById(id.CheckBox03);
        this.checkBox2 = (CheckBox) findViewById(id.CheckBox04);
        this.checkBox3 = (CheckBox) findViewById(id.CheckBox05);
        this.checkBox4 = (CheckBox) findViewById(id.CheckBox06);
        this.checkBox5 = (CheckBox) findViewById(id.CheckBox07);
        this.checkBox6 = (CheckBox) findViewById(id.CheckBox08);
    
        this.decrypt = (Button) findViewById(id.btn_decryptAct);
        this.cancel = (Button) findViewById(id.btn_decrypt_cancel);
	
        this.initialiserBoutons();
	}
	
	private void initialiserBoutons() {
		this.cancel.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	
	
}
