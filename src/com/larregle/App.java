package com.larregle;

import com.larregle.function.FractalTree;
import com.larregle.function.Mandelbrot;

public class App {
    public static void main(String[] args) throws Exception{
        Mandelbrot.getInstance().generateFractal();
        FractalTree.getInstance().generateFractal();
    }
}
