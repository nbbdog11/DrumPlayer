package drum;

import java.io.*;

import javax.sound.sampled.*;

public class Drum implements DrumInterface {
	
	private String soundClipFileName;
	
	public Drum(){
		this.soundClipFileName = "";
	}

	@Override
	public void setSoundClipFileName(final String filename){
		soundClipFileName = System.getProperty("user.dir") + filename;
	}

    @Override
	public void playSound(){
        try {
            prepareClip(AudioSystem.getClip()).start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

    private Clip prepareClip(final Clip clip) throws LineUnavailableException,
                                                     IOException,
                                                     UnsupportedAudioFileException {
        clip.open(getAudioInputStream());
        setGainValue(clip, +5.0f);
        return clip;
    }

    private AudioInputStream getAudioInputStream() throws UnsupportedAudioFileException,
                                                          IOException {
        return AudioSystem.getAudioInputStream(getAudioFile());
    }

    private File getAudioFile() {
        return new File(this.soundClipFileName);
    }

    private void setGainValue(final Clip clip,
                              final float gainValue) {
        final FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(gainValue);
    }
}
