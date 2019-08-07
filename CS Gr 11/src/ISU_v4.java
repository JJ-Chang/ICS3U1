//Jada Chang
//June 2019
//2048 Game

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ISU_v4 extends JPanel implements KeyListener, ActionListener {
	static JFrame frame;
	static JPanel panel;
	int[][] board = new int [5][5];
	int score = 0;
	boolean gameOver = false, musicPlaying, soundsON = true, sounds;

	Graphics offScreenBuffer;
	Image offScreenImage;
	Color colour;
	Graphics g;
	Font font, font2, font3, font4;
	AudioClip backGroundSound;
	AudioClip move;
	AudioClip lose;
	AudioClip win;

	final int SQUARE_SIZE = 95;
	final int TOP_OFFSET = 42;
	final int BORDER_SIZE = 4;

	//constructor
	public ISU_v4() {
		//Graphics window setup
		setPreferredSize(new Dimension(500, 600));
		setLocation(0, 0);		
		setBackground(Color.white);

		setFocusable (true); // Set focus to the panel to add the keyListener
		addKeyListener (this);
		font = new Font("Arial", Font.PLAIN, 25); 
		font2 = new Font("Arial", Font.PLAIN, 20);
		font3 = new Font("Arial", Font.PLAIN, 40);
		font4 = new Font("Arial", Font.PLAIN, 15);

		//background music
		backGroundSound = Applet.newAudioClip (getCompleteURL ("dreams.wav"));
		backGroundSound.loop ();
		musicPlaying = true;

		//Sound effects
		move = Applet.newAudioClip (getCompleteURL ("move.wav"));//when tiles move
		lose = Applet.newAudioClip(getCompleteURL("lost.wav"));//when board is full
		win = Applet.newAudioClip(getCompleteURL("win.wav"));//when game has a winner

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
		
		newGame();
		
		//set menu commands
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
	}//constructor

	//Clears the board and resets the score to 0
	void newGame ()
	{
		clearBoard (board);
		repaint ();
		score = 0;
		spawn();
		spawn();
		if(sounds == true)
			soundsON = true;
		score = 0;

		gameOver = false;
	}

	//Clears the board, resetting all tile values to 0
	static void clearBoard (int[] [] board)
	{
		for(int row = 0; row < board.length; row++)
			for(int column = 0; column < board[row].length; column++)
				board[row][column] = 0;
	}

	//Determines what colour to set the tile depending on the corresponding number
	//temp is the value of the tile to check for
	void findColour(int temp) {
		switch(temp) {
		case 0: 
			offScreenBuffer.setColor(Color.white);
			break;
		case 2:
			offScreenBuffer.setColor(new Color(238, 228, 218));
			break;
		case 4:
			offScreenBuffer.setColor(new Color(237, 224, 200));
			break;       
		case 8:
			offScreenBuffer.setColor(new Color(242, 177, 121));
			break;
		case 16:
			offScreenBuffer.setColor(new Color(234, 149, 99));
			break;
		case 32:
			offScreenBuffer.setColor(new Color(246, 124, 95));
			break;
		case 64:
			offScreenBuffer.setColor(new Color(246, 94, 59));
			break;
		case 128:
			offScreenBuffer.setColor(new Color(237, 207, 114));
			break;
		case 256:
			offScreenBuffer.setColor(new Color(237, 204, 97));
			break;
		case 512:
			offScreenBuffer.setColor(new Color(237, 200, 80));
			break;
		case 1024:
			offScreenBuffer.setColor(new Color(255, 51, 51));
			break;
		case 2048:
			offScreenBuffer.setColor(new Color(153, 0, 0));
			break;
		}
	}

	public void paintComponent (Graphics g)
	{
		// Set up the offscreen buffer the first time paint() is called
		if (offScreenBuffer == null)
		{
			offScreenImage = createImage (this.getWidth (), this.getHeight ());
			offScreenBuffer = offScreenImage.getGraphics ();

		}

		// Clear the offScreenBuffer
		offScreenBuffer.clearRect (0, 0, this.getWidth (), this.getHeight ());

		//Draw the board outline
		int xPos = (0) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + SQUARE_SIZE;
		offScreenBuffer.setColor(Color.black);
		offScreenBuffer.drawRect(xPos - 1, yPos - 2, 382, 382);
		//offScreenBuffer.fillRect(xPos - 1, yPos - 2, 382, 382);

		animate(g);
	}

	//draws all objects on the screen
	void animate (Graphics g) {
		int temp;
		boolean ok;
		int xPos = (0) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + SQUARE_SIZE;

		offScreenBuffer.setFont(font);
		
		//draw the score
		offScreenBuffer.setFont(font2);
		offScreenBuffer.setColor(Color.yellow);
		offScreenBuffer.fillRoundRect(280, 10, 110, 90, 30, 30);
		offScreenBuffer.setColor(Color.black);
		offScreenBuffer.drawString("Score:", 300, 50);
		offScreenBuffer.drawString(Integer.toString(score), 300, 75);
		
		//draw the 2048 logo
		offScreenBuffer.setFont(font3);
		offScreenBuffer.setColor(Color.orange);
		offScreenBuffer.fillRoundRect(20, 1, 130, 110, 30, 30);
		offScreenBuffer.setColor(Color.white);
		offScreenBuffer.drawString("2048", 40, 60);
		offScreenBuffer.setFont(font4);
		offScreenBuffer.drawString("by Jada Chang", 40, 100);
		
		offScreenBuffer.setFont(font);
		// Redraw the board with current pieces
		for (int row = 1 ; row < 5 ; row++)
			for (int column = 1 ; column < 5 ; column++)
			{
				temp = board[row][column];

				// Find the x and y positions for each row and column
				xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
				yPos = TOP_OFFSET + row * SQUARE_SIZE;

				// Draw each tile, depending on the value in board
				findColour(temp);
				offScreenBuffer.fillRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
				offScreenBuffer.setColor(Color.white);

				if(temp <= 8)
					offScreenBuffer.drawString(Integer.toString(temp), (xPos + SQUARE_SIZE / 2) - 5, (yPos + SQUARE_SIZE / 2) + 5);
				else if(temp <= 64)
					offScreenBuffer.drawString(Integer.toString(temp), (xPos + SQUARE_SIZE / 2) - 10, (yPos + SQUARE_SIZE / 2) + 5);
				else if(temp <= 512)
					offScreenBuffer.drawString(Integer.toString(temp), (xPos + SQUARE_SIZE / 2) - 20, (yPos + SQUARE_SIZE / 2) + 5);
				else if(temp <= 2048)
					offScreenBuffer.drawString(Integer.toString(temp), (xPos + SQUARE_SIZE / 2) - 25, (yPos + SQUARE_SIZE / 2) + 5);
			}

		// Transfer the offScreenBuffer to the screen
		g.drawImage (offScreenImage, 60, 40, this);
	}


	//spawns a new tile in a random empty spot
	void spawn() {
		Random rand = new Random();
		int x, y, value = 0, n;

		do {
			x = rand.nextInt(5);
			y = rand.nextInt(5);
		}while(board[x][y] != 0 || y == 0 || x == 0);

		n = rand.nextInt(3);
		if(n == 0)
			value = 2;
		else if(n == 1)
			value = 4;
		else if(n == 2)
			value = 8;

		board[x][y] = value;
		//System.out.print("value = " + value + "; ");
		//System.out.println("spot = board " + x + ", " + y);
		repaint();
	}

	//procedure when game over is true
	void ends() {
		if(soundsON == true) {
			lose.play();
			sounds = true;
		}
		soundsON = false;
		JOptionPane.showMessageDialog (this, "Board full :( GAME OVER. Start a new game through menu!",
				"Game Over", JOptionPane.WARNING_MESSAGE);
		return;
	}

	//procedure when tile 2048 is reached
	void wins() {
		if(soundsON == true)
			win.play();
		JOptionPane.showMessageDialog(this, "You reached 2048 :D WINNER!!! Start a new game through menu!");
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		frame = new JFrame ("2048");
		panel = new ISU_v4 ();

		frame.add (panel);
		frame.pack ();
		frame.setVisible (true);
		frame.setBackground(Color.white);
	}

	@Override
	//handles tile movement when arrow keys are pressed
	public void keyPressed(KeyEvent kp) {
		// TODO Auto-generated method stub
		int x, y;
		boolean moved = false;

		if (kp.getKeyCode () == KeyEvent.VK_RIGHT){
			for(int row = 4; row > 0; row--)
				for(int col = 4; col > 0; col--) {
					if(board[row][col] == board[row][col - 1]) {
						board[row][col] *= 2;
						board[row][col - 1] = 0;
						score += board[row][col] * 2;
						
						if(col < 4 && board[row][col + 1] == 0) {
							x = row;
							y = col;
							do {
								board[x][y + 1] = board[x][y];
								board[x][y] = 0;
								y++;
								if(y >= 3)
									break;
							}while(board[x][y + 1] == 0 && (y + 1 < 4));
							if(board[row][col] == board[row][col + 1]) {
								score += board[row][col + 1] * 2;
								board[row][col + 1] *= 2;
								board[row][col] = 0;
							}
						}

						moved = true;
					}else if(col < 4 && board[row][col + 1] == 0) {
						x = row;
						y = col;
						do {
							board[x][y + 1] = board[x][y];
							board[x][y] = 0;
							y++;
							if(y >= 3)
								break;
						}while(board[x][y + 1] == 0 && (y + 1 < 4));
						if(board[row][col] == board[row][col + 1]) {
							score += board[row][col + 1] * 2;
							board[row][col + 1] *= 2;
							board[row][col] = 0;
						}
						moved = true;
					}
				}
		}else if (kp.getKeyCode () == KeyEvent.VK_DOWN){
			for(int col = 4; col > 0; col--)
				for(int row = 4; row > 0; row--) {
					if(board[row][col] == board[row - 1][col]) {
						score += board[row][col] * 2;
						board[row][col] *= 2;
						board[row - 1][col] = 0;

						if(row < 4 && board[row + 1][col] == 0) {
							x = row;
							y = col;
							do {
								board[x + 1][y] = board[x][y];
								board[x][y] = 0;
								x++;
								if(x >= 4)
									break;
							}while(board[x + 1][y] == 0 && (x + 1 <= 4));
							if((x + 1 <= 4) && board[x][y] == board[x + 1][y]) {
								score += board[x + 1][y] * 2;
								board[x + 1][y] *= 2;
								score += board[x + 1][y] * 2;
								board[x][y] = 0;
							}
						}
						moved = true;
					}else if(row < 4 && board[row + 1][col] == 0) {
						x = row;
						y = col;
						do {
							board[x + 1][y] = board[x][y];
							board[x][y] = 0;
							x++;
							if(x >= 4)
								break;
						}while(board[x + 1][y] == 0 && (x + 1 <= 4));
						if((x + 1 < 4) && board[x][y] == board[x + 1][y]) {
							score += board[x + 1][y] * 2;
							board[x + 1][y] *= 2;
							score += board[x + 1][y] * 2;
							board[x][y] = 0;
						}
						moved = true;
					}
				}
		}else if (kp.getKeyCode () == KeyEvent.VK_LEFT){
			for(int row = 4; row > 0; row--)
				for(int col = 0; col < 4; col++) {
					if(board[row][col] == board[row][col + 1]) {
						score += board[row][col] * 2;
						board[row][col] *= 2;
						board[row][col + 1] = 0;

						if(col > 1 && board[row][col - 1] == 0) {
							x = row;
							y = col;
							do {
								board[x][y - 1] = board[x][y];
								board[x][y] = 0;
								y--;
								if(y <= 0)
									break;
							}while(board[x][y - 1] == 0 && (y - 1 > 0));
							if(board[x][y] == board[x][y - 1]) {
								score += board[x][y - 1] * 2;
								board[x][y - 1] *= 2;
								board[x][y] = 0;
							}
						}moved = true;
					}else if(col > 1 && board[row][col - 1] == 0) {
						x = row;
						y = col;
						do {
							board[x][y - 1] = board[x][y];
							board[x][y] = 0;
							y--;
							if(y <= 1)
								break;
						}while(board[x][y - 1] == 0 && (y - 1 > 1));
						if(board[row][col] == board[row][col - 1]) {
							score += board[row][col - 1] * 2;
							board[row][col - 1] *= 2;
							board[row][col] = 0;
						}moved = true;
					}
				}
		}else if (kp.getKeyCode() == KeyEvent.VK_UP){
			for(int col = 4; col > 0; col--)
				for(int row = 1; row < 4; row++) {
					if(board[row][col] == board[row + 1][col]) {
						score += board[row][col] * 2;
						board[row][col] *= 2;
						board[row + 1][col] = 0;

						if(row > 1 && board[row - 1][col] == 0) {
							x = row;
							y = col;
							do {
								board[x - 1][y] = board[x][y];
								board[x][y] = 0;
								x--;
								if(x <= 1)
									break;
							}while(board[x - 1][y] == 0 && (x - 1 < 1));
							if((x - 1 < 1) && board[x][y] == board[x - 1][y]) {
								score += board[x - 1][y] * 2;
								board[x - 1][y] *= 2;
								board[x][y] = 0;
							}
						}
						moved = true;
					}else if(row > 1 && board[row - 1][col] == 0) {
						x = row;
						y = col;
						do {
							board[x - 1][y] = board[x][y];
							board[x][y] = 0;
							x--;
							if(x <= 1)
								break;
						}while(board[x - 1][y] == 0 && (x - 1 > 1));
						if((x - 1 > 1) && board[x][y] == board[x - 1][y]) {
							score += board[x - 1][y] * 2;
							board[x - 1][y] *= 2;
							board[x][y] = 0;
						}
						moved = true;
					}
				}
		}
		repaint();
		
		for(int row = 0; row < 5; row++)
			for(int col = 0; col < 5; col++)
				if(board[row][col] == 2048)
					wins();

		if(moved == true) {
			if(soundsON == true)
				move.play();//sound effect
			spawn();
		}else if(moved == false) {
			gameOver = true;
			ends();	
		}

		moved = false;

		return;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	//to handle menu items
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
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
			sounds = false;
		}

	}

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

}
