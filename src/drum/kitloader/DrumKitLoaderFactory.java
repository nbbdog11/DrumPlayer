package drum.kitloader;

import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;

import java.util.Collection;
import java.util.HashSet;

public class DrumKitLoaderFactory {
    public static final Collection<String> STANDARD_KITS;

    static {
        STANDARD_KITS = new HashSet<>(3);
        STANDARD_KITS.add("Acoustic");
        STANDARD_KITS.add("Electro");
        STANDARD_KITS.add("Kurzweil");
    }

    public DrumKitLoader get(final String kitName,
                             final DrumPadRegistry drumPadRegistry,
                             final AudioClipHelper audioClipHelper) {
        return STANDARD_KITS.contains(kitName) ?
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
