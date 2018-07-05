package com.larregle.gui;

import com.larregle.function.Mandelbrot;
import com.larregle.math.Complex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Raster {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    public void generateImage() throws Exception {
        final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Color color = Mandelbrot.getInstance().find(new Complex(mapToReal(col), mapToImaginary(row)));
                image.setRGB(col, row, color.getRGB());
            }
        }

        ImageIO.write(image, "png", new File("mandelbrot_fractal.png"));
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
