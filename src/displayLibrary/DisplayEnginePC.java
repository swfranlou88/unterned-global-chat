package displayLibrary;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

class DisplayEnginePC extends JPanel implements KeyListener, MouseListener, MouseWheelListener{

	private static final long serialVersionUID = 1L;

	private DisplaySystem diplaySystem = null;
	
	DisplayEnginePC(DisplaySystem ds)
	{
		diplaySystem = ds;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		diplaySystem.si.mouseWheelMoved(diplaySystem, arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	//	System.out.println("mouseClicked");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		diplaySystem.si.mouseEntered(diplaySystem);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		diplaySystem.si.mouseExited(diplaySystem);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		diplaySystem.si.mousePressed(diplaySystem, arg0.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		diplaySystem.si.mouseReleased(diplaySystem, arg0.getButton());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		diplaySystem.si.keyPressed(diplaySystem, arg0.getKeyCode(), arg0.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		diplaySystem.si.keyReleased(diplaySystem, arg0.getKeyCode(), arg0.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	//	System.out.println("keyTyped");
	}
	
	public void paintComponent (Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		diplaySystem.drawing(g2d);
	}

}
