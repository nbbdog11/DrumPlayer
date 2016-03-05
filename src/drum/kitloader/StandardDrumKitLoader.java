package drum.kitloader;

import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;

import java.io.Serializable;

public class StandardDrumKitLoader extends DrumKitLoader implements Serializable {

    private String kitName;

    public StandardDrumKitLoader(final DrumPadRegistry drumPadRegistry,
                                 final AudioClipHelper audioClipHelper,
                                 final String kitName) {
        super(drumPadRegistry, audioClipHelper);
        this.kitName = kitName;
    }

    @Override
    public void loadKit() {
        loadPadWithSoundClip("button0", "/audio/" + this.kitName + "/ClosedHat.wav");
        loadPadWithSoundClip("button1", "/audio/" + this.kitName + "/ClosedHat2.wav");
        loadPadWithSoundClip("button2", "/audio/" + this.kitName + "/OpenHat.wav");
        loadPadWithSoundClip("button3", "/audio/" + this.kitName + "/PedalHat.wav");
        loadPadWithSoundClip("button4", "/audio/" + this.kitName + "/Kick.wav");
        loadPadWithSoundClip("button5", "/audio/" + this.kitName + "/Snare.wav");
        loadPadWithSoundClip("button6", "/audio/" + this.kitName + "/Tom1.wav");
        loadPadWithSoundClip("button7", "/audio/" + this.kitName + "/Tom2.wav");
    }
}