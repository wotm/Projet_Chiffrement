package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.R.id;
import iut.montreuil.projet.tuteure.easycrypt.modele.EncryptionFactory;
import iut.montreuil.projet.tuteure.easycrypt.modele.FileFactory;
import iut.montreuil.projet.tuteure.easycrypt.modele.FilePathConfigurationFactory;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement.TypeTache;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DecryptionActivity extends Activity{
	private Button btn_decrypt_action;
	private FileAdapter fileAdapter;
	private ListView mylistView;
	
	private List<String> encryptedFilesPathList = new ArrayList<String>();
	private List<String> filesPathToDecrypt = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decryption_activity);
        
        mylistView = (ListView)findViewById(R.id.list);
        btn_decrypt_action = (Button) findViewById(id.btn_decrypt_action);
        
        encryptedFilesPathList.addAll(FilePathConfigurationFactory.ReadFromConfigPathsListFile(false, false, getApplicationContext()));
        
        fileAdapter = new FileAdapter(this, R.layout.decryption_activity_row, R.id.txtView_encryptedFilePathChkBox, encryptedFilesPathList);
        mylistView.setAdapter(fileAdapter);
        initialiserBoutons();
	}
	
	private void initialiserBoutons() {
		this.btn_decrypt_action.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				filesPathToDecrypt = fileAdapter.getCheckedItems();
				TacheChiffrement t = new TacheChiffrement(getApplicationContext(), TypeTache.ByGUI, false);
				t.execute(filesPathToDecrypt.toArray(new String[filesPathToDecrypt.size()]));
			    onBackPressed();
			}
		});
	}
	
	
	
	/*** FILE ADAPTER FOR THE LISTVIEW ***/
	private class FileAdapter extends ArrayAdapter<String> {
	private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();

	  public FileAdapter(Context context, int resource,
	    int textViewResourceId, List<String> objects) {
	   super(context, resource, textViewResourceId, objects);
	   
	   for(int i = 0; i < objects.size(); i++){
	    myChecked.put(i, false);
	   }
	  }
	     
	  public void toggleChecked(int position) {
	   if(myChecked.get(position)){
	    myChecked.put(position, false);
	   }else{
	    myChecked.put(position, true);
	   }
	   notifyDataSetChanged();
	  }
	  
	  public List<Integer> getCheckedItemPositions() {
	   List<Integer> checkedItemPositions = new ArrayList<Integer>();
	   
	   for(int i = 0; i < myChecked.size(); i++){
	    if (myChecked.get(i)){
	     (checkedItemPositions).add(i);
	    }
	   }
	   
	   return checkedItemPositions;
	  }
	  
	  public List<String> getCheckedItems(){
	   List<String> checkedItems = new ArrayList<String>();
	   
	   for(int i = 0; i < myChecked.size(); i++){
	    if (myChecked.get(i)){
	     (checkedItems).add(encryptedFilesPathList.get(i));
	    }
	   }
	   
	   return checkedItems;
	  }
	  
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	   View row = convertView;
	   
	   if(row == null) {
	    LayoutInflater inflater = getLayoutInflater();
	    row = inflater.inflate(R.layout.decryption_activity_row, parent, false);  
	   }
	   
	   TextView txtView_encryptedFilePath = (TextView)row.findViewById(R.id.txtView_encryptedFilePath);
	   txtView_encryptedFilePath.setText(encryptedFilesPathList.get(position));
	   
	   if (new File(encryptedFilesPathList.get(position)).isDirectory()) {
		   row.setBackgroundColor(Color.YELLOW);
	   }else
		   row.setBackgroundColor(Color.TRANSPARENT);
	   
	   CheckBox txtView_encryptedFilePathChkBox =  (CheckBox) row.findViewById(R.id.txtView_encryptedFilePathChkBox);
	   txtView_encryptedFilePathChkBox.setTag(position);
	   txtView_encryptedFilePathChkBox.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			fileAdapter.toggleChecked(position);
		}
	});

	   Boolean checked = myChecked.get(position);
	   if (checked != null) {
		   txtView_encryptedFilePathChkBox.setChecked(checked);
	   }
	   return row;
	  }
	}
}
