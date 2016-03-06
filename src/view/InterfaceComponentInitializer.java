package view;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Collection;

public class InterfaceComponentInitializer implements Serializable {

    public void setUpInterfaceComponents(final Collection<JComponent> components,
                                         final Container container) {
        centerAlignComponents(components);
        addComponentsToPanel(components, container);
    }

    private void centerAlignComponents(final Collection<JComponent> components) {
        components.forEach(this::centerAlignComponent);
    }

    private void centerAlignComponent(final JComponent component) {
        component.setAlignmentX(Container.CENTER_ALIGNMENT);
    }

    private void addComponentsToPanel(final Collection<JComponent> components,
                                      final Container container) {
        components.forEach(c -> container.add(c, container));
    }
}