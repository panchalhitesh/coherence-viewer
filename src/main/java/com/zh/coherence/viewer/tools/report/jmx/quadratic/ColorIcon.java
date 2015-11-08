package com.zh.coherence.viewer.tools.report.jmx.quadratic;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ColorIcon extends ImageIcon {
    Color color;

    public ColorIcon(Color color) {
        this.color = color;

        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        int rgb = color.getRGB();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                img.setRGB(i, j, rgb);
            }
        }

        setImage(img);
    }
}
