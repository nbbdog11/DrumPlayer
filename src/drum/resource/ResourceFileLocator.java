package drum.resource;

import java.io.File;

public class ResourceFileLocator {

    public File findFileFromRelativePath(final String relativeResourcePath) {
        return new File(System.getProperty("user.dir") + "/" + relativeResourcePath);
    }
}
