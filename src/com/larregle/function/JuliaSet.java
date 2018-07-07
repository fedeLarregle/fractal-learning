package com.larregle.function;

import com.larregle.math.Complex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class JuliaSet {

    private static final JuliaSet instance;
    public static final int MAX_ITERATIONS = 300;
    public static final int WIDTH = 2920;
    public static final int HEIGHT = 2080;
    private int zoom;
    private int moveX;
    private int moveY;
    public static final Complex COMPLEX;

    static {
        instance = new JuliaSet();
        COMPLEX = new Complex(-0.7, -0.27015);
    }

    private JuliaSet() {
        zoom = 5;
        moveX = 0;
        moveY = 0;
    }

    public static JuliaSet getInstance() { return instance; }

    public void generateFractal() throws Exception {
        final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Color color = find(new Complex(mapToReal(col), mapToImaginary(row)));
                image.setRGB(col, row, color.getRGB());
            }
        }

        ImageIO.write(image, "png", new File("julia_set_fractal.png"));
    }

    /**
     * Finds a color for a given complex number using the 'Escape time algorithm'
     * @param complex
     * @return the corresponding {@link Color} for a given {@link Complex} number.
     */
    private Color find(Complex complex) {
        int i = 0;
        while ( ((complex.getX() * complex.getX()) + (complex.getY() * complex.getY())) < 4.0 && i < MAX_ITERATIONS ) {
            double new_x = complex.getX() * complex.getX() - complex.getY() * complex.getY() + COMPLEX.getX();
            complex.setY( 2.0 * complex.getX() * complex.getY() + COMPLEX.getY() );
            complex.setX(new_x);
            i++;
        }

        return new Color(i % 256, i % 256, i % 256);
    }

    private double mapToReal(int x) {
        return 1.5 * (x - WIDTH / 2) / (0.5 * zoom * WIDTH) + moveX;
    }

    private double mapToImaginary(int y) {
        return (y - HEIGHT / 2) / (0.5 * zoom * HEIGHT) + moveY;
    }
}
