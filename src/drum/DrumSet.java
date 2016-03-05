package drum;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class DrumSet extends JPanel implements DrumView {

    private static final Collection<String> STANDARD_KITS;

    private final DrumPadRegistry drumPadRegistry;
    private final String imageFilePath;
    private final ActionHandler actionHandler;

    static {
        STANDARD_KITS = new HashSet<>(3);
        STANDARD_KITS.add("Acoustic");
        STANDARD_KITS.add("Electro");
        STANDARD_KITS.add("Kurzweil");
    }

    public DrumSet(String imageFile) {
        this(new DrumPadRegistry(), imageFile);
    }

    public DrumSet(DrumPadRegistry drumPadRegistry, String imageFilePath){
        this.drumPadRegistry = drumPadRegistry;
        this.imageFilePath = imageFilePath;
        this.actionHandler = new ActionHandler(drumPadRegistry);
    }

    @Override
    public void initializeView() {
        final int rows = 2;
        final int cols = 4;
        this.setLayout(buildGridLayout(rows, cols, 0, 0));

        int totalButtons = 8;
        for(int i = 0; i < totalButtons; i++){
            buildAndInitializeButton(i);
        }
    }

    private void buildAndInitializeButton(int buttonNumber) {
        initializeButtonValues(constructButtonWithImage(), buttonNumber);
    }

    private void initializeButtonValues(final JButton button,
                                        final int buttonNumber) {
        final int buttonWidth = 230;
        final int buttonHeight = 272;
        button.setSize(buttonWidth, buttonHeight);
        button.setBorder(getEmptyBorder());
        button.addActionListener(actionHandler);
        this.add(button);
        final String buttonName = "button"+ buttonNumber;
        button.setActionCommand(buttonName);
    }

    private JButton constructButtonWithImage() {
        BufferedImage buttonIcon;
        try {
            buttonIcon = ImageIO.read(new File(System.getProperty("user.dir") + imageFilePath)); //read image file to create icon for button
            return new JButton(new ImageIcon(buttonIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Border getEmptyBorder() {
        return BorderFactory.createEmptyBorder();
    }

    private GridLayout buildGridLayout(int rows, int columns, int hgap, int vgap) {
        return new GridLayout(rows, columns, hgap, vgap);
    }

    public void updateDrums(final String kitName){
        if (STANDARD_KITS.contains(kitName)) {
            loadStandardKit(kitName);
        }
        if("Vinyl".equals(kitName)){
            loadVinylKit();
        }
    }

    private void loadStandardKit(String kitName) {
        loadPadWithSoundClip("button0", "/audio/" + kitName + "/ClosedHat.wav");
        loadPadWithSoundClip("button1", "/audio/" + kitName + "/ClosedHat2.wav");
        loadPadWithSoundClip("button2", "/audio/" + kitName + "/OpenHat.wav");
        loadPadWithSoundClip("button3", "/audio/" + kitName + "/PedalHat.wav");
        loadPadWithSoundClip("button4", "/audio/" + kitName + "/Kick.wav");
        loadPadWithSoundClip("button5", "/audio/" + kitName + "/Snare.wav");
        loadPadWithSoundClip("button6", "/audio/" + kitName + "/Tom1.wav");
        loadPadWithSoundClip("button7", "/audio/" + kitName + "/Tom2.wav");
    }

    private void loadPadWithSoundClip(final String padName,
                                      final String filename) {
        drumPadRegistry.getPadOrDefault(padName, new Drum()).setSoundClipFileName(filename);
    }

    private void loadVinylKit() {
        loadPadWithSoundClip("button0", "/audio/Vinyl/ClosedHat.wav");
        loadPadWithSoundClip("button1", "/audio/Vinyl/ClosedHat2.wav");
        loadPadWithSoundClip("button2", "/audio/Vinyl/OpenHat.wav");
        loadPadWithSoundClip("button3", "/audio/Vinyl/Tambourine.wav");
        loadPadWithSoundClip("button4", "/audio/Vinyl/Kick.wav");
        loadPadWithSoundClip("button5", "/audio/Vinyl/Snare.wav");
        loadPadWithSoundClip("button6", "/audio/Vinyl/Tambourine.wav");
        loadPadWithSoundClip("button7", "/audio/Vinyl/Kick2.wav");
    }

    private static class ActionHandler implements ActionListener {

        private final DrumPadRegistry drumPadRegistry;

        public ActionHandler(DrumPadRegistry drumPadRegistry) {
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
}
