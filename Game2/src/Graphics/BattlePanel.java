package Graphics;

import javax.swing.*;

public class BattlePanel extends JPanel{

    public BattlePanel(){
        JLabel first = new JLabel(new ImageIcon("Images/aragorn.png"));
        add(first);
        first.setBounds(50, 5, 110, 80);
    }
}
