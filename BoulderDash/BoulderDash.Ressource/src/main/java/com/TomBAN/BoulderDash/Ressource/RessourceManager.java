package com.TomBAN.BoulderDash.Ressource;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class RessourceManager {
	private static RessourceManager currentInstance;
	private HashMap<String, Image> images;
	private ArrayList<String> language;
	private HashMap<String, String> texts;
	private ArrayList<String> languageCode;
	private RessourceManager() {
		language = new ArrayList<String>();
		languageCode = new ArrayList<String>();
		images = new HashMap<String, Image>();
		texts = new HashMap<String, String>();
	}

	public static RessourceManager getInstance() {
		if(currentInstance==null) {
			currentInstance = new RessourceManager();
		}
		return currentInstance;
	}

	public void loadImages(String directory) {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File("data/Images.index")));
			String line = buff.readLine();
			while(line!=null) {
				images.put(line, ImageIO.read(new File("data/"+directory+"/"+line)));
				line = buff.readLine();
			}	
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadLanguages(String langCode) {
		try {
			texts = new HashMap<String, String>();
			BufferedReader buff = new BufferedReader(new FileReader(new File("data/"+ langCode +".lang")));
			String line = buff.readLine();
			while(line!=null) {
				texts.put(line.split(";")[0], line.split(";")[1]);
				line = buff.readLine();
			}
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadLanguageList() {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File("data/Language.index")));
			String line = buff.readLine();
			while(line!=null) {
				language.add(line.split(";")[1]);
				languageCode.add(line.split(";")[0]);
				line = buff.readLine();
			}
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Image getImage(String name) {
		if(images.containsKey(name)) {
			return images.get(name);
		}
		return null;
	}
	public String getText(String name) {
		if(texts.containsKey(name)) {
			return texts.get(name);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getLanguageList() {
		return (ArrayList<String>) language.clone();
	}
	@SuppressWarnings("unchecked")
	public ArrayList<String> getLanguageCodeList() {
		return (ArrayList<String>) languageCode.clone();
	}
	
	

//	public void loadText(String directory) {
//		// String[] data =
//	}
}
