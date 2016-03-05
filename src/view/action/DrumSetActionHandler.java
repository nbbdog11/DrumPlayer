package view.action;

import drum.registry.DrumPadRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrumSetActionHandler implements ActionListener {

    private final DrumPadRegistry drumPadRegistry;

    public DrumSetActionHandler(final DrumPadRegistry drumPadRegistry) {
        this.drumPadRegistry = drumPadRegistry;
    }

    public void actionPerformed(final ActionEvent evt) {
        final String command = ((JButton) evt.getSource()).getActionCommand();
        handleCommand(command);
    }

    private void handleCommand(final String padName) {
        drumPadRegistry.getPad(padName).playSound();
    }
}
