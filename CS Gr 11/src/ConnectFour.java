/*May 2019
 *Connect Four Assignment
 *Extra features: background music, sound effects for dropping fruit, full row, and winner
 *Sub menu to turn music / SFX on / off, with icons as options
 *Game keeps track of score, displays points every time a team wins
*/
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

public class ConnectFour extends JPanel implements ActionListener, MouseListener, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame frame;
	AudioClip backGroundSound;
	AudioClip drop;
	AudioClip full;
	AudioClip win;
	final int BANANA = -1;
	final int STRAWBERRY = 1;
	final int EMPTY = 0;
	final int SQUARE_SIZE = 60;
	final int TOP_OFFSET = 42;
	final int BORDER_SIZE = 4;

	int[] [] board;
	int currentPlayer;
	int currentColumn;
	int bScore = 0;
	int sScore = 0;
	Image firstImage, secondImage;
	boolean musicPlaying;
	boolean soundsON = true;
	boolean temp;
	String show;

	Timer timer;

	// For drawing images offScreen (prevents Flicker)
	// These variables keep track of an off screen image object and
	// its corresponding graphics object
	Image offScreenImage;
	Graphics offScreenBuffer;

	boolean gameOver;

	public ConnectFour ()
	{
		// Setting the defaults for the panel
		setPreferredSize (new Dimension (7 * SQUARE_SIZE + 2 * BORDER_SIZE + 1, (6 + 1) * SQUARE_SIZE + TOP_OFFSET + BORDER_SIZE + 1));
		setLocation (100, 10);
		setBackground (new Color (200, 200, 200));
		setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

		board = new int [8] [9];

		// Set up the Menu
		// Set up the Game MenuItems
		JMenuItem newOption, exitOption, musicON, musicOFF, SFXon, SFXoff;
		newOption = new JMenuItem ("New");
		exitOption = new JMenuItem ("Exit");

		// Set up the Game Menu
		JMenu gameMenu = new JMenu ("Game");
		JMenu soundMenu;

		//Sound sub menu
		gameMenu.addSeparator();
		soundMenu = new JMenu("Sound");

		//assign sound menu icons
		ImageIcon music = new ImageIcon("music.png");
		ImageIcon noMusic = new ImageIcon("no music.png");
		ImageIcon sfx = new ImageIcon("sound.png");
		ImageIcon noSFX = new ImageIcon("no sound.png");

		musicON = new JMenuItem(music);
		musicOFF = new JMenuItem(noMusic);
		SFXon = new JMenuItem(sfx);
		SFXoff = new JMenuItem(noSFX);

		//add sound menu items
		soundMenu.add(musicON);
		soundMenu.addSeparator();
		soundMenu.add(musicOFF);
		soundMenu.addSeparator();
		soundMenu.add(SFXon);
		soundMenu.addSeparator();
		soundMenu.add(SFXoff);

		// Add each MenuItem to the Game Menu (with a separator)
		gameMenu.add(soundMenu);
		gameMenu.addSeparator();
		gameMenu.add (newOption);
		gameMenu.addSeparator ();
		gameMenu.add (exitOption);

		JMenuBar mainMenu = new JMenuBar ();
		mainMenu.add (gameMenu);
		// Set the menu bar for this frame to mainMenu
		frame.setJMenuBar (mainMenu);

		// Use a media tracker to make sure all of the images are
		// loaded before we continue with the program
		MediaTracker tracker = new MediaTracker (this);
		firstImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");
		tracker.addImage (firstImage, 0);
		secondImage = Toolkit.getDefaultToolkit ().getImage ("strawberry.gif");
		tracker.addImage (secondImage, 1);

		//  Wait until all of the images are loaded
		try
		{
			tracker.waitForAll ();
		}
		catch (InterruptedException e)
		{
		}

		// Set up the icon image (Tracker not needed for the icon image)
		Image iconImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");
		frame.setIconImage (iconImage);

		// Start a new game and then make the window visible
		newGame ();

		newOption.setActionCommand ("New");
		newOption.addActionListener (this);
		exitOption.setActionCommand ("Exit");
		exitOption.addActionListener (this);
		musicON.setActionCommand("music on");
		musicON.addActionListener (this);
		musicOFF.setActionCommand("music off");
		musicOFF.addActionListener (this);
		SFXon.setActionCommand("sound on");
		SFXon.addActionListener (this);
		SFXoff.setActionCommand("sound off");
		SFXoff.addActionListener (this);

		

		addMouseListener (this);

		//background music
		backGroundSound = Applet.newAudioClip (getCompleteURL ("Crinoline Dreams.wav"));
		backGroundSound.loop ();
		musicPlaying = true;

		//Sound effects
		drop = Applet.newAudioClip (getCompleteURL ("drop.wav"));//drop fruit
		full = Applet.newAudioClip(getCompleteURL("full.wav"));//when row is full
		win = Applet.newAudioClip(getCompleteURL("win.wav"));//when game has a winner

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
	} // Constructor


	// To handle normal menu items
	public void actionPerformed (ActionEvent event)
	{
		String eventName = event.getActionCommand ();
		if (eventName.equals ("New"))
		{
			newGame ();
		}
		else if (eventName.equals ("Exit"))
		{
			System.exit (0);
		}
		else if(eventName.equals ("music on"))
		{
			if(musicPlaying == false)
				backGroundSound.loop();
		}
		else if(eventName.equals("music off"))
		{
			backGroundSound.stop();
			musicPlaying = false;
		}
		else if(eventName.equals("sound on")) 
		{
			soundsON = true;
		}
		else if(eventName.equals("sound off"))
		{
			soundsON = false;
			temp = false;
		}

	}


	public void newGame ()
	{
		currentPlayer = BANANA;
		clearBoard (board);
		gameOver = false;
		currentColumn = 3;
		repaint ();
		if(temp == true)
			soundsON = true;
	}

	//------------YOUR CODE GOES HERE  ------------------//

	static void clearBoard (int[] [] board)
	{
		for(int row = 0; row < board.length; row++)
			for(int column = 0; column < board[row].length; column++)
				board[row][column] = 0;
	}


	static int findNextRow (int[] [] board, int column)
	{
		int next = -1;
		for(int row = 0; row < board.length - 1; ++row) {
			if(board[row][column] == 0)
				next = row;
		}
		return next;

	}

	public int checkForWinner (int[] [] board, int lastRow, int lastColumn)
	{
		int check1, check2;
		boolean win = false;

		check1 = board[lastRow][lastColumn];
		for(int i = lastColumn + 1; i < lastColumn + 4; ++i) {//Check horizontal right
			check2 = board[lastRow][i];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
		}
		if(win == true) {
			//System.out.println(1);//*****
			return check1;
		}

		for(int i = lastColumn - 1; i > lastColumn - 4; --i) {//check horizontal left
			check2 = board[lastRow][i];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
		}
		if(win == true) {
			//System.out.println(2);//*****
			return check1;
		}

		for(int i = lastRow + 1; i < lastRow + 4; ++i) {//Check vertical down
			check2 = board[i][lastColumn];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
		}
		if(win == true) {
			//System.out.println(3);//*****
			return check1;
		}

		int a = lastColumn - 1;
		for(int i = lastRow + 1; i < lastRow + 4; ++i) {//check diagonal up left
			check2 = board[i][a];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
			a--;
		}
		if(win == true) {
			//System.out.println(4);//*****
			return check1;
		}

		a = lastColumn + 1;
		for(int i = lastRow + 1; i < lastRow + 4; ++i) {//check diagonal up right
			check2 = board[i][a];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
			a++;
		}
		if(win == true) {
			//System.out.println(5);//*****
			return check1;
		}

		a = lastColumn - 1;
		for(int i = lastRow - 1; i > lastRow - 4; --i) {//check diagonal down left
			check2 = board[i][a];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
			a--;
		}
		if(win == true) {
			//System.out.println(6);//*****
			return check1;
		}

		a = lastColumn + 1;
		for(int i = lastRow - 1; i > lastRow - 4; --i) {//check diagonal down right
			check2 = board[i][a];
			if(check1 == check2) 
				win = true;
			else {
				win = false;
				break;
			}
			a++;
		}
		if(win == true) {
			//System.out.println(7);//*****
			return check1;
		}

		for(int i = lastColumn - 1; i > lastColumn - 3; --i) {//check 2 left, 1 right horizontal
			check2 = board[lastRow][i];
			if(check1 == check2)
				win = true;
			else {
				win = false;
				break;
			}
		}
		if(win == true) {
			check2 = board[lastRow][lastColumn + 1];
			if(check1 == check2) {
				//System.out.println(8);//*****
				return check1;
			}
		}

		for(int i = lastColumn + 1; i < lastColumn + 3; ++i) {//check 1 left, 2 right horizontal
			check2 = board[lastRow][i];
			if(check1 == check2)
				win = true;
			else {
				win = false;
				break;
			}
		}
		if(win == true) {
			check2 = board[lastRow][lastColumn - 1];
			if(check1 == check2) {
				//System.out.println(9);//*****
				return check1;
			}
		}

		a = lastColumn - 1;
		for(int i = lastRow + 1; i < lastRow + 3; ++i) {//check diagonal 2 up left, 1 down right
			check2 = board[i][a];
			if(check1 == check2)
				win = true;
			else {
				win = false;
				break;
			}
			a--;
		}
		if(win == true) {
			check2 = board[lastRow - 1][lastColumn + 1];
			if(check1 == check2) {
				//System.out.println(10);//*****
				return check1;
			}
		}

		a = lastColumn + 1;
		for(int i = lastRow - 1; i > lastRow - 3; i--) {//check diagonal 2 down right, 1 up left
			check2 = board[i][a];
			if(check1 == check2)
				win = true;
			else {
				win = false;
				break;
			}
			a++;
		}		
		if(win == true) {
			check2 = board[lastRow + 1][lastColumn - 1];
			if(check1 == check2) {
				//System.out.println(11);//*****
				return check1;
			}
		}

		a = lastColumn + 1;
		for(int i = lastRow + 1; i < lastRow + 3; ++i) {//check diagonal 2 up right, 1 down left
			check2 = board[i][a];
			if(check1 == check2)
				win = true;
			else {
				win = false;
				break;
			}
			a++;
		}
		if(win == true) {
			check2 = board[lastRow - 1][lastColumn - 1];
			if(check1 == check2) {
				//System.out.println(12);//*****
				return check1;
			}
		}

		a = lastColumn - 1;
		for(int i = lastRow - 1; i > lastRow - 3; --i) {//check diagonal 2 down left, 1 up right
			check2 = board[i][a];
			if(check1 == check2)
				win = true;
			else {
				win = false;
				break;
			}
			--a;
		}
		if(win == true) {
			check2 = board[lastRow + 1][lastColumn + 1];
			if(check1 == check2) {
				//System.out.println(13);//*****
				return check1;
			}
		}

		return EMPTY;
	}
	//----------------------------------------------------//


	public void handleAction (int x, int y)
	{
		if (gameOver)
		{
			if(soundsON == true)
				temp = true;
			soundsON = false;
			JOptionPane.showMessageDialog (this, "Please Select Game...New to start a new game",
					"Game Over", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int column = (x - BORDER_SIZE) / SQUARE_SIZE + 1;
		int row = findNextRow (board, column);
		if (row <= 0)
		{
			if(soundsON == true)
				full.play();
			JOptionPane.showMessageDialog (this, "Please Select another Column",
					"Column is Full", JOptionPane.WARNING_MESSAGE);
			return;
		}

		animatePiece (currentPlayer, column, row);
		board [row] [column] = currentPlayer;

		int winner = checkForWinner (board, row, column);

		if (winner == BANANA)
		{
			gameOver = true;
			repaint ();
			if(soundsON == true)
				win.play();
			bScore++;
			show = "Banana Wins!!! Score // banana: " + bScore + " points, strawberry: " + sScore + " points";
			JOptionPane.showMessageDialog (this, show,
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (winner == STRAWBERRY)
		{
			gameOver = true;
			repaint ();
			if(soundsON == true)
				win.play();
			sScore++;
			show = "Strawberry Wins!!! Score // banana: " + bScore + " points, strawberry: " + sScore + " points";
			JOptionPane.showMessageDialog (this, show,
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		else
			// Switch to the other player
			currentPlayer *= -1;
		currentColumn = 3;

		repaint ();
	}


	// MouseListener methods
	public void mouseClicked (MouseEvent e)
	{
		int x, y;
		x = e.getX ();
		y = e.getY ();
		if(soundsON == true)
			drop.play();//sound effect

		handleAction (x, y);
	}


	public void mouseReleased (MouseEvent e)
	{
	}


	public void mouseEntered (MouseEvent e)
	{
	}


	public void mouseExited (MouseEvent e)
	{
	}


	public void mousePressed (MouseEvent e)
	{
	}


	//KeyListener methods
	public void keyPressed (KeyEvent kp)
	{
		if (kp.getKeyCode () == KeyEvent.VK_RIGHT)
		{
			if (currentColumn < 6)
				currentColumn++;
		}
		else if (kp.getKeyCode () == KeyEvent.VK_DOWN)
		{
			handleAction ((currentColumn) * SQUARE_SIZE + BORDER_SIZE, 0);
		}
		else if (kp.getKeyCode () == KeyEvent.VK_LEFT)
		{
			if (currentColumn > 0)
				currentColumn--;
		}
		else
			return;
		repaint ();
	}


	public void keyReleased (KeyEvent e)
	{
	}


	public void keyTyped (KeyEvent e)
	{
	}


	public void animatePiece (int player, int column, int finalRow)
	{
		Graphics g = getGraphics ();

		// Find the x and y positions for each row and column
		int xPos = (4 - 1) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + 0 * SQUARE_SIZE;
		offScreenBuffer.clearRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
		for (double row = 0 ; row < finalRow ; row += 0.10)
		{
			// Find the x and y positions for each row and column
			xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
			yPos = (int) (TOP_OFFSET + row * SQUARE_SIZE);
			// Redraw the grid for this column
			for (int gridRow = 1 ; gridRow <= 6 ; gridRow++)
			{
				// Draw the squares
				offScreenBuffer.setColor (Color.black);
				offScreenBuffer.drawRect ((column - 1) * SQUARE_SIZE + BORDER_SIZE,
						TOP_OFFSET + gridRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}

			// Draw each piece, depending on the value in board
			if (player == BANANA)
				offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			else if (player == STRAWBERRY)
				offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);

			// Transfer the offScreenBuffer to the screen
			g.drawImage (offScreenImage, 0, 0, this);
			delay (3);
			offScreenBuffer.clearRect (xPos + 1, yPos + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);

			//play fruit drop sound
			//bgTime = ((Object) backGroundSound).getMicrosecondPostion();
			//backGroundSound.stop();
			//backGroundSound.setMicrosecondPosition(bgTime);
			//backGroundSound.start();
		}
	}


	// Avoid flickering -- smoother graphics
	public void update (Graphics g)
	{
		paint (g);
	}


	public void paintComponent (Graphics g)
	{

		// Set up the offscreen buffer the first time paint() is called
		if (offScreenBuffer == null)
		{
			offScreenImage = createImage (this.getWidth (), this.getHeight ());
			offScreenBuffer = offScreenImage.getGraphics ();
		}

		// All of the drawing is done to an off screen buffer which is
		// then copied to the screen.  This will prevent flickering
		// Clear the offScreenBuffer first
		offScreenBuffer.clearRect (0, 0, this.getWidth (), this.getHeight ());

		// Redraw the board with current pieces
		for (int row = 1 ; row <= 6 ; row++)
			for (int column = 1 ; column <= 7 ; column++)
			{
				// Find the x and y positions for each row and column
				int xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
				int yPos = TOP_OFFSET + row * SQUARE_SIZE;

				// Draw the squares
				offScreenBuffer.setColor (Color.black);
				offScreenBuffer.drawRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);

				// Draw each piece, depending on the value in board
				if (board [row] [column] == BANANA)
					offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
				else if (board [row] [column] == STRAWBERRY)
					offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			}

		// Draw next player
		if (!gameOver)
			if (currentPlayer == BANANA)
				offScreenBuffer.drawImage (firstImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);
			else
				offScreenBuffer.drawImage (secondImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);

		// Transfer the offScreenBuffer to the screen
		g.drawImage (offScreenImage, 0, 0, this);
	}


	/** Purpose: To delay the given number of milliseconds
	 * @param milliSec The number of milliseconds to delay
	 */
	private void delay (int milliSec)
	{
		try
		{
			Thread.sleep (milliSec);
		}
		catch (InterruptedException e)
		{
		}
	}


	public static void main (String[] args)
	{
		frame = new JFrame ("Connect Four");
		ConnectFour myPanel = new ConnectFour ();

		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);

	} // main method

	//gets URL needed for audio clip
	public URL getCompleteURL (String fileName)
	{
		try
		{
			return new URL ("file:" + System.getProperty ("user.dir") + "/" + fileName);
		}
		catch (MalformedURLException e)
		{
			System.err.println (e.getMessage ());
		}
		return null;
	}
} // ConnectFourWorking class


