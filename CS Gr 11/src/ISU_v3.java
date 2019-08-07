//Jada Chang
//June 2019
//2048 Game

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ISU_v3 extends JPanel implements KeyListener{
	static JFrame frame;
	static JPanel panel;
	int[][] board = new int [5][5];
	int score = 0;

	static Font font;
	//Font farmToMarket;
	Graphics offScreenBuffer;
	Image offScreenImage;
	Color colour;
	Graphics g;

	final int SQUARE_SIZE = 95;
	final int TOP_OFFSET = 42;//test
	final int BORDER_SIZE = 4;//test
	boolean gameOver = false;

	//constructor
	public ISU_v3() {
		//Graphics window setup
		setPreferredSize(new Dimension(500, 600));
		setLocation(0, 0);		
		setBackground(Color.white);

		setFocusable (true); // Need this to set the focus to the panel in order to add the keyListener
		addKeyListener (this);

		//		try {
		//		font = Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf"));
		//		font.deriveFont(12f);
		//		//font = new Font (farmToMarket, Font.PLAIN, 25);
		//		}
		//		catch (FontFormatException | IOException e) {
		//			System.out.println ("DSKFJ");//test
		//		}
		font = new Font("Hover-Classic-Lite", Font.PLAIN, 25); 

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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		frame = new JFrame ("2048");
		panel = new ISU_v3 ();

		frame.add (panel);
		frame.pack ();
		frame.setVisible (true);
		frame.setBackground(Color.white);
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
		animate(g);
		spawn();
		spawn();
	}

	void animate (Graphics g) {
		int temp = 1;//test
		boolean ok;
		
		// Set up the offscreen buffer the first time paint() is called
		if (offScreenBuffer == null)
		{
			offScreenImage = createImage (this.getWidth (), this.getHeight ());
			offScreenBuffer = offScreenImage.getGraphics ();

		}

		// Clear the offScreenBuffer
		offScreenBuffer.clearRect (0, 0, this.getWidth (), this.getHeight ());
		//font = new Font("Courier", Font.PLAIN, 30);
		offScreenBuffer.setFont(font);
		//draw the board outline
		int xPos = (0) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + SQUARE_SIZE;
		offScreenBuffer.setColor(Color.black);
		offScreenBuffer.drawRect(xPos - 1, yPos - 2, 382, 382);

		// Redraw the board with current pieces
		for (int row = 1 ; row < 5 ; row++)
			for (int column = 1 ; column < 5; column++)
			{
				//temp = board[row][column];
				if(temp == 2048)
					temp = 1;
				temp *= 2;

				// Find the x and y positions for each row and column
				xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
				yPos = TOP_OFFSET + row * SQUARE_SIZE;

				// Draw each tile, depending on the value in board
				findColour(temp);
				offScreenBuffer.fillRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
				offScreenBuffer.setColor(Color.white);

				if(temp == 0)
					ok = true;
				else if(temp <= 8 )
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
		delay(3);
	}

	@Override
	public void keyPressed(KeyEvent kp) {
		// TODO Auto-generated method stub
		if (kp.getKeyCode () == KeyEvent.VK_RIGHT){
			for(int row = 0; row < 3; row++)
				for(int col = 0; col < 3; col++) {
					if(board[row][col + 1] == 0) {
						board[row][col + 1] = board[row][col];
						board[row][col] = 0;
					}else if(board[row][col] == board[row][col + 1]) {
						board[row][col] = 0;
						board[row][col + 1] *= 2;
						//play positive SFX
						animate(g);
						repaint();
					}else {
						System.out.print("no move");//test
						gameOver = checkGameOver();
						//play negative SFX
					}
				}
		}else if (kp.getKeyCode () == KeyEvent.VK_DOWN){
			for(int col = 0; col < 3; col++)
				for(int row = 0; row < 3; row++) {
					if(board[row + 1][col] == 0) {
						board[row + 1][col] = board[row][col];
						board[row][col] = 0;
					}else if(board[row][col] == board[row + 1][col]) {
						board[row][col] = 0;
						board[row + 1][col] *= 2;
						//play positive SFX
						animate(g);
						repaint();
					}else {
						System.out.print("no move");//test
						gameOver = checkGameOver();
						//play negative SFX
					}
				}
		}else if (kp.getKeyCode () == KeyEvent.VK_LEFT){
			for(int row = 3; row > 0; row++)
				for(int col = 3; col > 0; col--) {
					if(board[row][col - 1] == 0) {
						board[row][col - 1] = board[row][col];
						board[row][col] = 0;
					}else if(board[row][col] == board[row][col - 1]) {
						board[row][col] = 0;
						board[row][col + 1] *= 2;
						//play positive SFX
						animate(g);
						repaint();
					}else {
						System.out.print("no move");//test
						gameOver = checkGameOver();
						//play negative SFX
					}
				}
		}else if (kp.getKeyCode() == KeyEvent.VK_UP){
			for(int col = 3; col > 0; col++)
				for(int row = 3; col > 0; row--) {
					if(board[row - 1][col] == 0) {
						board[row - 1][col] = board[row][col];
						board[row][col] = 0;
					}if(board[row][col] == board[row - 1][col]) {
						board[row][col] = 0;
						board[row - 1][col] *= 2;
						//play positive SFX
						//animate(g);
						repaint();
					}else {
						System.out.print("no move");//test
						gameOver = checkGameOver();
						//play negative SFX
					}
				}
		}else
			return;
		repaint ();
	}

	//activates spawn
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

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

	//spawns new tiles after each turn
	void spawn() {
		Random rand = new Random();
		int x, y, value = 0, n;

		do {
			x = rand.nextInt(4);
			y = rand.nextInt(4);
		}while(board[x][y] != 0);

		n = rand.nextInt(3);
		if(n == 0)
			value = 2;
		else if(n == 1)
			value = 4;
		else if(n == 2)
			value = 8;

		board[x][y] = value;
	}

	boolean checkGameOver() {
		boolean end = true;

		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
				if(board[row][col] == 0) {
					end = false;
					break;
				}
		return end;
	}
}
