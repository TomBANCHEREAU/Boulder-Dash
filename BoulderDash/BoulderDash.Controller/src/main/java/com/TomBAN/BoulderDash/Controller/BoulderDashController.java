package com.TomBAN.BoulderDash.Controller;

import java.sql.SQLException;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Model.Map;
import com.TomBAN.mySQL.MySQL;

public class BoulderDashController {
	private static final String URL = "jdbc:mysql://localhost:3306/a1-project5?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private BoulderDashModel model;
	private JFrame frame;
	public BoulderDashController(JFrame frame,GameMode gameMode) {
		this.frame = frame;
		loadMap(0);
		// TODO Auto-generated constructor stub
	}
	public Map loadMap(int id) {
		try {
			MySQL.Connect(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TODO 
		return new Map(5, 5, "#####\n#####\n#####\n#####\n#####");
	}
}
