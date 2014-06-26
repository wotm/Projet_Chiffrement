package iut.montreuil.projet.tuteure.easycrypt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import iut.montreuil.projet.tuteure.easycrypt.modele.FilePathConfigurationFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
	        this.btn_decrypt = (Button) findViewById(id.btn_decrypt);
	        /*
	        try{

				File f1 = new File(Environment.getExternalStorageDirectory().getPath() +"/DossierTest/DossierRecursif1/ZichierTest");			
				boolean t = f1.createNewFile();
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "<kj"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + ">kj"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k+j"));//ok
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k*j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k/j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k\"j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k\\j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k'j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k?j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k%j"));
				t = f1.renameTo(new File(f1.getParentFile().getAbsolutePath() + "/" + "k|j"));
				
			} catch(Exception e) {
				
			}
	        */
	        /*
	        try {
	        	File f1 = new File(Environment.getExternalStorageDirectory().getPath() +"/DossierTest/DossierRecursif1/"+"k+j");
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(getFilesDir() + MainActivity.PathConfigFilesToDecrypt))));
				writer.write("");
				writer.flush();
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        
	        try{
	        	/*
	        	File ftopete = new File(Environment.getExternalStorageDirectory().getPath());
	        	for (File f : ftopete.listFiles()) {
					if(f.getName().startsWith("L]"))
						f.renameTo(new File(f.getParent()+"/DossierTest"));
				}
	        	
				File f1 = new File(Environment.getExternalStorageDirectory().getPath() +"/DossierTest/DossierRecursif1/fichier1.alfa");		
				File f2 = new File(f1.getParentFile().getParent());
				wipeDirectory(f2);
				
				f1.mkdirs();				
				new File(f1.getParent()+"/rato.bg").createNewFile();
				
				new File(f1.getParentFile().getParent()+"/DossierRecursif2").mkdir();
				new File(f1.getParentFile().getParent()+"/DossierRecursif2/blabla").createNewFile();*/
	        	//new File(Environment.getExternalStorageDirectory().getPath() +"/DoLnload").renameTo(new File(Environment.getExternalStorageDirectory().getPath() +"/Download"));
			} catch(Exception e) {
				
			}
	        
	        initialiserBoutons();
	    }
	 
	 private void wipeDirectory(File f) {
		 for (File file : f.listFiles()) {
			if(file.isDirectory()) 
				wipeDirectory(file);
			file.delete();
		}
	 }
	 
	 private void initialiserBoutons() {
	    	this.btn_encrypt.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent(StartingActivity.this, EncryptionActivity.class);
					startActivity(intent);
				}
			});
	    	
	    	this.btn_decrypt.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent(StartingActivity.this, DecryptionActivity.class);
					startActivity(intent);
				}
			});
	    }
	 
}
