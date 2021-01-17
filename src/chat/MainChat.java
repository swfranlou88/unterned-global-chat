package chat;

import java.awt.AWTException;
import java.awt.Robot;

import displayLibrary.DisplaySystem;

public class MainChat {

	public static boolean chatOn = false;
	
	public static void main(String[] args)
	{
		Screen screen = new Screen();
		DisplaySystem ds = new DisplaySystem(screen,"");
		
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e1) {e1.printStackTrace();}
		
		while(true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {e.printStackTrace();}
			
			if(chatOn)
				r.keyPress(19);
			
			ds.paint();
		}
	}
	
}
