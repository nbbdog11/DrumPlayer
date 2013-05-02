package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

import drum.*;

public class DrumPlayer extends JFrame {
	
	public static Drum[][] drumArray = new Drum[4][2]; //4x2 array of drums
	String drumpadIcon = "resources/images/drumpad.jpg"; //filename for button image
	
	/**
	*
	*@Standard constructor for DrumPlayer
	*/
	public DrumPlayer(){
		super("DrumPlayer"); //add title to title bar
		//instantiate each drum object
		for(int i = 0; i<4; i++){
			for(int j = 0; j<2; j++){
				drumArray[i][j] = new Drum();
			}
		setBackground(Color.CYAN); //set background color
		}
		Container c = getContentPane();
		//add various panels in BorderLayout
		c.add(new DrumControl(), BorderLayout.NORTH);
		c.add(new DrumSet(drumpadIcon), BorderLayout.CENTER);
		c.add(new Welcome(), BorderLayout.SOUTH);
	}
	
	/**
	*
	*@Main method for DrumPlayer project
	*/
	public static void main(String[] args){
		//set up the GUI
		new Splash();
		final JFrame jFrame = new DrumPlayer();    
		jFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setSize(1024,768);
		jFrame.setVisible(true);
	}
}
