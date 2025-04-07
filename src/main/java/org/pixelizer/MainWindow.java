package org.pixelizer;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public MainWindow() {
        // Set the title of the window
        setTitle("Pixelizer");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a Button
        JButton button = new JButton("Click Me!");

        // Add the button to the window
        add(button);

        // Pack the window to fit its contents
        pack();
        setVisible(true);
    }
}
