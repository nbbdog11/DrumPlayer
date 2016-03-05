package drum;

import java.awt.BorderLayout;

public enum BorderLayoutLocation {

    NORTH(BorderLayout.NORTH),
    CENTER(BorderLayout.CENTER),
    SOUTH(BorderLayout.SOUTH);

    private final String borderLayout;

    BorderLayoutLocation(final String borderLayout) {

        this.borderLayout = borderLayout;
    }

    public String getBorderLayout() {
        return borderLayout;
    }
}
