package com.larregle.function;

import com.larregle.math.Complex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Mandelbrot {

    private static final int MAX_ITERATIONS = 2000;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final Complex MIN_COMPLEX;
    public static final Complex MAX_COMPLEX;

    private static final Mandelbrot instance;


    static {
        instance = new Mandelbrot();
        MIN_COMPLEX = new Complex(-2.5, -1.0);
        MAX_COMPLEX = new Complex(1.0, 1.0);
    }

    private Mandelbrot() {}

    public static Mandelbrot getInstance() { return instance; }

    public void generateFractal() throws Exception {
        final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Color color = Mandelbrot.getInstance().find(new Complex(mapToReal(col), mapToImaginary(row)));
                image.setRGB(col, row, color.getRGB());
            }
        }

        ImageIO.write(image, "png", new File("mandelbrot_fractal.png"));
    }

    /**
     * Finds a color for a given complex number using the 'Escape time algorithm'
     * @param complex
     * @return the corresponding {@link Color} for a given {@link Complex} number.
     */
    private Color find(Complex complex) {
        int i = 0;
        Complex zc = new Complex();
        while (((zc.getX() * zc.getX()) + (zc.getY() * zc.getY())) < 4.0 && i < MAX_ITERATIONS) {
            double new_x = zc.getX() * zc.getX() - zc.getY() * zc.getY() + complex.getX();
            zc.setY(2.0 * zc.getX() * zc.getY() + complex.getY());
            zc.setX(new_x);
            i++;
        }
        return new Color(i % 256, i % 256, i % 256);
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
