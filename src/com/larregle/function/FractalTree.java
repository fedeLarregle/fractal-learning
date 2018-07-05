package com.larregle.function;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class FractalTree {

    private static final FractalTree instance;
    public static final int WIDTH = 768;
    public static final int HEIGHT = 920;

    static {
        instance = new FractalTree();
    }

    private FractalTree() {}

    public static FractalTree getInstance() { return instance; }

    public void generateFractal() throws Exception {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        drawTree(graphics, 400, 500, -90, 9);

        ImageIO.write(image, "png", new File("fractal-tree.png"));
    }

    private void drawTree(Graphics2D graphics, int x, int y, double angle, int depth) {
        if (depth == 0) return;

        int x2 = x + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        int y2 = y + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);

        graphics.setColor(Color.WHITE);
        graphics.drawLine(x, y, x2, y2);

        drawTree(graphics, x2, y2, angle - 20, depth - 1);
        drawTree(graphics, x2, y2, angle + 20, depth - 1);
    }
}
