package Lights_Out;

import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import java.awt.Color;

/**
 * @author Andrew Fryzel CS 1410-001 A9 "Lights Out"
 *
 */
public class LightButton extends JButton {

	/**
	 * Instance variable for the LightButton and 2D arrays
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	public LightButton[][] buttonPress;

	/**
	 * LightButton constructor. Declares and initializes needed variables.
	 * 
	 * @param _x
	 *            the x value of the 2D array.
	 * @param _y
	 *            the y value of the 2D array.
	 */
	public LightButton(int _x, int _y) {
		super();
		x = _x;
		y = _y;

		setBackground(Color.white);
		setFont(new Font("Dialog", Font.PLAIN, 24));
		setFocusPainted(false);
	}

	/**
	 * Getter for the y value.
	 * 
	 * @return returns the x value
	 */
	public int gettX() {

		return x;
	}

	/**
	 * Getter for the y value
	 * 
	 * @return returns the y value
	 */

	public int gettY() {

		return y;
	}

	/**
	 * Sets the x value of the 2D array
	 * 
	 * @param x
	 *            the value to be set
	 */

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y value of the 2D array
	 * 
	 * @param y
	 *            the value to be set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Changes the color of the square on the grid to the opposite color (white to
	 * black; black to white)
	 * 
	 * 
	 */
	public void switchColor() {

		if (getBackground().equals(Color.black)) {
			setBackground(Color.white);

		} else {
			setBackground(Color.black);
		}
	}

}