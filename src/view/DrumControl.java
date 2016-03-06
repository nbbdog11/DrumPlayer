package view;

import drum.audio.BackTrackAudioHandler;
import drum.registry.DrumSets;
import drum.audio.BackTrackFacade;
import main.DrumPlayerConstants;
import view.action.DrumControlActionHandler;
import view.action.DrumControlComboBoxHandler;
import view.action.DrumSetComboBoxHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

public class DrumControl extends Container implements DrumView {

    private final DrumSet drumSet;
    private final BackTrackFacade backTrackFacade;
    private final InterfaceComponentInitializer componentInitializer;
    private final BackTrackAudioHandler backTrackAudioHandler;

    public DrumControl(final DrumSet drumSet,
                       final BackTrackFacade backTrackFacade,
                       final InterfaceComponentInitializer componentInitializer,
                       final BackTrackAudioHandler backTrackAudioHandler){
		this.drumSet = drumSet;
        this.backTrackFacade = backTrackFacade;
        this.componentInitializer = componentInitializer;
        this.backTrackAudioHandler = backTrackAudioHandler;
    }

	@Override
	public void initializeView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JComboBox<String> setSelector = buildDrumSetsSelector();
        loadDefaultDrumKit(setSelector);

        final JButton playButton = new JButton(DrumPlayerConstants.PLAY_BUTTON_TEXT);
        playButton.addActionListener(new DrumControlActionHandler(backTrackFacade, backTrackAudioHandler));

        final JLabel trackLabel = new JLabel();
        ViewComponentRegistry.registerComponent(ViewComponent.TRACK_LABEL, trackLabel);

        final Collection<JComponent> components = Arrays.asList(trackLabel, playButton, setSelector);
        setUpInterfaceComponents(components);
    }

    private JComboBox<String> buildDrumSetsSelector() {
        final Collection<String> drumSetNames = DrumSets.drumSetNames();
        final JComboBox<String> setSelector = new JComboBox<>(drumSetNames.toArray(new String[drumSetNames.size()]));
        return setUpSetSelector(setSelector);
    }

    private void loadDefaultDrumKit(final JComboBox<String> setSelector) {
        drumSet.updateDrums(getDefaultDrumKit(setSelector));
    }

    private String getDefaultDrumKit(final JComboBox<String> setSelector) {
        setSelector.setSelectedIndex(0);
        return DrumSetComboBoxHelper.getSelectedSet(setSelector);
    }

    private JComboBox<String> setUpSetSelector(final JComboBox<String> setSelector) {
        setSelector.addItemListener(new DrumControlComboBoxHandler(drumSet));
        setSelector.setMaximumSize(setSelector.getPreferredSize());
        return setSelector;
    }

    private void setUpInterfaceComponents(final Collection<JComponent> components) {
        componentInitializer.setUpInterfaceComponents(components, this);
    }
}
