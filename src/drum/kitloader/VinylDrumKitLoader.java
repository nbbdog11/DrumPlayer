package drum.kitloader;

import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;

import java.io.Serializable;

public class VinylDrumKitLoader extends DrumKitLoader implements Serializable {

    public VinylDrumKitLoader(final DrumPadRegistry drumPadRegistry,
                              final AudioClipHelper audioClipHelper) {
        super(drumPadRegistry, audioClipHelper);
    }

    @Override
    public void loadKit() {
        loadPadWithSoundClip("button0", "/audio/Vinyl/ClosedHat.wav");
        loadPadWithSoundClip("button1", "/audio/Vinyl/ClosedHat2.wav");
        loadPadWithSoundClip("button2", "/audio/Vinyl/OpenHat.wav");
        loadPadWithSoundClip("button3", "/audio/Vinyl/Tambourine.wav");
        loadPadWithSoundClip("button4", "/audio/Vinyl/Kick.wav");
        loadPadWithSoundClip("button5", "/audio/Vinyl/Snare.wav");
        loadPadWithSoundClip("button6", "/audio/Vinyl/Tambourine.wav");
        loadPadWithSoundClip("button7", "/audio/Vinyl/Kick2.wav");
    }
}