package drum.audio;

import java.util.Arrays;

public class BackTrackFacade {

    private final BackTrack[] backTracks;
    private int count = 0;

    public BackTrackFacade() {
        this.backTracks = initializeBackTracks();
    }

    private BackTrack[] initializeBackTracks() {
        final BackTrack[] values = BackTrack.values();
        return Arrays.copyOf(values, values.length);
    }

    public BackTrack getNext() {
        final BackTrack backTrack = backTracks[count % backTracks.length];
        count++;
        return backTrack;
    }
}
