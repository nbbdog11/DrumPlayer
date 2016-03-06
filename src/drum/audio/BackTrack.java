package drum.audio;

public enum BackTrack {

    GET_LUCKY("Get Lucky - Daft Punk ft. Pharrell Williams",
              "/audio/Tracks/getlucky.wav"),
    THRIFT_SHOP("Thrift Shop - Macklemore ft. Ryan Lewis",
                "/audio/Tracks/thriftshop.wav"),
    LEVELS("Levels (Skrillex Remix) - Avicii ",
           "/audio/Tracks/levels.wav");

    private final String name;
    private final String filePath;

    BackTrack(final String name,
              final String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }
}
