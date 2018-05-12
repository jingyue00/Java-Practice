package edu.nyu.cs9053.homework3;

import java.awt.image.BufferedImage;

/**
 * User: blangel
 */
public class ImageInfoProvider {

    private final BufferedImage image;

    public ImageInfoProvider(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException();
        }
        this.image = image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

}
