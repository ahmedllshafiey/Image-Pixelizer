package org.pixelizer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Pixelizer {

    public BufferedImage pixelate(BufferedImage image, int pixelSize) {
        BufferedImage pixelated = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = pixelated.createGraphics();

        for (int y = 0; y < image.getHeight(); y += pixelSize) {
            for (int x = 0; x < image.getWidth(); x += pixelSize) {
                int width = Math.min(pixelSize, image.getWidth() - x);
                int height = Math.min(pixelSize, image.getHeight() - y);

                int rgb = image.getRGB(x, y);
                g.setColor(new java.awt.Color(rgb));
                g.fillRect(x, y, width, height);
            }
        }

        g.dispose();
        return pixelated;
    }
}
