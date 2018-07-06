package com.larregle.function.tree;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class FractalTree extends Tree{

    protected FractalTree() { super(); }

    public void generateFractal(String fileType, String fileName) throws Exception {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        drawTree(graphics, 8,1000, 1000, -90, 9,10, 10);

        ImageIO.write(image, fileType, new File(fileName));
    }

    protected void drawTree(Graphics2D graphics, float lineWidth, int x, int y, double angle, int depth, int powerX, int powerY) {
        if (depth == 0) return;
        if (lineWidth <= 0) { lineWidth = 0.1F; }
        graphics.setStroke(new BasicStroke( lineWidth));
        int x2 = x + (int) (Math.cos(Math.toRadians(angle)) * depth * powerX);
        int y2 = y + (int) (Math.sin(Math.toRadians(angle)) * depth * powerY);

        graphics.setColor(Color.WHITE);
        graphics.drawLine(x, y, x2, y2);

        drawTree(graphics, lineWidth - 2, x2, y2, angle - 25, depth - 1, 10, 10);
        drawTree(graphics, lineWidth - 2, x2, y2, angle + 25, depth - 1,10, 10);

    }
}
