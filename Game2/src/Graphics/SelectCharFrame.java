package Graphics;

import Game.InGame;

import javax.swing.*;
import java.awt.*;

public class SelectCharFrame extends JFrame{
        private SelectedCharPanel selectedPanel;
        private SelectCharPanel selectPanel;
        private BattlePanel battlePanel;


    public SelectCharFrame(InGame game){
        selectedPanel = new SelectedCharPanel();
        selectPanel = new SelectCharPanel(selectedPanel, game);
        BattlePanel battlePanel = new BattlePanel();

        setTitle("Game");
        setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());


        GridBagConstraints selectRestriction = new GridBagConstraints();
        selectRestriction.insets = new Insets(20,10,10,10);
        selectRestriction.gridy = 1;
        selectRestriction.gridx = 0;
        selectRestriction.weightx = 140;
        selectRestriction.weighty = 1;
        ImageIcon tmp = new ImageIcon("Images/blanck-select.png");
        selectedPanel.add(new JLabel(tmp));
        selectedPanel.add(new JLabel(tmp));
        selectedPanel.add(new JLabel(tmp));
        contentPane.add(selectedPanel, selectRestriction);


        selectRestriction.insets = new Insets(20,10,10,10);
        selectRestriction.weightx = 140;
        selectRestriction.weighty = 1;
        selectRestriction.gridy = 0;
        selectRestriction.gridx = 0;
        contentPane.add(selectPanel, selectRestriction);

        selectRestriction.insets = new Insets(20,10,10,10);
        selectRestriction.weightx = 140;
        selectRestriction.weighty = 1;
        selectRestriction.gridy = 0;
        selectRestriction.gridx = 0;
        contentPane.add(battlePanel, selectRestriction);


        setVisible(true);


    }
}
