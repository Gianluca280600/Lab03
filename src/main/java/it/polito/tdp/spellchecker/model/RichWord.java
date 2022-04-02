package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

public class RichWord {
	
	private String word;
	private boolean correct;
	private Dictionary d;
	
	public RichWord(String word, boolean correct) {
		this.word = word;
		this.correct = correct;
	}

	public RichWord(String word) {
		this.word = word;
	}


	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "RichWord [word=" + word + ", correct=" + correct + "]";
	}
	

}