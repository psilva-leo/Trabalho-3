package Graphics;

import Characters.Character;

import javax.swing.*;
import java.awt.*;

public class AttributesPanel extends JPanel {

    public AttributesPanel(Character character){
        setLayout(new GridLayout(3, 1, 5, 5));
        add(new JLabel("Strength      "+ character.getStrength()));
        add(new JLabel("Speed         "+ character.getSpeed()));
        add(new JLabel("Dexterity     "+ character.getDexterity()));
        add(new JLabel("Constitution  "+ character.getConstitution()));
    }
}
