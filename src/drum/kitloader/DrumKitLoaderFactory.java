package drum.kitloader;

import drum.registry.DrumPadRegistry;
import drum.registry.DrumSets;
import drum.resource.AudioClipHelper;

public class DrumKitLoaderFactory {


    public DrumKitLoader get(final String kitName,
                             final DrumPadRegistry drumPadRegistry,
                             final AudioClipHelper audioClipHelper) {
        return DrumSets.lookupByName(kitName).isStandard() ?
                getStandardDrumKitLoader(kitName, drumPadRegistry, audioClipHelper) :
                getVinylDrumKitLoader(drumPadRegistry, audioClipHelper);
    }

    private DrumKitLoader getVinylDrumKitLoader(DrumPadRegistry drumPadRegistry, AudioClipHelper audioClipHelper) {
        return new VinylDrumKitLoader(drumPadRegistry, audioClipHelper);
    }

    private DrumKitLoader getStandardDrumKitLoader(String kitName, DrumPadRegistry drumPadRegistry, AudioClipHelper audioClipHelper) {
        return new StandardDrumKitLoader(drumPadRegistry, audioClipHelper, kitName);
    }
}
