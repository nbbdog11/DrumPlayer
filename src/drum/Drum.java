package drum;

import java.io.*;

import javax.sound.sampled.*;

public class Drum {
	
	private String soundclip; //contains the filename of the soundclip for the drum
	
	public Drum(){
		this.soundclip = null;
	}
	/**
	*
	*@set the sound clip to play on each pad
	*/
	protected void setSoundClip(String filename){
			soundclip = filename;
	}
	
	/**
	*
	*@play the sound clip of the drum
	*/
	protected void playSound(){
		AudioInputStream audio; //create an AudioInputStream for sound playback
		try {
			File file = new File(soundclip); //instantiate File object from sound file
			audio = AudioSystem.getAudioInputStream(file); //generate AudioInputStream from File object
			Clip clip = AudioSystem.getClip(); //create audio Clip
	        clip.open(audio); //open the AudioInputStream
	        FloatControl gainControl = 
	        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue(+5.0f); // Increase volume by 5 decibels.
	        clip.start(); //play the sound clip
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
