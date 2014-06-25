package iut.montreuil.projet.tuteure.easycrypt.modele.Algorithmes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//This class is needed because of the android restricted caracters !!

public class MyASCII {
	
	private static Integer[] restricted = new Integer [] {
		(int) '\"',
		(int) '%',
		(int) '\'',
		(int) '*',
		(int) '/',
		(int) '<',
		(int) '>',
		(int) '?',
		(int) '\\',
		(int) '|',
		(int) ':'
		};
	
	private static int start = 33;
	private static int end = 126;
	
	public static HashMap<Integer, Character> asciiMap (){
		
				
		HashMap<Integer, Character> map =new HashMap<Integer, Character>();
				
		ArrayList<Integer> restrict = (ArrayList<Integer>) Arrays.asList(restricted);
		
		for(int i = start; i<= end; i++) {
			if(restrict.contains(i))
				continue;
			map.put(i, (char) i);
		}
		return map;
	};
	
	public static ArrayList<Character> asciiCorrectChars() {
		ArrayList<Character> allCorrectsChars = new ArrayList<Character>();
		
		ArrayList<Integer> restrict = new ArrayList<Integer>(Arrays.asList(restricted));
		
		for(int i = start; i<= end; i++) {
			if(restrict.contains(i))
				continue;
			allCorrectsChars.add((char)i);
		}
		return allCorrectsChars;
	}
}
