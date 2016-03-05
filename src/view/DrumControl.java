package view;

import drum.registry.DrumSets;
import drum.resource.ResourceFileLocator;
import view.action.DrumControlComboBoxHandler;
import view.action.DrumSetComboBoxHelper;

import java.awt.Container;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
    static JComboBox setSelector = new JComboBox(DrumSets.drumSetNames().toArray(new String[]{})); //create combobox
	static JLabel trackLabel = new JLabel();
	private final DrumSet drumSet;
    private final ResourceFileLocator resourceFileLocator;
    Clip clip;
	
	//declare handlers for button and combobox
	ActionHandler action = new ActionHandler();
	final DrumControlComboBoxHandler combo;
	
	static String selectedItem = getDefaultDrumKit(); //intialize selectedItem so first set is loaded

    private static String getDefaultDrumKit() {
        setSelector.setSelectedIndex(0);
        return DrumSetComboBoxHelper.getSelectedSet(setSelector);
    }

    public DrumControl(final DrumSet drumSet,
                       final ResourceFileLocator resourceFileLocator){
		this.drumSet = drumSet;
        this.resourceFileLocator = resourceFileLocator;
        this.combo = new DrumControlComboBoxHandler(drumSet);
    }

	@Override
	public void initializeView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //set boxlayout to center items
		drumSet.updateDrums(selectedItem); //loads the first drumset
        final JButton playButton = new JButton("Play Next Backing Track");
        playButton.addActionListener(action); //adds listener to play button
		setSelector.addItemListener(combo); //adds listener for combobox

		//Center the elements and constrain combobox size
        centerAlignComponent(trackLabel);
        centerAlignComponent(playButton);
		centerAlignComponent(setSelector);
		setSelector.setMaximumSize(setSelector.getPreferredSize());

		//add components to panel
		this.add(trackLabel, this);
		this.add(playButton, this);
		this.add(setSelector, this);
		AudioInputStream audio; //create an AudioInputStream for sound playback
		File file = getTrackFile(track);
		try {
			audio = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip(); //create audio Clip
			clip.open(audio); //open the AudioInputStream
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

    public void centerAlignComponent(JComponent component) {
        component.setAlignmentX(Container.CENTER_ALIGNMENT);
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
				File file = getTrackFile(track); //instantiate File object from sound file
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

    public File getTrackFile(String track) {
        return resourceFileLocator.findFileFromRelativePath(track);
    }

}
