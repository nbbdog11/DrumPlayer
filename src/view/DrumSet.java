package view;

import drum.kitloader.DrumKitLoader;
import drum.resource.ResourceFileLocator;
import main.DrumPlayerConstants;
import view.action.DrumSetActionHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrumSet extends JPanel implements DrumView {

    private final DrumSetActionHandler actionHandler;
    private final DrumKitLoader drumKitLoader;
    private final ResourceFileLocator resourceFileLocator;
    private final String imageFilePath;

    public DrumSet(final DrumSetActionHandler actionHandler,
                   final DrumKitLoader drumKitLoader,
                   final ResourceFileLocator resourceFileLocator,
                   final String imageFilePath){
        this.actionHandler = actionHandler;
        this.drumKitLoader = drumKitLoader;
        this.resourceFileLocator = resourceFileLocator;
        this.imageFilePath = imageFilePath;
    }

    @Override
    public void initializeView() {
        this.setLayout(buildGridLayout(DrumPlayerConstants.PAD_ROW_COUNT, DrumPlayerConstants.PAD_COLUMN_COUNT, 0, 0));

        for(int i = 0; i < DrumPlayerConstants.NUMBER_OF_PADS; i++){
            buildAndInitializeButton(i);
        }
    }

    private void buildAndInitializeButton(int buttonNumber) {
        initializeButtonValues(constructButtonWithImage(), buttonNumber);
    }

    private void initializeButtonValues(final JButton button,
                                        final int buttonNumber) {
        button.setSize(DrumPlayerConstants.PAD_WIDTH, DrumPlayerConstants.PAD_HEIGHT);
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
        drumKitLoader.loadKit(kitName);
    }
}
