package Lights_Out;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @author Andrew Fryzel CS140-001 A9 "Lights Out"
 *
 */

public class LightsOut extends JPanel implements ActionListener {

	/**
	 * Instance variables for the LightsOut class
	 */
	private LightButton[][] buttons;

	JButton quitButton;
	JButton restart;
	JButton hint;
	JPanel panel;
	JPanel panel2;
	private int counter = 0;
	JLabel notWork;
	JLabel winner;
	JLabel clicks;

	JFrame mainWindow;

	/**
	 * LightsOut constructor. Declares and initializes needed variables.
	 * 
	 * Add buttons and GUI elements to a JPanel that is added to a JFrame
	 */
	public LightsOut() {

		super();
		JPanel panel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setPreferredSize(new Dimension(250, 250));
		panel.setLayout(new GridLayout(5, 5));

		buttons = new LightButton[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				LightButton button = new LightButton(i, j);

				buttons[i][j] = button;
				panel.add(button);
				button.addActionListener(this);
				button.setBackground(Color.black);

			}
		}

		random();

		quitButton = new JButton("Quit");
		JPanel panel2 = new JPanel();
		panel2.add(quitButton);
		add(panel2);
		add(panel);

		restart = new JButton("Reset");
		panel2.add(restart);
		add(panel2);
		add(panel);

		hint = new JButton("Hint");
		panel2.add(hint);
		add(panel2);
		add(panel);

		clicks = new JLabel("Number of Clicks " + counter);
		panel2.add(clicks);
		add(panel2);
		add(panel);

		quitButton.addActionListener(this);
		restart.addActionListener(this);
		hint.addActionListener(this);

	}

	/**
	 * This is the method that is invoked when a button is selected. Determines
	 * which button was selected then performs a specific action
	 * 
	 * @param --
	 *            an object that represents the event of the button being selected
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof LightButton) {

			LightButton button = (LightButton) e.getSource();

			buttonPress(button);
			counter++;
			clicks.setText("Number of Clicks " + counter);

			win();

		}

		if (e.getSource() == (quitButton)) {

			System.exit(0);

		}

		if (e.getSource().equals(restart)) {
			random();

		}

		if (e.getSource().equals(hint)) {
			hint();

		}
	}

	/**
	 * Method that takes a button pressed at a position [x][y] and changes it's
	 * color. Then changes the buttons color above, below to the right and to the
	 * left of the pressed button to the opposite color.
	 * 
	 * @param button
	 *            a LightButton that is used to determine where on the grid a button
	 *            was pressed and then to change the surrounding button's colors.
	 */
	private void buttonPress(LightButton button) {

		LightButton button2 = buttons[button.gettX()][button.gettY()];

		button2.switchColor();

		if (button.gettX() > 0) {
			button2 = buttons[button.gettX() - 1][button.gettY()];
			button2.switchColor();

		}

		if (button.gettX() < 4) {
			button2 = buttons[button.gettX() + 1][button.gettY()];
			button2.switchColor();
		}

		if (button.gettY() > 0) {

			button2 = buttons[button.gettX()][button.gettY() - 1];
			button2.switchColor();
		}

		if (button.gettY() < 4) {

			button2 = buttons[button.gettX()][button.gettY() + 1];
			button2.switchColor();

		}

	}

	/**
	 * Determines whether or not the player has won. If they win, a message is
	 * displayed.
	 */
	private void win() {
		int counter3 = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (buttons[i][j].getBackground().equals(Color.black)) {

					counter3++;
					if (counter3 == 25) {

						JOptionPane.showMessageDialog(null, "You Win! Please Exit the window and have a nice day!");

						// wasn't sure completely what to do here to make it unclickable

					}
				}
			}

		}

	}

	/**
	 * Randomizes the game board at the beginning of the game and after every press
	 * of the "Restart" button.
	 * 
	 * 
	 * Uses the switchColor method in LightsOutButton to change the color. Uses
	 * random numbers assigned to x and y to randomize the game board
	 */
	private void random() {

		for (int i = 0; i < 10; i++) {

			Random rando = new Random();
			int ranX = rando.nextInt(5);
			int ranY = rando.nextInt(5);

			LightButton button = buttons[ranX][ranY];

			button.switchColor();

			if (button.gettX() > 0) {
				button = buttons[button.gettX() - 1][button.gettY()];
				button.switchColor();

			}

			if (button.gettX() < 4) {
				button = buttons[button.gettX() + 1][button.gettY()];
				button.switchColor();
			}

			if (button.gettY() > 0) {

				button = buttons[button.gettX()][button.gettY() - 1];
				button.switchColor();
			}

			if (button.gettY() < 4) {

				button = buttons[button.gettX()][button.gettY() + 1];
				button.switchColor();

			}

		}
	}

	/**
	 * Displays a hint to solving the game dependent on the color layout of the
	 * bottom row. If no solution is available or if the bottom row isn't the only
	 * row left, displays a "Not solvable yet!" message instead
	 * 
	 * Determines if a button is lit up by its background color
	 * 
	 */
	public void hint() {

		int counter2 = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				if (buttons[i][j].getBackground().equals(Color.white)) {
					counter2++;

				}
			}
		}
		if (counter2 != 0) {
			JOptionPane.showMessageDialog(null,
					"Not solvable yet! Change all squares to black except the bottom row to receive a hint!");
		} else {
			// good
			if (buttons[4][0].getBackground().equals(Color.black) && buttons[4][1].getBackground().equals(Color.black)
					&& buttons[4][2].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, second button. Work your way back down the grid to win!");
			}
			// good
			else if (buttons[4][1].getBackground().equals(Color.black)
					&& buttons[4][3].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, first and fourth buttons. Work your way back down the grid to win!");
			}
			// good
			else if (buttons[4][0].getBackground().equals(Color.black)
					&& buttons[4][1].getBackground().equals(Color.black)
					&& buttons[4][3].getBackground().equals(Color.black)
					&& buttons[4][4].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, third button. Work your way back down the grid to win!");
			}
			// good
			else if (buttons[4][0].getBackground().equals(Color.black)
					&& buttons[4][2].getBackground().equals(Color.black)
					&& buttons[4][3].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, fifth button. Work your way back down the grid to win!");
			}
			// good
			else if (buttons[4][0].getBackground().equals(Color.black)
					&& buttons[4][4].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, first and second buttons. Work your way back down the grid to win!");
			}
			// good
			else if (buttons[4][1].getBackground().equals(Color.black)
					&& buttons[4][2].getBackground().equals(Color.black)
					&& buttons[4][4].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, first button. Work your way back down the grid to win!");
			}

			// good
			else if (buttons[4][2].getBackground().equals(Color.black)
					&& buttons[4][3].getBackground().equals(Color.black)
					&& buttons[4][4].getBackground().equals(Color.black)) {
				JOptionPane.showMessageDialog(null,
						"Click the top row, fourth button. Work your way back down the grid to win!");
			} else
				JOptionPane.showMessageDialog(null, "Not solvable! Keep going or press restart!");
		}

	}

	private static final long serialVersionUID = 1L;

	/**
	 * Main method that puts everything together.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame mainWindow = new JFrame("Memory Game");
		// Exit on close
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Some machines have trouble with background colors without this
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Now, make the game components
		LightsOut memoryPanel = new LightsOut();
		// Add our custom panel to the content pane
		mainWindow.setContentPane(memoryPanel);
		mainWindow.setPreferredSize(new Dimension(700, 400));
		mainWindow.pack();

		mainWindow.setVisible(true);

		JOptionPane.showMessageDialog(null,
				"Hello! Thank you for trying my game! Try to change all of the squares to black to win! Good luck!");

	}
}
