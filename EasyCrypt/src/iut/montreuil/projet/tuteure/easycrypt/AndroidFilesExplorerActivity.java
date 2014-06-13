package iut.montreuil.projet.tuteure.easycrypt;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidFilesExplorerActivity extends Activity {
	private ListView myListView;
	private Button encrypt;
	private ImageButton upOneLevelIcon;
	 
	private enum DISPLAYMODE{ ABSOLUTE, RELATIVE; }
	private final DISPLAYMODE displayMode = DISPLAYMODE.RELATIVE;
	
	private File currentFile = new File("/");
	private List<File> filesEntries = new ArrayList<File>();
	private FileAdapter fileAdapter;
	public List<String> filesToEncryptList;
		 
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.android_files_explorer_activity);
		        myListView = (ListView)findViewById(R.id.list);
		        upOneLevelIcon = (ImageButton) findViewById(R.id.upOneLevel);
		        encrypt = (Button)findViewById(R.id.btn_encrypt_action);
		        encrypt.setOnClickListener(new OnClickListener() {

		        	@Override
		        	public void onClick(View v) {
					    //BUILDING OF THE FILES LIST TO ENCRYPT
//					    filesToEncryptList = fileAdapter.getCheckedItems();
//					    for(int i = 0; i < filesToEncryptList.size(); i++) {
					    	
					        Toast.makeText(
								      getApplicationContext(), 
								      "Vos fichiers ont bien été chiffrés !", 
								      Toast.LENGTH_LONG).show();
//					    }
					  }
		        	});
		        
		        browseToRoot();
		        
		        /*** BROWSING LISTENER TO BROWSE THROUGH THE FILES ***/
				myListView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(android.widget.AdapterView<?> FileAdapter, View myListView, int itemPos, long rowId) {
						File clickedFile = filesEntries.get(itemPos);
						if (clickedFile.isDirectory()) {
							currentFile = clickedFile;
							browseTo(currentFile);
						}
					};
				});
				
			    upOneLevelIcon.setOnClickListener(new OnClickListener() {
			    	@Override
					public void onClick(View arg0) {
			    		if(currentFile.getParent() != null)
			    			browseTo(currentFile.getParentFile());
			    		
			    		if (currentFile.getAbsolutePath().equals("/")) {
			    			upOneLevelIcon.setVisibility(View.GONE);
			    		}
					}
				});
		    }
		    		  
		  
		/**** BEGINNING OF FILES BROWSER ****/
		private void browseToRoot() {
			browseTo(new File("/"));
		}
		
		private void browseTo(final File aFile) {
			// On relative we display the full path in the title.
			if(this.displayMode == DISPLAYMODE.RELATIVE)
				this.setTitle(aFile.getAbsolutePath());
			if (aFile.isDirectory()) {
				this.currentFile = aFile;
				fill(aFile.listFiles());
			}
		}
		
		private void fill(File[] files) {
			this.filesEntries.clear();
			if (currentFile.getParent() != null) {
				upOneLevelIcon.setVisibility(View.VISIBLE);
			}
			for (File currentFile : files) {
				this.filesEntries.add(currentFile);
			}
			Collections.sort(this.filesEntries);
	        fileAdapter = new FileAdapter(this, R.layout.row, R.id.filePathChkBox, filesEntries);
	        myListView.setAdapter(fileAdapter);
		}
		

		/*** FILE ADAPTER FOR THE LISTVIEW ***/
		private class FileAdapter extends ArrayAdapter<File> {
		private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();

		  public FileAdapter(Context context, int resource,
		    int textViewResourceId, List<File> objects) {
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
		  
		  public List<Integer> getCheckedItemPositions(){
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
		     (checkedItems).add(filesEntries.get(i).getPath());
		    }
		   }
		   
		   return checkedItems;
		  }
		  
		  
		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		   View row = convertView;
		   
		   if(row == null) {
		    LayoutInflater inflater = getLayoutInflater();
		    row = inflater.inflate(R.layout.row, parent, false);  
		   }
		   
		   TextView filePathTxtView = (TextView)row.findViewById(R.id.filePathTxtView);
		   int currentPathStringLenght = currentFile.
					getAbsolutePath().length();
		   filePathTxtView.setText(filesEntries.get(position).getAbsolutePath().substring(currentPathStringLenght));
		   
		   CheckBox filePathChkBox =  (CheckBox) row.findViewById(R.id.filePathChkBox);
		   filePathChkBox.setTag(position);
		   filePathChkBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int position = (Integer) v.getTag();
				fileAdapter.toggleChecked(position);
			}
		});
	
		   Boolean checked = myChecked.get(position);
		   if (checked != null) {
			   filePathChkBox.setChecked(checked);
		   }
		   return row;
		  }
		}
	}