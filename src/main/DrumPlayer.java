package main;

import drum.audio.BackTrackAudioHandler;
import drum.kitloader.DrumKitLoader;
import drum.registry.DrumPadRegistry;
import drum.audio.AudioClipHelper;
import drum.audio.BackTrackFacade;
import drum.resource.ResourceFileLocator;
import view.*;
import view.action.DrumSetActionHandler;

import java.awt.*;

import javax.swing.*;

public class DrumPlayer extends JFrame {

    public DrumPlayer(){
		super(DrumPlayerConstants.WINDOW_TITLE);
    }

    private void buildViews(final Container container) {

        final ResourceFileLocator resourceFileLocator = new ResourceFileLocator();
        final DrumSet drumSet = buildDrumSet(resourceFileLocator, DrumPlayerConstants.DRUM_PAD_ICON_PATH);
        final DrumControl drumControl = buildDrumControl(drumSet, resourceFileLocator);
        addViewToContainer(container, drumControl, BorderLayoutLocation.NORTH);
        addViewToContainer(container, drumSet, BorderLayoutLocation.CENTER);
        addViewToContainer(container, new Welcome(), BorderLayoutLocation.SOUTH);
    }

    private DrumControl buildDrumControl(final DrumSet drumSet,
                                         final ResourceFileLocator resourceFileLocator) {
        final AudioClipHelper audioClipHelper = new AudioClipHelper(resourceFileLocator);
        return new DrumControl(drumSet,
                               new BackTrackFacade(),
                               new InterfaceComponentInitializer(),
                               new BackTrackAudioHandler(audioClipHelper));
    }

    private DrumSet buildDrumSet(final ResourceFileLocator resourceFileLocator,
                                 final String drumPadIconPath) {
        final DrumPadRegistry drumPadRegistry = new DrumPadRegistry();
        return new DrumSet(
                new DrumSetActionHandler(drumPadRegistry), new DrumKitLoader(drumPadRegistry, new AudioClipHelper(resourceFileLocator)), resourceFileLocator,
                           drumPadIconPath);
    }

    private void addViewToContainer(final Container c,
                                    final DrumView view,
                                    final BorderLayoutLocation borderLayout) {
        view.initializeView();
        c.add((Component) view, borderLayout.getBorderLayout());
    }

    public static void main(final String[] args){
        final DrumPlayer drumPlayer = new DrumPlayer();
        initializeDrumPlayer(drumPlayer);
	}

    private static void initializeDrumPlayer(final DrumPlayer drumPlayer) {
        drumPlayer.initializeFrame();
        drumPlayer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        drumPlayer.pack();
        drumPlayer.setSize(DrumPlayerConstants.WINDOW_WIDTH, DrumPlayerConstants.WINDOW_HEIGHT);
        drumPlayer.setVisible(true);
    }

    public void initializeFrame() {
        setBackground(Color.CYAN);
        buildViews(getContentPane());
    }
}
