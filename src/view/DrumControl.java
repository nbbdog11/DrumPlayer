package view;

import drum.registry.DrumSets;
import drum.resource.AudioClipHelper;
import drum.resource.BackTrack;
import drum.resource.BackTrackFacade;
import view.action.DrumControlComboBoxHandler;
import view.action.DrumSetComboBoxHelper;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class DrumControl extends Container implements DrumView {

    static JComboBox<String> setSelector = buildDrumSetsSelector(); //create combobox

    static JLabel trackLabel = new JLabel();

    private final DrumSet drumSet;
    private final BackTrackFacade backTrackFacade;
    private final InterfaceComponentInitializer componentInitializer;
    private final AudioClipHelper audioClipHelper;

    private final String playButtonText = "Play Next Backing Track";
    Clip clip;

    private static String getDefaultDrumKit() {
        setSelector.setSelectedIndex(0);
        return DrumSetComboBoxHelper.getSelectedSet(setSelector);
    }

    public DrumControl(final DrumSet drumSet,
                       final BackTrackFacade backTrackFacade,
                       final InterfaceComponentInitializer componentInitializer,
                       final AudioClipHelper audioClipHelper){
		this.drumSet = drumSet;
        this.backTrackFacade = backTrackFacade;
        this.componentInitializer = componentInitializer;
        this.audioClipHelper = audioClipHelper;
    }

	@Override
	public void initializeView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //set boxlayout to center items
		loadDefaultDrumKit(); //loads the first drumset
        final JButton playButton = new JButton(playButtonText);
        playButton.addActionListener(new ActionHandler()); //adds listener to play button
        setUpSetSelector();

        final Collection<JComponent> components = Arrays.asList(trackLabel, playButton, setSelector);
        componentInitializer.setUpInterfaceComponents(components, this);

//		clip = prepareClipWithBackTrack(backTrackFacade.getNext());
	}

    private void setUpSetSelector() {
        setSelector.addItemListener(new DrumControlComboBoxHandler(drumSet)); //adds listener for combobox
        setSelector.setMaximumSize(setSelector.getPreferredSize());
    }

    private void loadDefaultDrumKit() {
        drumSet.updateDrums(getDefaultDrumKit());
    }

    private static JComboBox<String> buildDrumSetsSelector() {
        final Collection<String> drumSetNames = DrumSets.drumSetNames();
        return new JComboBox<>(drumSetNames.toArray(new String[drumSetNames.size()]));
    }

    private class ActionHandler implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals(playButtonText)){
                handlePlayCommand();
            }
		}

        private void handlePlayCommand() {
            final BackTrack track = backTrackFacade.getNext();
            trackLabel.setText(track.getName());
            try {
                setNewBackTrack(track);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private void setNewBackTrack(BackTrack track) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip != null) {
            if(clip.isRunning()) {
                clip.stop();
            }
        }
        clip = prepareClipWithBackTrack(track);
        clip.loop(5);
    }

    private Clip prepareClipWithBackTrack(BackTrack track) {
        return audioClipHelper.prepareClip(track.getFilePath());
    }

}
