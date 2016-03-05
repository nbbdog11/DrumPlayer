package drum;

import drum.resource.AudioClipHelper;

public class Drum implements DrumInterface {

    private final AudioClipHelper audioClipHelper;
    private String soundClipFileName;
	
	public Drum(final AudioClipHelper audioClipHelper){
        this.audioClipHelper = audioClipHelper;
        this.soundClipFileName = "";
    }

	@Override
	public void setSoundClipFileName(final String soundClipFileName){
		this.soundClipFileName = soundClipFileName;
	}

    @Override
	public void playSound(){
        audioClipHelper.playClip(this.soundClipFileName);
	}


}
