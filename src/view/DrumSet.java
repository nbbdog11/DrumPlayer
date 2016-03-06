package view;

import drum.kitloader.DrumKitLoader;
import drum.kitloader.DrumKitLoaderFactory;
import drum.registry.DrumPadRegistry;
import drum.resource.AudioClipHelper;
import drum.resource.ResourceFileLocator;
import view.action.DrumSetActionHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrumSet extends JPanel implements DrumView {

    private final DrumPadRegistry drumPadRegistry;
    private final DrumKitLoaderFactory drumKitLoaderFactory;
    private final ResourceFileLocator resourceFileLocator;
    private final String imageFilePath;

    private final DrumSetActionHandler actionHandler;

    public DrumSet(final DrumPadRegistry drumPadRegistry,
                   final DrumKitLoaderFactory drumKitLoaderFactory,
                   final ResourceFileLocator resourceFileLocator,
                   final String imageFilePath){
        this.drumPadRegistry = drumPadRegistry;
        this.drumKitLoaderFactory = drumKitLoaderFactory;
        this.resourceFileLocator = resourceFileLocator;
        this.imageFilePath = imageFilePath;
        this.actionHandler = new DrumSetActionHandler(drumPadRegistry);
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
            buttonIcon = ImageIO.read(resourceFileLocator.findFileFromRelativePath(imageFilePath)); //read image file to create icon for button
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
        DrumKitLoader drumKitLoader = drumKitLoaderFactory.get(kitName, drumPadRegistry, new AudioClipHelper(resourceFileLocator));
        drumKitLoader.loadKit();
    }

}
