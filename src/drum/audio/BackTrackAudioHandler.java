package drum.audio;

import main.DrumPlayerConstants;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.Serializable;

public class BackTrackAudioHandler implements Serializable {

    private final AudioClipHelper audioClipHelper;
    private Clip clip;

    public BackTrackAudioHandler(final AudioClipHelper audioClipHelper) {
        this.audioClipHelper = audioClipHelper;
    }

    public void setNewBackTrack(final BackTrack track) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
        clip = prepareClipWithBackTrack(track);
        clip.loop(DrumPlayerConstants.BACK_TRACK_LOOPS);
    }

    private Clip prepareClipWithBackTrack(final BackTrack track) {
        return audioClipHelper.prepareClip(track.getFilePath());
    }
}