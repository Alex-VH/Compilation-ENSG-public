/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamples.utils;

import fr.cyann.graphr.Graphr;
import fr.cyann.graphr.StyleBuilder;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author cyann
 */
public class UiUtils {

    public static Graphr BuildPanel() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        size.setSize(3 * width / 4, 3 * height / 4);

        JFrame frame = new JFrame("GofComposite");
        frame.setPreferredSize(size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StyleBuilder.Style style = new StyleBuilder().buildStyle();
        Graphr graph = new Graphr(style);

        frame.setContentPane(graph);

        frame.pack();
        frame.setVisible(true);
        
        return graph;
    }
    
    public static void sleep() throws InterruptedException {
            Thread.sleep(1000);
    }

}
