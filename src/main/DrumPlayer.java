package main;

import drum.BorderLayoutLocation;
import drum.DrumSet;
import drum.DrumView;

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
        final DrumSet drumSet = new DrumSet(drumPadIconPath);
        final DrumControl drumControl = new DrumControl(drumSet);
        addViewToContainer(container, drumControl, BorderLayoutLocation.NORTH);
        addViewToContainer(container, drumSet, BorderLayoutLocation.CENTER);
        addViewToContainer(container, new Welcome(), BorderLayoutLocation.SOUTH);
    }

    private void addViewToContainer(Container c, DrumView view, BorderLayoutLocation borderLayout) {
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
