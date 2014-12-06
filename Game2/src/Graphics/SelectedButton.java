package Graphics;

import Characters.*;
import Characters.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectedButton extends JButton implements MouseListener{

    private Character character;
    private AttributesPanel attributes;

    public SelectedButton(ImageIcon image, Character character){
        setIcon(image);
        this.character = character;
    }

    public void mousePressed(MouseEvent e){ }

    public void mouseReleased(MouseEvent e){ }

    public void mouseEntered(MouseEvent e){
        JOptionPane.showMessageDialog(null, "Character selected.");
        attributes = new AttributesPanel(character);
        attributes.setBounds(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 200, 200);
        attributes.setVisible(true);
    }

    public void mouseExited(MouseEvent e) {
        attributes.setVisible(false);
    }

    public void mouseClicked(MouseEvent e) {

    }
}
