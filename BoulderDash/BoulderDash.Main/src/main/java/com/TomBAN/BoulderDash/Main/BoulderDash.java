package com.TomBAN.BoulderDash.Main;

import com.TomBAN.BoulderDash.Controller.BoulderDashController;
import com.TomBAN.BoulderDash.Controller.GameMode;
import com.TomBAN.BoulderDash.Frame.BoulderDashFrame;

public class BoulderDash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoulderDashController controller = new BoulderDashController(new BoulderDashFrame(1280,720),GameMode.SinglePlayer);
	}

}
