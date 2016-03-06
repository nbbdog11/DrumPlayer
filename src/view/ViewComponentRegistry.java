package view;

import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;

public class ViewComponentRegistry {

    private static final Map<ViewComponent, JComponent> COMPONENT_MAP;

    static {
        COMPONENT_MAP = new HashMap<>();
    }

    public static JComponent registerComponent(final ViewComponent key,
                                               final JComponent value) {
        return COMPONENT_MAP.put(key, value);
    }

    public static JComponent getComponent(final ViewComponent key) {
        return COMPONENT_MAP.get(key);
    }
}
