//Jada Chang
//June 2019
//2048 Game

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ISU_v2 extends JPanel implements ActionListener{
	static JFrame frame;
	static JPanel panel;
	static JLabel label;
	static  JPopupMenu mainMenu;
	private JButton newGame, credits;
	static Graphics g;
	
	final int MAXX = frame.getWidth();
	final int MAXY = frame.getHeight();
	int[][] board = new int [4][4];

	//constructor
	public ISU_v2(){
		super();//test
		
		//Graphics window setup
		setPreferredSize(new Dimension(500, 500));
		setLocation(0, 0);		
		setBackground(Color.white);

		//Load startup images
		Image logo = Toolkit.getDefaultToolkit ().getImage ("2048 welcome.png");
		g.drawImage(logo, 175, 20, observer);//test

		//Main menu buttons
		newGame = new JButton("NEW GAME");
		newGame.setVerticalTextPosition(AbstractButton.CENTER);
		newGame.setHorizontalTextPosition(AbstractButton.CENTER);
		newGame.setActionCommand("new game");
		newGame.setVisible(true);//test
		newGame.setEnabled(true);//test
		
		credits = new JButton("CREDITS");
		credits.setVerticalTextPosition(AbstractButton.CENTER);
		credits.setHorizontalTextPosition(AbstractButton.CENTER);
		credits.setActionCommand("credits");
		credits.setVisible(true);//test
		credits.setEnabled(true);//test
		
		//Listen for actions on menu buttons
	    newGame.addActionListener(this);
	    credits.addActionListener(this);
	    repaint();//test

	    //check if window closed, end program
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing (java.awt.event.WindowEvent windowEvent) {
				try
				{
					Thread.sleep (1200);
				}
				catch (InterruptedException e)
				{
				}
				System.exit(0);
			}   
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		frame = new JFrame ("2048");
		JPanel myPanel = new ISU_v2();
		frame.add (myPanel);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.pack ();
		frame.setVisible (true);
		frame.setResizable(false);
		credits(g);//test
	}

	//Handle main menu button actions
	public void actionPerformed(ActionEvent e) {
	    if ("new game".equals(e.getActionCommand())) {
	        startNewGame();
	    	System.out.println("new game");//test
	    } else if ("credits".equals(e.getActionCommand())){
	        //make a credits page
	    	System.out.println("credits");//test
	    }
	} 
	
	public void startNewGame() {
		//clearBoard (board);
		//gameOver = false;
		repaint ();
	}
	
	//displays credits page
	public static void credits(Graphics g) {
		//clear jframe
		g.drawString("test", 10, 10);
		//show programmer
		//show date
		//show graphics
		//back to main menu button
	}
}
