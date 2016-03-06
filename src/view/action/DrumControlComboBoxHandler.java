package view.action;

import view.DrumSet;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DrumControlComboBoxHandler implements ItemListener {

    private final DrumSet drumSet;

    public DrumControlComboBoxHandler(final DrumSet drumSet) {
        this.drumSet = drumSet;
    }

    @Override
    public void itemStateChanged(final ItemEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            drumSet.updateDrums(DrumSetComboBoxHelper.getSelectedSet(cb));
        }
    }
}
