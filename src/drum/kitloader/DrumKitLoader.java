package drum.kitloader;

import drum.Drum;
import drum.DrumInterface;
import drum.registry.DrumPadRegistry;
import drum.audio.AudioClipHelper;
import main.DrumPlayerConstants;

import java.io.Serializable;

public class DrumKitLoader implements Serializable {

    private final DrumPadRegistry drumPadRegistry;
    private final AudioClipHelper audioClipHelper;

    public DrumKitLoader(final DrumPadRegistry drumPadRegistry,
                         final AudioClipHelper audioClipHelper) {
        this.drumPadRegistry = drumPadRegistry;
        this.audioClipHelper = audioClipHelper;
    }

    public void loadKit(final String kitName) {
        for (int i = 0; i < DrumPlayerConstants.NUMBER_OF_PADS; i++) {
            loadPadWithSoundClip(getPadName(i), getFilename(kitName, i));
        }
    }

    private String getPadName(final int i) {
        return "button" + i;
    }

    private String getFilename(final String kitName,
                               final int i) {
        return "/audio/" + kitName + "/Pad" + i + ".wav";
    }

    private void loadPadWithSoundClip(final String padName,
                                      final String filename) {
        drumPadRegistry.getPadOrDefault(padName, new Drum(audioClipHelper, filename));
    }
}