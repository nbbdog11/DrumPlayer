package main;

import drum.*;

import java.awt.Container;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class DrumControl extends Container implements DrumView {
	
	//arrays for track files and track names
	static String[] backTracks = {"/audio/Tracks/getlucky.wav","/audio/Tracks/thriftshop.wav","/audio/Tracks/levels.wav"};
	static String[] trackNames = {"Get Lucky - Daft Punk ft. Pharrell Williams","Thrift Shop - Macklemore ft. Ryan Lewis","Levels (Skrillex Remix) - Avicii "};
	static int count = 0;
	static String track = backTracks[0];
	static String[] drumSets = {"Acoustic","Electro","Kurzweil","Vinyl"}; //array of drumset options for combobox
	static JComboBox setSelector = new JComboBox(drumSets); //create combobox
	static JLabel trackLabel = new JLabel();
	private final DrumSet drumSet;
	JButton playButton = new JButton("Play Next Backing Track"); //create button to play backing tracks
	Clip clip;
	
	//declare handlers for button and combobox
	ActionHandler action = new ActionHandler();
	final ComboBoxHandler combo;
	
	static String selectedItem = (String) setSelector.getItemAt(0); //intialize selectedItem so first set is loaded

    public DrumControl(final DrumSet drumSet){
		this.drumSet = drumSet;
		this.combo = new ComboBoxHandler(drumSet);
	}

	@Override
	public void initializeView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //set boxlayout to center items
		drumSet.updateDrums(selectedItem); //loads the first drumset
		playButton.addActionListener(action); //adds listener to play button
		setSelector.addItemListener(combo); //adds listener for combobox

		//Center the elements and constrain combobox size
		trackLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
		playButton.setAlignmentX(Container.CENTER_ALIGNMENT);
		setSelector.setAlignmentX(Container.CENTER_ALIGNMENT);
		setSelector.setMaximumSize(setSelector.getPreferredSize());

		//add components to panel
		this.add(trackLabel, this);
		this.add(playButton, this);
		this.add(setSelector, this);
		AudioInputStream audio; //create an AudioInputStream for sound playback
		File file = new File(System.getProperty("user.dir") + track); //instantiate File object from sound file
		try {
			audio = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip(); //create audio Clip
			clip.open(audio); //open the AudioInputStream
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("Play Next Backing Track")){
				track = backTracks[count];
				trackLabel.setText(trackNames[count]);
				if(count < 2)
					count++;
				else
					count = 0;
				AudioInputStream audio; //create an AudioInputStream for sound playback
				File file = new File(System.getProperty("user.dir") + track); //instantiate File object from sound file
				try {
					if(clip.isRunning()){ //stop previous clip if it is still running
						clip.stop();
					}
					audio = AudioSystem.getAudioInputStream(file);
					clip = AudioSystem.getClip(); //create audio Clip
			        clip.open(audio); //open the AudioInputStream
			        clip.loop(5); //play the sound clip	
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
            }
		}
	}

	private class ComboBoxHandler implements ItemListener{
		private final DrumSet drumSet;

		public ComboBoxHandler(final DrumSet drumSet) {
			this.drumSet = drumSet;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			if(e.getStateChange() == ItemEvent.SELECTED){
				drumSet.updateDrums((String) cb.getSelectedItem());
			}
		}
	}
}
