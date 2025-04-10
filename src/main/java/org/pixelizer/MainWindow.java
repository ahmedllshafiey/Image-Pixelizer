package org.pixelizer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MainWindow extends JFrame {

    private JLabel originalLabel;
    private JLabel pixelatedLabel;
    private JSlider pixelSlider;
    private BufferedImage originalImage;
    private BufferedImage pixelatedImage;

    @SuppressWarnings("CallToPrintStackTrace")
    public MainWindow() {
        setTitle("Pixelizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels for images
        JPanel imagePanel = new JPanel(new GridLayout(1, 2));
        imagePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imagePanel.setBackground(java.awt.Color.LIGHT_GRAY);
        originalLabel = new JLabel("", JLabel.CENTER);
        pixelatedLabel = new JLabel("", JLabel.CENTER);
        imagePanel.add(originalLabel);
        imagePanel.add(pixelatedLabel);

        // Slider for pixel size
        pixelSlider = new JSlider(JSlider.HORIZONTAL, 2, 50, 10);
        pixelSlider.setMajorTickSpacing(8);
        pixelSlider.setMinorTickSpacing(2);
        pixelSlider.setPaintTicks(true);
        pixelSlider.setPaintLabels(true);

        // Convert Button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener((ActionEvent e) -> {
            if (originalImage != null) {
                int pixelSize = pixelSlider.getValue();
                Pixelizer pixelizer = new Pixelizer();
                pixelatedImage = pixelizer.pixelate(originalImage, pixelSize);
                Image scaled = pixelatedImage.getScaledInstance(pixelatedLabel.getWidth(), pixelatedLabel.getHeight(), Image.SCALE_SMOOTH);
                pixelatedLabel.setIcon(new ImageIcon(scaled));
            }
        });

        // Load image on start
        JButton loadButton = new JButton("Load Image");
        loadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    originalImage = ImageIO.read(file);
                    Image scaled = originalImage.getScaledInstance(originalLabel.getWidth(), originalLabel.getHeight(), Image.SCALE_SMOOTH);
                    originalLabel.setIcon(new ImageIcon(scaled));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to load image.");
                }
            }
        });

        // Controls Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(loadButton);
        buttonsPanel.add(convertButton);

        controlPanel.add(new JLabel("  Pixel Size:"), BorderLayout.NORTH);
        controlPanel.add(pixelSlider, BorderLayout.CENTER);
        controlPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Assemble frame
        add(imagePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setVisible(true);
    }
}
