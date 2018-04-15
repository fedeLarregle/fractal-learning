package com.larregle;

import com.larregle.gui.Raster;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {

        EventQueue.invokeLater(()-> {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setDefaultLookAndFeelDecorated(true);
                f.setResizable(false);
                Raster rt = new Raster();
                f.add(rt, BorderLayout.CENTER);
                f.pack();
                f.setVisible(true);
        });
    }
}
