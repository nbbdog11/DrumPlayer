package drum.kitloader;

import drum.Drum;
import drum.DrumInterface;
import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;

public abstract class DrumKitLoader {

    private final DrumPadRegistry drumPadRegistry;
    private final AudioClipHelper audioClipHelper;

    public DrumKitLoader(final DrumPadRegistry drumPadRegistry,
                         final AudioClipHelper audioClipHelper) {
        this.drumPadRegistry = drumPadRegistry;
        this.audioClipHelper = audioClipHelper;
    }

    public abstract void loadKit();

    public void loadPadWithSoundClip(final String padName,
                                     final String filename) {
        final DrumInterface drumPad = drumPadRegistry.getPadOrDefault(padName, new Drum(audioClipHelper));
        drumPad.setSoundClipFileName(filename);
    }
}
