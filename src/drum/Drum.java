package drum;

import drum.audio.AudioClipHelper;
import main.DrumPlayerConstants;

public class Drum implements DrumInterface {

    private final AudioClipHelper audioClipHelper;
    private final String soundClipFileName;
	
	public Drum(final AudioClipHelper audioClipHelper,
                final String soundClipFileName){
        this.audioClipHelper = audioClipHelper;
        this.soundClipFileName = soundClipFileName;
    }

    @Override
	public void playSound() {
        audioClipHelper.playClip(this.soundClipFileName, DrumPlayerConstants.PAD_GAIN_VALUE);
    }
}
