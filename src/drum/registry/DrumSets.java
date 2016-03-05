package drum.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public enum DrumSets {

    ACOUSTIC("Acoustic", true),
    ELECTRO("Electro", true),
    KURZWEIL("Kurzweil", true),
    VINYL("Vinyl", false);

    private final String name;
    private final boolean isStandard;

    DrumSets(final String name,
             final boolean isStandard) {
        this.name = name;
        this.isStandard = isStandard;
    }

    private static final Map<String, DrumSets> DRUM_SETS_MAP;
    private static final Collection<String> DRUM_SET_NAMES;

    static {
        final DrumSets[] values = DrumSets.values();
        DRUM_SETS_MAP = new HashMap<>(values.length);
        DRUM_SET_NAMES = new ArrayList<>(values.length);
        for (final DrumSets value : values) {
            DRUM_SETS_MAP.put(value.name, value);
            DRUM_SET_NAMES.add(value.name);
        }
    }
    public static DrumSets lookupByName(final String name) {
        return DRUM_SETS_MAP.get(name);
    }

    public static Collection<String> drumSetNames() {
        return DRUM_SET_NAMES;
    }

    public boolean isStandard() {
        return isStandard;
    }
}
