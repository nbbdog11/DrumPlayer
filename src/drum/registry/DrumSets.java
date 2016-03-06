package drum.registry;

import java.util.ArrayList;
import java.util.Collection;

public enum DrumSets {

    ACOUSTIC("Acoustic"),
    ELECTRO("Electro"),
    KURZWEIL("Kurzweil"),
    VINYL("Vinyl");

    private final String name;

    DrumSets(final String name) {
        this.name = name;
    }

    private static final Collection<String> DRUM_SET_NAMES;

    static {
        final DrumSets[] values = DrumSets.values();
        DRUM_SET_NAMES = new ArrayList<>(values.length);
        for (final DrumSets value : values) {
            DRUM_SET_NAMES.add(value.name);
        }
    }

    public static Collection<String> drumSetNames() {
        return DRUM_SET_NAMES;
    }

}
