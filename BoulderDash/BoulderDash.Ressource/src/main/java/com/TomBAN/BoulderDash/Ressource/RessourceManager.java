package com.TomBAN.BoulderDash.Ressource;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class RessourceManager {
	private static RessourceManager currentInstance;
	HashMap<String, Image> images;
	private RessourceManager() {
		// TODO Auto-generated constructor stub
	}

	public static RessourceManager getInstance() {
		if(currentInstance==null) {
			currentInstance = new RessourceManager();
		}
		return currentInstance;
	}

	public void loadImages(String directory) {
		images = new HashMap<String, Image>();
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File("data/images.index")));
			String line = buff.readLine();
			while(line!=null) {
				images.put(line, ImageIO.read(new File("data/"+directory+"/"+line)));
				line = buff.readLine();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(String name) {
		if(images.containsKey(name)) {
			return images.get(name);
		}
		return null;
	}
	
	

//	public void loadText(String directory) {
//		// String[] data =
//	}
}
