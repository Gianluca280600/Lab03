package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dizionario {

	public List<String> dizionario=new  LinkedList<String>();
	public String language;
	
	
public void loadDictionary(String language) {
		
		try {
			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr); 
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word);
			}
				Collections.sort(dizionario);
				br.close();
		} catch (IOException e){
			System.out.println("Errore nella lettura del file");

		}
	
	}

	public List<RichWord> spellCheck(List<String> input){
		boolean flag=false;
		List<RichWord> lcor=new LinkedList<RichWord>();
		for(String s:input) {
			for(String d:dizionario) {
				if(s.equals(d)==true) {
					flag=true;
				}
			}
			RichWord r=new RichWord(s, flag);
			if(flag==false) {	
				r.setWord(s);
				r.setCorrect(flag);
				lcor.add(r);
			}
		}
		return lcor;
	}
	
	public List<RichWord> spellCheckL(List<String> input){
		boolean flag=false;
		List<RichWord> lcor=new LinkedList<RichWord>();
		for(String s:input) {
			for(String d:dizionario) {
				if(s.equals(d)==true) {
					flag=true;
				}
			}
			RichWord r=new RichWord(s, flag);
			if(flag==false) {	
				r.setWord(s);
				r.setCorrect(flag);
				lcor.add(r);
			}
		}
		return lcor;
	}
	
	public List<RichWord> spellCheckD(List<String> input){
		List<RichWord> lcor=new LinkedList<RichWord>();
		
		for(String str:input) {
			RichWord r=new RichWord(str);
			if (binarySearch(str.toLowerCase()))
				r.setCorrect(true);
			else
				r.setCorrect(false);
				lcor.add(r);
		}
		
		return lcor;
	}
	
	private boolean binarySearch(String stemp) {
		int inizio = 0;
		int fine = dizionario.size();
		while (inizio != fine) {
			int medio = inizio + (fine - inizio) / 2;
			if (stemp.compareToIgnoreCase(dizionario.get(medio)) == 0) {
				return true;
			} else if (stemp.compareToIgnoreCase(dizionario.get(medio)) > 0) {
				inizio = medio + 1;
			} else {
				fine = medio;
			}
		}

		return false;
	}
	
	
	
}
