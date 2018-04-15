package com.larregle.function;

import com.larregle.math.Complex;

import java.awt.*;

public class Mandelbrot {

    private static final int MAX_ITERATIONS = 500;

    private static final Mandelbrot instance;

    static {
        instance = new Mandelbrot();
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
        while (i < MAX_ITERATIONS && ((zc.getX() * zc.getX()) + (zc.getY() * zc.getY())) < 4.0) {
            zc.setX(zc.getX() * zc.getX());
            zc.setY(zc.getY() * zc.getY());
            i++;
        }
        return new Color(i % 256, i % 256, i % 256);
    }

}
