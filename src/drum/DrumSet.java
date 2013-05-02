package drum;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.DrumPlayer;

public class DrumSet extends JPanel {
	
	BufferedImage buttonIcon; //used to make button into an image
	JButton button;
	ActionHandler action = new ActionHandler();
	
	/**
	*
	*@Standard constructor for DrumSet class
	*/
	public DrumSet(String imageFile){
		try {
			this.buttonIcon = ImageIO.read(new File(imageFile)); //read image file to create icon for button
		} catch (IOException e) {
			e.printStackTrace();
		}
		GridLayout layout = new GridLayout(2,4,0,0); //set grid layout for 4x2 configuration of pads
		this.setLayout(layout);
		
		//adds the buttons to the panel, and adds listeners
		for(int i = 0; i < 8; i++){
			button = new JButton(new ImageIcon(buttonIcon));
			button.setSize(230,272);
			button.setBorder(BorderFactory.createEmptyBorder());
			this.add(button);
			button.addActionListener(action);
			String buttonName = "button"+i;
			button.setActionCommand(buttonName);
		}
	}
	
	/**
	*
	*@Update file path for each drum; dependent on dropdown list
	*/
	public static void updateDrums(String setName){
		//sets sound clips based on drumset selected from dropdown
		if(setName == "Acoustic"){
			DrumPlayer.drumArray[0][0].setSoundClip("resources/audio/Acoustic/ClosedHat.wav");
			DrumPlayer.drumArray[1][0].setSoundClip("resources/audio/Acoustic/ClosedHat2.wav");
			DrumPlayer.drumArray[2][0].setSoundClip("resources/audio/Acoustic/OpenHat.wav");
			DrumPlayer.drumArray[3][0].setSoundClip("resources/audio/Acoustic/PedalHat.wav");
			DrumPlayer.drumArray[0][1].setSoundClip("resources/audio/Acoustic/Kick.wav");
			DrumPlayer.drumArray[1][1].setSoundClip("resources/audio/Acoustic/Snare.wav");
			DrumPlayer.drumArray[2][1].setSoundClip("resources/audio/Acoustic/Tom1.wav");
			DrumPlayer.drumArray[3][1].setSoundClip("resources/audio/Acoustic/Tom2.wav");
		}
		if(setName == "Electro"){
			DrumPlayer.drumArray[0][0].setSoundClip("resources/audio/Electro/ClosedHat.wav");
			DrumPlayer.drumArray[1][0].setSoundClip("resources/audio/Electro/ClosedHat2.wav");
			DrumPlayer.drumArray[2][0].setSoundClip("resources/audio/Electro/OpenHat.wav");
			DrumPlayer.drumArray[3][0].setSoundClip("resources/audio/Electro/Ride.wav");
			DrumPlayer.drumArray[0][1].setSoundClip("resources/audio/Electro/Kick.wav");
			DrumPlayer.drumArray[1][1].setSoundClip("resources/audio/Electro/Snare.wav");
			DrumPlayer.drumArray[2][1].setSoundClip("resources/audio/Electro/Tom1.wav");
			DrumPlayer.drumArray[3][1].setSoundClip("resources/audio/Electro/Tom2.wav");
		}
		if(setName == "Kurzweil"){
			DrumPlayer.drumArray[0][0].setSoundClip("resources/audio/Kurzweil/ClosedHat.wav");
			DrumPlayer.drumArray[1][0].setSoundClip("resources/audio/Kurzweil/OpenHat.wav");
			DrumPlayer.drumArray[2][0].setSoundClip("resources/audio/Kurzweil/Ride.wav");
			DrumPlayer.drumArray[3][0].setSoundClip("resources/audio/Kurzweil/Crash.wav");
			DrumPlayer.drumArray[0][1].setSoundClip("resources/audio/Kurzweil/Kick.wav");
			DrumPlayer.drumArray[1][1].setSoundClip("resources/audio/Kurzweil/Snare.wav");
			DrumPlayer.drumArray[2][1].setSoundClip("resources/audio/Kurzweil/Tom1.wav");
			DrumPlayer.drumArray[3][1].setSoundClip("resources/audio/Kurzweil/Tom2.wav");
		}
		if(setName == "Vinyl"){
			DrumPlayer.drumArray[0][0].setSoundClip("resources/audio/Vinyl/ClosedHat.wav");
			DrumPlayer.drumArray[1][0].setSoundClip("resources/audio/Vinyl/ClosedHat2.wav");
			DrumPlayer.drumArray[2][0].setSoundClip("resources/audio/Vinyl/OpenHat.wav");
			DrumPlayer.drumArray[3][0].setSoundClip("resources/audio/Vinyl/Tambourine.wav");
			DrumPlayer.drumArray[0][1].setSoundClip("resources/audio/Vinyl/Kick.wav");
			DrumPlayer.drumArray[1][1].setSoundClip("resources/audio/Vinyl/Snare.wav");
			DrumPlayer.drumArray[2][1].setSoundClip("resources/audio/Vinyl/Tambourine.wav");
			DrumPlayer.drumArray[3][1].setSoundClip("resources/audio/Vinyl/Kick2.wav");
		}
		
	}
	
	/**
	*
	*@ActionHandler for drum pad buttons. Plays sound clip on press
	*/	
	class ActionHandler implements ActionListener
	{			   
		//play sounds whenever a pad is clicked
	 	public void actionPerformed(ActionEvent e) 
	    {
	 		String command = ((JButton)e.getSource()).getActionCommand();
		    if(command.equals("button0"))
		    	DrumPlayer.drumArray[0][0].playSound();
		    if(command.equals("button1"))
		    	DrumPlayer.drumArray[1][0].playSound();
		    if(command.equals("button2"))
		    	DrumPlayer.drumArray[2][0].playSound();
		    if(command.equals("button3"))
		    	DrumPlayer.drumArray[3][0].playSound();
		    if(command.equals("button4"))
		    	DrumPlayer.drumArray[0][1].playSound();
		    if(command.equals("button5"))
		    	DrumPlayer.drumArray[1][1].playSound();
		    if(command.equals("button6"))
		    	DrumPlayer.drumArray[2][1].playSound();
		    if(command.equals("button7"))
		    	DrumPlayer.drumArray[3][1].playSound();	
	    } // close actionperformed
	} //close ActionHandler
}
