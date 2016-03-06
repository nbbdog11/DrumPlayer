package drum.resource;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioClipHelper {

    private final ResourceFileLocator resourceFileLocator;

    public AudioClipHelper(ResourceFileLocator resourceFileLocator) {
        this.resourceFileLocator = resourceFileLocator;
    }

    public void playClip(final String soundClipFileName) {
        final Clip preparedClip = prepareClipWithGain(soundClipFileName, +5.0f);
        if (preparedClip != null) {
            preparedClip.start();
        }
    }

    public Clip prepareClipWithGain(final String soundClipFileName, float gainValue) {
        final Clip clip = prepareClip(soundClipFileName);
        if (clip != null) {
            setGainValue(clip, gainValue);
        }
        return clip;
    }

    public Clip prepareClip(final String soundClipFileName) {
        final Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(getAudioInputStream(soundClipFileName));
            return clip;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AudioInputStream getAudioInputStream(final String soundClipFileName) throws UnsupportedAudioFileException,
            IOException {
        return AudioSystem.getAudioInputStream(getAudioFile(soundClipFileName));
    }

    private File getAudioFile(final String soundClipFileName) {
        return resourceFileLocator.findFileFromRelativePath(soundClipFileName);
    }

    private void setGainValue(final Clip clip,
                              final float gainValue) {
        final FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(gainValue);
    }
}
