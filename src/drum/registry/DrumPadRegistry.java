package drum.registry;

import drum.DrumInterface;
import drum.NullDrum;

import java.util.HashMap;
import java.util.Map;

public class DrumPadRegistry {

    private static final Map<String, DrumInterface> DRUM_MAP = new HashMap<>();

    public DrumInterface getPadOrDefault(final String name,
                                         final DrumInterface defaultValue) {
        return DRUM_MAP.computeIfAbsent(name, (key) -> defaultValue);
    }

    public DrumInterface getPad(final String name) {
        return DRUM_MAP.computeIfAbsent(name, (key) -> new NullDrum());
    }
}
