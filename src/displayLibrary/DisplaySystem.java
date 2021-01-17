package displayLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DisplaySystem{

	private DisplayEnginePC de = null;
	ScreenInterface si = null;
	
	private Image waitingIm = null;
	private boolean isWaiting = false;
	private JFrame frame = null;

	public DisplaySystem(ScreenInterface pan,String waitingImage)
	{
		si = pan;
		de = new DisplayEnginePC(this);

		if(!waitingImage.equals(""))
		{
			isWaiting = true;
			try {
				waitingIm = ImageIO.read(new File(waitingImage));
			} catch (IOException e) {e.printStackTrace();}
		}
		
		frame = new JFrame();
//		frame.setResizable(true);
		frame.setTitle("Test");
		frame.setSize(300,80);
		// full screen
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setExtendedState(JFrame.ICONIFIED);
//		frame.setUndecorated(true);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(de);
		frame.addKeyListener(de);
		frame.addMouseListener(de);
		frame.addMouseWheelListener(de);

	}

	private Graphics2D g2d = null;
	public void paint()
	{
		isWaiting = false;
		de.repaint();
	}
		
	void drawing(Graphics2D g)
	{
		g2d = g;
		g2d.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		setColor(0,0,0);
		if(isWaiting) {
			drawImage(waitingIm,0,0,getWidth(),getHeight());
		} else {
			si.display(this);
		}
	}
	
	public Point getMousePosition()
	{
		try{
			Point location = MouseInfo.getPointerInfo().getLocation();
			Point locaScreen = frame.getLocationOnScreen();
			Point p = new Point(location.x-locaScreen.x - (frame.isUndecorated() ? 0 : 8), 
					location.y-locaScreen.y - (frame.isUndecorated() ? 0 : 31));
			return p;
		} catch(Exception e) {
			return new Point();
		}
	}
	
	public int getHeight()
	{
		return de.getHeight();
	}
	
	public int getWidth()
	{
		return de.getWidth();
	}
	
	public int getStringSize(String text)
	{
		int size = g2d.getFontMetrics().stringWidth(text);
		return size;
	}
	
	private ArrayList<Color> colors = new ArrayList<Color>();
	public void setColor(int r, int g, int b, int a)
	{
//		System.out.println(colors.size());
		Color col = null;
		for(int i = 0 ; i < colors.size() ; i ++)
		{
			Color colA = colors.get(i);
			if(colA.getRed() == r && colA.getGreen() == g && colA.getBlue() == b && colA.getAlpha() == a)
			{
				col = colA;
				colors.set(i,colors.get(0));
				colors.set(0,colA);
				break;
			}
		}
		if(col == null)
		{
			col = new Color(r, g, b, a);
			colors.add(col);
		}
		g2d.setColor(col);
	}
	
	public void setColor(int r, int g, int b) {
		setColor(r,g,b,255);
	}
	
	public void drawImage(Image img, int[] tab)
	{
		drawImage(img, tab[0], tab[1], tab[2], tab[3]);
	}
	
	public void drawImage(Image img, int dx1, int dy1, int sx1, int sy1)
	{
		g2d.drawImage(img, dx1, dy1, sx1, sy1, null);
	}
	
	public void contourOval(int dx, int dy, int width, int height)
	{
		g2d.drawOval(dx, dy, width, height);
	}
	
	public void drawString(String str, int dx, int dy)
	{
		g2d.drawString(str, dx, dy);
	}
	
	public void drawRect(int[] tab)
	{
		drawRect(tab[0], tab[1], tab[2], tab[3]);
	}
	public void drawRect(int dx1, int dy1, int sx1, int sy1)
	{
		g2d.fillRect(dx1, dy1, sx1, sy1);
	}
	
	public void contourRect(int[] tab)
	{
		contourRect(tab[0], tab[1], tab[2], tab[3]);
	}
	public void contourRect(int[] tab, int size)
	{
		contourRect(tab[0], tab[1], tab[2], tab[3], size);
	}
	public void contourRect(int dx1, int dy1, int sx1, int sy1)
	{
		g2d.drawRect(dx1, dy1, sx1, sy1);
	}
	public void contourRect(int dx1, int dy1, int sx1, int sy1, int size)
	{
		if(size > 0) {
			for(int i = 0 ; i < size ; i ++)
			g2d.drawRect(dx1 - i, dy1 - i, sx1 + i*2, sy1 + i*2);
		} else {
			for(int i = 0 ; i < -size ; i ++)
			g2d.drawRect(dx1 + i, dy1 + i, sx1 - i*2, sy1 - i*2);
		}
	}
	
	public void drawLine(int[] tab)
	{
		drawLine(tab[0], tab[1], tab[2], tab[3]);
	}
	public void drawLine(int dx1, int dy1, int sx1, int sy1)
	{
		g2d.drawLine(dx1, dy1, sx1, sy1);
	}
	
	
	
}
