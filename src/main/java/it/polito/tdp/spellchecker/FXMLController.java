package it.polito.tdp.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dizionario;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	
	private List<String> input;
	private Dizionario d;
	private RichWord r;
	private final static boolean dichotomicSearch = false;
	private final static boolean linearSearch = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;
    
    @FXML
    private Button btnClear;

    @FXML
    private Button btnSpell;

    @FXML
    private Label txtNer;

    @FXML
    private Label txtSec;

    @FXML
    private TextArea txtTesto;

    @FXML
    private TextArea txtWrong;

    @FXML
    void doActivation(ActionEvent event) {
    	boxLingua.getItems().addAll("English","Italian");
		if (boxLingua.getValue() !=null) {
	    		
	    		txtTesto.setDisable(false);
				txtWrong.setDisable(false);
				btnSpell.setDisable(false);
				btnClear.setDisable(false);
				txtTesto.clear();
				txtWrong.clear();
	    		
	   	}else {
	    		
				txtTesto.setDisable(true);
				txtWrong.setDisable(true);
				btnSpell.setDisable(true);
				btnClear.setDisable(true);
				txtTesto.setText("Seleziona una lingua!");
	    		
	   	}
	  }
    
    @FXML
    void doClear(ActionEvent event) {
    	txtTesto.clear();
    	txtWrong.clear();
    	txtNer.setText("Number of Errors:");
		txtSec.setText("Spell Check Status:");
    }

    @FXML
    void doSpell(ActionEvent event) {
    	
    
    	List<String> in= new LinkedList<String>();
    	List<RichWord> out= new LinkedList<RichWord>();
    	String testo=txtTesto.getText();
    	testo=testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	testo=testo.replaceAll("\n", " ");
    	String[] par=testo.split(" ");
    	for(int i=0;i<par.length;i++) {
    		in.add(par[i]);
    	}
    	String st=boxLingua.getValue();
    	d.loadDictionary(boxLingua.getValue());
    	
    	long start = System.nanoTime();
    	if (dichotomicSearch) {
    		out=d.spellCheckD(in);
    	}else if (linearSearch) {
    		out=d.spellCheckL(in);
    	}else {
    			out=d.spellCheck(in);
    	}
    
    	long end = System.nanoTime();
    	
    	for(RichWord s:out) {
    		txtWrong.appendText(s.getWord()+"\n");
    	}
    	txtNer.setText("numero errori "+out.size());
    	txtSec.setText("completato in "+(end-start));
    	
    }

    @FXML
    void initialize() {
    	assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNer != null : "fx:id=\"txtNer\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSec != null : "fx:id=\"txtSec\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'Scene.fxml'.";

    }
public void setModel(Dizionario m) {
    	
    	txtTesto.setDisable(true);
    	txtTesto.setText("Selezionare una lingua");	
    	txtWrong.setDisable(true);
    	boxLingua.getItems().addAll("English","Italian");
    	btnSpell.setDisable(true);
    	btnClear.setDisable(true);
 	
    	this.d = m;
    }
   

}


