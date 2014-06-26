package iut.montreuil.projet.tuteure.easycrypt;

import iut.montreuil.projet.tuteure.easycrypt.modele.FilePathConfigurationFactory;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement.TypeTache;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsActivity extends Activity {
	private ListView myListView;
	private Button btn_addToConfig;
	private ImageButton upOneLevelIcon;
	private ImageView imv;

	private enum DISPLAYMODE {
		ABSOLUTE, RELATIVE;
	}

	private final DISPLAYMODE displayMode = DISPLAYMODE.RELATIVE;

	private File currentFile = new File("/");
	private List<File> filesEntries = new ArrayList<File>();
	private Collection<String> listPathsInFile;
	private FileAdapter fileAdapter;
	public List<String> filesCheckedToEncryptList;
	public List<String> filesToRemove;
	public List<String> encryptedFilesList;
	
	@Override
	protected void onResume() {
		super.onResume();
		listPathsInFile = FilePathConfigurationFactory.ReadFromConfigPathsListFile(true, false, getApplicationContext());

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_files_explorer_activity);
		
		listPathsInFile = FilePathConfigurationFactory.ReadFromConfigPathsListFile(true, false, getApplicationContext());
		
		filesToRemove = new ArrayList<String>();
		
		myListView = (ListView) findViewById(R.id.list);
		upOneLevelIcon = (ImageButton) findViewById(R.id.upOneLevel);

		final LayoutInflater androidFilesExpFac = getLayoutInflater();
		final View row = androidFilesExpFac.inflate(R.layout.row, null);
		imv = (ImageView) row.findViewById(R.id.fileIconDrawable);

		btn_addToConfig = (Button) findViewById(R.id.btn_encrypt_action);
		btn_addToConfig.setText("Enregistrer modifications");//("Ajouter/Retirer de la config");
		btn_addToConfig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// BUILDING OF THE FILES LIST TO ENCRYPT
				filesCheckedToEncryptList = fileAdapter.getCheckedItems();

				System.out.println("Liste des fichiers :");
				for (int i = 0; i < filesCheckedToEncryptList.size(); i++) {
					System.out.println("Chemin " + (i + 1) + " = "
							+ filesCheckedToEncryptList.get(i));
				}
				
				System.out.println("Paths dans le fichier de config : \n");
				for (String path : listPathsInFile) {
					System.out.println(path);
				}
				
				//Remove unchecked paths from the config file
				listPathsInFile.removeAll(filesToRemove);
				FilePathConfigurationFactory.WriteInConfigPathsListFile(listPathsInFile, false, true, getApplicationContext());
				filesToRemove.clear();
				
				Collection<String> pathsToAdd = new ArrayList<String>();
				//Add new paths in the config file
				for (String path : filesCheckedToEncryptList) {
					if(!listPathsInFile.contains(path))
						pathsToAdd.add(path);
				}
				if(!pathsToAdd.isEmpty())
					FilePathConfigurationFactory.WriteInConfigPathsListFile(pathsToAdd, true, true, getApplicationContext());
				
				listPathsInFile = FilePathConfigurationFactory.ReadFromConfigPathsListFile(true, false, getApplicationContext());
				
				System.out.println("Paths dans le fichier de config (Après modifs) : \n");
				for (String path : listPathsInFile) {
					System.out.println(path);
				}
				
				MainActivity.AfficherToast(getApplicationContext(), "Modifications enregistrées !");

			}
		});

		browseToRoot();

		/*** BROWSING LISTENER TO BROWSE THROUGH THE FILES ***/
		myListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(android.widget.AdapterView<?> FileAdapter,
					View myListView, int itemPos, long rowId) {
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
				if (currentFile.getParent() != null)
					browseTo(currentFile.getParentFile());

				if (currentFile.getAbsolutePath().equals("/")) {
					upOneLevelIcon.setVisibility(View.GONE);
				}
			}
		});
	}

	/**** BEGINNING OF FILES BROWSER ****/
	private void browseToRoot() {
		browseTo(new File(Environment.getExternalStorageDirectory().getPath()));
	}

	private void browseTo(final File aFile) {
		// On relative we display the full path in the title.
		if (this.displayMode == DISPLAYMODE.RELATIVE)
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

		String fileName = this.currentFile.getName();
		Drawable currentIcon = null;
		if (checkEndsWithInStringArray(fileName,
				getResources().getStringArray(R.array.fileEndingImage))) {
			currentIcon = getResources().getDrawable(R.drawable.image);
		} else if (checkEndsWithInStringArray(fileName, getResources()
				.getStringArray(R.array.fileEndingWebText))) {
			currentIcon = getResources().getDrawable(R.drawable.webtext);
		} else if (checkEndsWithInStringArray(fileName, getResources()
				.getStringArray(R.array.fileEndingPackage))) {
			currentIcon = getResources().getDrawable(R.drawable.packed);
		} else if (checkEndsWithInStringArray(fileName, getResources()
				.getStringArray(R.array.fileEndingAudio))) {
			currentIcon = getResources().getDrawable(R.drawable.audio);
		} else {
			currentIcon = getResources().getDrawable(R.drawable.text);
		}
		imv.setImageDrawable(currentIcon);
		if (files != null) {
			for (File currentFile : files) {
				this.filesEntries.add(currentFile);
			}
			Collections.sort(this.filesEntries);
			fileAdapter = new FileAdapter(this, R.layout.row,
					R.id.filePathChkBox, filesEntries);

		} else {
			fileAdapter = new FileAdapter(this, R.layout.row,
					R.id.filePathChkBox, new ArrayList<File>());
		}
		myListView.setAdapter(fileAdapter);

	}

	private boolean checkEndsWithInStringArray(String checkItsEnd,
			String[] fileEndings) {
		for (String aEnd : fileEndings) {
			if (checkItsEnd.endsWith(aEnd))
				return true;
		}
		return false;
	}

	/*** FILE ADAPTER FOR THE LISTVIEW ***/
	private class FileAdapter extends ArrayAdapter<File> {
		private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();

		public FileAdapter(Context context, int resource,
				int textViewResourceId, List<File> objects) {
			super(context, resource, textViewResourceId, objects);

			for (int i = 0; i < objects.size(); i++) {
				if(listPathsInFile.contains(objects.get(i).getAbsolutePath()))
					myChecked.put(i, true);
				else
					myChecked.put(i, false);				
			}
		}

		public void toggleChecked(int position) {
			String absolutePath = filesEntries.get(position).getAbsolutePath();
			
			if (myChecked.get(position)) {
				myChecked.put(position, false);
				filesToRemove.add(absolutePath);
			} else {
				myChecked.put(position, true);
				if(filesToRemove.contains(absolutePath))
					filesToRemove.remove(absolutePath);
			}

			notifyDataSetChanged();
		}

		public List<Integer> getCheckedItemPositions() {
			List<Integer> checkedItemPositions = new ArrayList<Integer>();

			for (int i = 0; i < myChecked.size(); i++) {
				if (myChecked.get(i)) {
					(checkedItemPositions).add(i);
				}
			}
			return checkedItemPositions;
		}

		public List<String> getCheckedItems() {
			List<String> checkedItems = new ArrayList<String>();

			for (int i = 0; i < myChecked.size(); i++) {
				if (myChecked.get(i)) {
					(checkedItems).add(filesEntries.get(i).getPath());
				}
			}

			return checkedItems;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.row, parent, false);
			}

			TextView filePathTxtView = (TextView) row
					.findViewById(R.id.filePathTxtView);
			int currentPathStringLenght = currentFile.getAbsolutePath()
					.length();
			filePathTxtView.setText(filesEntries.get(position)
					.getAbsolutePath().substring(currentPathStringLenght));

			if (filesEntries.get(position).isDirectory()) {
				row.setBackgroundColor(Color.YELLOW);
			} else
				row.setBackgroundColor(Color.TRANSPARENT);

			CheckBox filePathChkBox = (CheckBox) row
					.findViewById(R.id.filePathChkBox);
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		if (StartingManuel.start == true) {
			StartingManuel.start = false;
			Intent i = new Intent(GetThis(), MainActivity.class);
			startActivity(i);
			
		}
		super.onBackPressed();
	}

	private Context GetThis() {
		return this;
	}
}
