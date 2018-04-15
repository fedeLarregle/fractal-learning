package com.larregle.gui;

import com.larregle.function.Mandelbrot;
import com.larregle.math.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Raster extends JPanel {

    private static final int WIDTH = 512;
    private static final int HEIGHT = 512;

    public Raster() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        final BufferedImage image = (BufferedImage) createImage(WIDTH, HEIGHT);
        WritableRaster writableRaster = image.getRaster();

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Color color = Mandelbrot.getInstance().find(new Complex(mapToReal(col), mapToImaginary(row)));
                writableRaster.setPixel(col, row, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
            }
        }

        g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
    }

    private double mapToReal(int x) {
        double range = Mandelbrot.MAX_COMPLEX.getX() - Mandelbrot.MIN_COMPLEX.getX();
        return x * (range / WIDTH) + Mandelbrot.MIN_COMPLEX.getX();
    }

    private double mapToImaginary(int y) {
        double range = Mandelbrot.MAX_COMPLEX.getY() - Mandelbrot.MIN_COMPLEX.getY();
        return y * (range / HEIGHT) + Mandelbrot.MIN_COMPLEX.getY();
    }
}
