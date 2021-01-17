package chat;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseWheelEvent;

import displayLibrary.DisplaySystem;
import displayLibrary.ScreenInterface;

public class Screen implements ScreenInterface{
	
	private static final int BUTTON_MICRO = 0;
	private static final int BUTTON_INITIALIZE = 1;
	
	private static final String APX = "px";
	private static final String APY = "py";
	private static final String ASX = "sx";
	private static final String ASY = "sy";
	
	@Override
	public void display(DisplaySystem ds) {
		Point mouse = ds.getMousePosition();
		ds.setColor(127, 127, 127);
		ds.drawRect(0, 0, ds.getWidth(), ds.getHeight());
		
		ds.setColor(0, 0, 0);
		if(onButton(mouse, getButtonPostition(ds, BUTTON_MICRO)))
			ds.setColor(0, 255, 0);
		ds.contourRect(getButtonPostition(ds, BUTTON_MICRO));
		ds.setColor(0, 0, 0);
		ds.drawString("Micro : ", 13, 27);
		
		if(MainChat.chatOn) {
			ds.setColor(0, 255, 0);
			ds.drawString("ON", 80, 27);
		} else {
			ds.setColor(255, 0, 0);
			ds.drawString("OFF", 80, 27);
		}
		
		ds.setColor(0, 0, 0);
		if(onButton(mouse, getButtonPostition(ds, BUTTON_INITIALIZE)))
			ds.setColor(0, 0, 255);
		ds.contourRect(getButtonPostition(ds, BUTTON_INITIALIZE));
		ds.drawString("Initialize", 143, 27);
	}

	@Override
	public void mouseWheelMoved(DisplaySystem ds, MouseWheelEvent arg0) {
		
	}

	@Override
	public void mouseEntered(DisplaySystem ds) {
		
	}

	@Override
	public void mouseExited(DisplaySystem ds) {
		
	}

	@Override
	public void mousePressed(DisplaySystem ds, int arg0)
	{
		Point mouse = ds.getMousePosition();
		
		if(onButton(mouse, getButtonPostition(ds, BUTTON_MICRO)))
			MainChat.chatOn = !MainChat.chatOn;
		
		if(onButton(mouse, getButtonPostition(ds, BUTTON_INITIALIZE)))
		{
			try {
				Thread.sleep(10000);
			
				Robot r = new Robot();
				r.keyPress(19);

			} catch (Exception e) {e.printStackTrace();}
		}
	}

	@Override
	public void mouseReleased(DisplaySystem ds, int arg0) {
		
	}

	@Override
	public void keyPressed(DisplaySystem ds, int code, char character) {
		System.out.println(code);
	}

	@Override
	public void keyReleased(DisplaySystem ds, int code, char character) {
		
	}
	
	private static boolean onButton(Point mouse, int[] Button)
	{
		return onButton(mouse, Button[0], Button[1], Button[2], Button[3]);
	}
	
	private static boolean onButton(Point mouse, int ButtonX, int ButtonY, int sizeX, int sizeY)
	{
		if(mouse.x > ButtonX && mouse.x < ButtonX + sizeX && mouse.y > ButtonY && mouse.y < ButtonY + sizeY)
			return true;
		return false;
	}
	
	private static int[] getButtonPostition(DisplaySystem ds, int buttonNum)
	{
		int[] tab = new int[4];
		
		if(buttonNum == BUTTON_MICRO) {
			tab[0] = 10;
			tab[1] = 10;
			tab[2] = 120;
			tab[3] = 20;
		} else if(buttonNum == BUTTON_INITIALIZE) {
			tab[0] = 140;
			tab[1] = 10;
			tab[2] = 100;
			tab[3] = 20;
		}
		
		return tab;
	}
	/*
	private static int getButtonPostition(DisplaySystem ds, int buttonNum, String type)
	{
		int[] tab = getButtonPostition(ds, buttonNum);
		
		if(type.equals(APX)) {
			return tab[0];
		} else if(type.equals(APY)) {
			return tab[1];
		} else if(type.equals(ASX)) {
			return tab[2];
		} else if(type.equals(ASY)) {
			return tab[3];
		}
		
		return -1;
	}
	*/
}
