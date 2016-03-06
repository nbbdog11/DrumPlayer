package view.action;

import drum.audio.BackTrack;
import drum.audio.BackTrackFacade;
import main.DrumPlayerConstants;
import drum.audio.BackTrackAudioHandler;
import view.ViewComponent;
import view.ViewComponentRegistry;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DrumControlActionHandler implements ActionListener {

    private final BackTrackFacade backTrackFacade;
    private final BackTrackAudioHandler backTrackAudioHandler;

    public DrumControlActionHandler(final BackTrackFacade backTrackFacade,
                                    final BackTrackAudioHandler backTrackAudioHandler) {
        this.backTrackFacade = backTrackFacade;
        this.backTrackAudioHandler = backTrackAudioHandler;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (event.getActionCommand().equals(DrumPlayerConstants.PLAY_BUTTON_TEXT)) {
            handlePlayCommand();
        }
    }

    private void handlePlayCommand() {
        final BackTrack track = backTrackFacade.getNext();
        ((JLabel) ViewComponentRegistry.getComponent(ViewComponent.TRACK_LABEL)).setText(track.getName());
        try {
            backTrackAudioHandler.setNewBackTrack(track);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
