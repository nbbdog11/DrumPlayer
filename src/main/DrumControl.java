package main;

import drum.*;

import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class DrumControl extends JPanel {
	
	static String[] drumSets = {"Acoustic","Electro","Kurzweil","Vinyl"}; //array of drumset options for combobox
	static JComboBox setSelector = new JComboBox(drumSets); //create combobox
	JButton playButton = new JButton("Play Backing Track"); //create button to play backing tracks
	//declare handlers for button and combobox
	ActionHandler action = new ActionHandler();
	ComboHandler combo = new ComboHandler();
	String[] backTracks = {};
	static String selectedItem = (String) setSelector.getItemAt(0); //intialize selectedItem so first set is loaded
	
	/**
	*
	*@Standard constructor for DrumControl
	*/
	public DrumControl(){
		DrumSet.updateDrums(selectedItem); //loads the first drumset
		playButton.addActionListener(action); //adds listener to play button
		setSelector.addItemListener(combo); //adds listener for combobox
		//add components to panel
		this.add(playButton);
		this.add(setSelector);		
	}
	
	/**
	*
	*@Listener to play back beat
	*/
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Unimplemented as of yet
			if(e.getActionCommand().equals("Play Backing Track")){
				Random rand = new Random();
				int index = rand.nextInt(3);
				//track = backTracks[index];
			}
			
		}
	}
	
	/**
	*
	*@Listener for combo box to change drumsets
	*/
	class ComboHandler implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			//If the combobox value changes loaded the selected drumset
			JComboBox cb = (JComboBox) e.getSource();
			if(e.getStateChange() == ItemEvent.SELECTED){
				DrumSet.updateDrums((String) cb.getSelectedItem());
			}
			
		}
	
	}
}
