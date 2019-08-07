//Jada Chang
//Methods Exercises, Graphics questions
//#10, 12
//April 2019
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsTesting extends JPanel implements MouseListener {
	static int game;
	
	public GraphicsTesting()
	{   game = 1;
		setPreferredSize(new Dimension (500, 500));
		setBackground(Color.white);
		addMouseListener(this);
	}

	//#10: Draw a happy face at size 100 x 100 pixels
	//(x, y) coordinates for circle, width/height of of circle
	public void drawHappyFace(Graphics g, int x, int y, int width, int height) {

		//circle
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);

		//eyes
		g.setColor(Color.black);
		g.fillOval(width/2 - 15, height/3, 10, 10);
		g.fillOval(width/2 + 20, height/3, 10, 10);

		//mouth
		g.setColor(Color.red);
		g.fillOval(x + 35, y + 40, (int)(width/3.33), (int)(height/3.33));
		g.setColor(Color.yellow);
		g.fillArc(x + 35, y + 40, (int)(width/3.33), (int)(height/3.33), 0, 180);

		//nose
		g.setColor(Color.black);
		g.drawLine(width / 2 + 7, y + 35, width / 2 + 7, y + 40);

		//mustache
		g.drawLine(width/2 - 5, y + 50, width/2 + 5, y + 48);
		g.drawLine(width/2 + 10, y + 48, width/2 + 20, y + 50);
	}
	
	/*#12: draw red diamond (20x20)
	 *x1, y1 = coordinates of start of first line (top vertex of diamond)
	 *x2, y2 = dimensions of the diamond
	 */
	public void drawDiamond(Graphics g, int x1, int y1, int x2, int y2){
	    int x = (x1+x2)/2;
	    int y = (y1+y2)/2;
	    g.setColor(Color.red);
	    g.drawLine(x1, y, x, y1);
	    g.drawLine(x, y1, x2, y);
	    g.drawLine(x2, y, x, y2);
	    g.drawLine(x, y2, x1, y);
	}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (game == 2)
			//#10: Call drawHappyFace
			drawHappyFace(g, 10, 10, 100, 100);
		else if (game == 3) 
			//#12: Call drawDiamond
			drawDiamond(g, 50, 50, 20, 20);
			
	}

	public static void main(String[] args){
		JFrame frame = new JFrame("Smiley");
		GraphicsTesting smiley = new GraphicsTesting();
		frame.add(smiley);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		game++;
		repaint ();

			
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}