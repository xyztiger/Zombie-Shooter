package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Abstract class for all panels which have buttons
public abstract class ButtonPanel implements ActionListener {

    // EFFECTS: initializes the JPanel and adds the JLabel
    public void initPanel(JPanel panel, JLabel label) {
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
    }

    // EFFECTS: returns a button with given label and icon
    public JButton addButton(String label, ImageIcon icon) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBounds(0, 200, 80, 80);
        try {
            button.setIcon(fitToButton(icon));
        } catch (Exception e) {
            return button;
        }
        return button;
    }

    // EFFECTS: returns a button with given label
    public JButton addButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBounds(0, 200, 80, 80);
        return button;
    }

    // EFFECTS: fits the given icon to the size of the button
    public Icon fitToButton(ImageIcon icon) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

}
