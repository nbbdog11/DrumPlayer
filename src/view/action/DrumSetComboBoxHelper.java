package view.action;

import javax.swing.JComboBox;

public class DrumSetComboBoxHelper {

    public static String getSelectedSet(final JComboBox cb) {
        return (String) cb.getSelectedItem();
    }
}
