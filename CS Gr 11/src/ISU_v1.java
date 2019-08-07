//Jada Chang
//June 2019
//2048 Game
//***MAKE FULL METHOD COMMENTS!!!!****

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ISU_v1 extends JPanel implements ActionListener, MouseListener{

	static JFrame frame;
	static JPanel panel;
	static JLabel label;
	static  JPopupMenu mainMenu;

	//constructor
	public ISU_v1(){
		//Graphics window setup
		setPreferredSize(new Dimension(500, 500));
		setLocation(0, 0);		
		setBackground(Color.white);

		//Load startup images
		ImageIcon logo = new ImageIcon("2048 welcome.png");
		label = new JLabel(logo);
		add(label);
		addMouseListener(this);

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

	//startup menu
	public void startMenu() {
		mainMenu = new JPopupMenu();
		JMenuItem newGame, credits, quit;
		newGame = new JMenuItem("New");
		credits = new JMenuItem("Credits");
		quit = new JMenuItem("Quit");

		// Add each item to the menu
		mainMenu.add(newGame);
		mainMenu.addSeparator();
		mainMenu.add(credits);
		mainMenu.addSeparator ();
		mainMenu.add(quit);

		// Action listener for menu items
		newGame.setActionCommand ("New");
		credits.setActionCommand("Credits");
		quit.setActionCommand ("Quit");

		JMenuBar menuBar = new JMenuBar ();
		menuBar.add (mainMenu);
		frame.setJMenuBar (menuBar);//test

		
	}

	// To handle menu items
	public void actionPerformed (ActionEvent event)
	{
		String eventName = event.getActionCommand ();
		if (eventName.equals ("New")){
			//newGame ();
		}else if(eventName.equals("Credits")) {
			//credits ();
		}else if(eventName.equals("Quit")) {
			System.exit (0);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println ("SFJ");
		showPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		showPopup(e);
	}

	private void showPopup(MouseEvent e) {
		System.out.println ("SLKFJ");
		startMenu();
//		if (e.isPopupTrigger()) {
//			mainMenu.show(e.getComponent(),
//					e.getX(), e.getY());
//		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		frame = new JFrame ("2048");
		JPanel myPanel = new ISU_v1();
		frame.add (myPanel);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.pack ();
		frame.setVisible (true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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

}
