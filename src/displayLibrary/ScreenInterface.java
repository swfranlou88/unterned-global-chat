package displayLibrary;

import java.awt.event.MouseWheelEvent;

public interface ScreenInterface {

	
	public void display(DisplaySystem ds);
	public void mouseWheelMoved(DisplaySystem ds, MouseWheelEvent arg0);
	public void mouseEntered(DisplaySystem ds);
	public void mouseExited(DisplaySystem ds);
	public void mousePressed(DisplaySystem ds, int arg0);
	public void mouseReleased(DisplaySystem ds, int arg0);
	public void keyPressed(DisplaySystem ds, int code, char character);
	public void keyReleased(DisplaySystem ds, int code, char character);
}
