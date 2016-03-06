package drum.kitloader;

import drum.Drum;
import drum.DrumInterface;
import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;

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
        for (int i = 0; i < 8; i++) {
            loadPadWithSoundClip("button" + i, "/audio/" + kitName + "/Pad" + i + ".wav");
        }
    }

    private void loadPadWithSoundClip(final String padName,
                                      final String filename) {
        final DrumInterface drumPad = drumPadRegistry.getPadOrDefault(padName, new Drum(audioClipHelper));
        drumPad.setSoundClipFileName(filename);
    }
}