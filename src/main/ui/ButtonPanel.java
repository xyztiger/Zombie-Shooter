package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ButtonPanel implements ActionListener {

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

    public Icon fitToButton(ImageIcon icon) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

}
