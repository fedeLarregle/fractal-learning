package com.larregle.function.tree;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Tree {

    private static final Map<Class<? extends Tree>, Tree> instances;
    public static final int WIDTH = 2000;
    public static final int HEIGHT = 1000;

    static {
        instances = new HashMap<>();
    }

    protected Tree() {}

    public static <T extends Tree> T getInstance(Class<T> clazz) {
        T instance = clazz.cast(instances.get(clazz));
        if (instance == null) {
            try {
                instance = clazz.cast(clazz.getDeclaredConstructors()[0].newInstance());
            } catch (InstantiationException|IllegalAccessException|InvocationTargetException e) {
                e.printStackTrace();
            }
            instances.put(clazz, instance);
        }

        return instance;
    }

    public abstract void generateFractal(String fileType, String fileName) throws Exception;

    protected abstract void drawTree(Graphics2D graphics, float lineWidth, int x, int y, double angle, int depth, int powerX, int powerY);
}
