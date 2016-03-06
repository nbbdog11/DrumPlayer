package main;

import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;
import drum.resource.BackTrackFacade;
import drum.resource.ResourceFileLocator;
import view.*;

import java.awt.*;

import javax.swing.*;

public class DrumPlayer extends JFrame {

    public DrumPlayer(){
		super("DrumPlayer");
    }

    public void initializeFrame() {
        setBackground(Color.CYAN);
        buildViews(getContentPane());
    }

    private void buildViews(final Container container) {
        final String drumPadIconPath = "/images/drumpad.jpg";
        final ResourceFileLocator resourceFileLocator = new ResourceFileLocator();
        final DrumSet drumSet = buildDrumSet(resourceFileLocator, drumPadIconPath);
        final DrumControl drumControl = new DrumControl(drumSet, new BackTrackFacade(), new InterfaceComponentInitializer(), new AudioClipHelper(resourceFileLocator));
        addViewToContainer(container, drumControl, BorderLayoutLocation.NORTH);
        addViewToContainer(container, drumSet, BorderLayoutLocation.CENTER);
        addViewToContainer(container, new Welcome(), BorderLayoutLocation.SOUTH);
    }

    private DrumSet buildDrumSet(final ResourceFileLocator resourceFileLocator,
                                 final String drumPadIconPath) {
        return new DrumSet(new DrumPadRegistry(),
                resourceFileLocator,
                           drumPadIconPath);
    }

    private void addViewToContainer(final Container c,
                                    final DrumView view,
                                    final BorderLayoutLocation borderLayout) {
        view.initializeView();
        c.add((Component)view, borderLayout.getBorderLayout());
    }

    public static void main(String[] args){
        final DrumPlayer drumPlayer = new DrumPlayer();
        initializeDrumPlayer(drumPlayer);
	}

    private static void initializeDrumPlayer(DrumPlayer drumPlayer) {
        drumPlayer.initializeFrame();
        drumPlayer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        drumPlayer.pack();
        drumPlayer.setSize(1024, 768);
        drumPlayer.setVisible(true);
    }
}
