package com.larregle.function;

import com.larregle.math.Complex;

import java.awt.*;

public class Mandelbrot {

    private static final int MAX_ITERATIONS = 2000;
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

    /**
     * Finds a color for a given complex number using the 'Escape time algorithm'
     * @param complex
     * @return the corresponding {@link Color} for a given {@link Complex} number.
     */
    public Color find(Complex complex) {
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

}
