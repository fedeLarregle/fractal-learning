package com.larregle;

import com.larregle.function.Mandelbrot;
import com.larregle.function.tree.FractalTree;
import com.larregle.function.tree.PineTree;

public class App {
    public static void main(String[] args) throws Exception{
        Mandelbrot.getInstance().generateFractal();
        FractalTree.getInstance(FractalTree.class).generateFractal("png", "fractal-tree.png");
        PineTree.getInstance(PineTree.class).generateFractal("png", "pine-fractal-tree.png");
    }
}
