package com.zh.coherence.viewer.tools.report.jmx.quadratic;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HeaderPanel extends JPanel {
    private JLabel titleLabel;

    public HeaderPanel(String title) {
        titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);

        add(titleLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = getBackground();
        Color color2 = color1.darker();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
