package Graphics;

import Game.InGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SelectCharPanel extends JPanel{

    private final InGame game;
    private Image bgImage;
    private ArrayList<String> imageNameArray;
    private SelectedCharPanel selectedPanel;
    private static Integer counter = 0;
    private ArrayList<Integer> used;

    public SelectCharPanel(SelectedCharPanel selected, InGame pgame){
        this.selectedPanel = selected;
        this.game = pgame;
        used = new ArrayList<Integer>();

        imageNameArray = new ArrayList<String>();
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-select.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");
        imageNameArray.add("Images/aragorn-selected.png");


        setLayout(new GridLayout(2, 4,10,10));
        for(Integer i=0; i<8; i++) {

            final ImageIcon image = new ImageIcon(imageNameArray.get(i));
            final SelectedButton tmpBtm = new SelectedButton(image,game.getChar(i));
            tmpBtm.setName(i.toString());
            tmpBtm.setMargin(new Insets(0, 0, 0, 0));
            tmpBtm.setIcon(image);
            tmpBtm.setBorder(null);
            tmpBtm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(counter < 3) {
                        if(!used.contains(Integer.valueOf(tmpBtm.getName()))) {
                            tmpBtm.setIcon(new ImageIcon(imageNameArray.get(Integer.valueOf(tmpBtm.getName())+8)));
                            revalidate();

                            used.add(Integer.valueOf(tmpBtm.getName()));
                            //JOptionPane.showMessageDialog(null, "Character selected.");
                            ImageIcon tmpImg = new ImageIcon(imageNameArray.get(Integer.valueOf(tmpBtm.getName())));
                            selectedPanel.remove(2 - counter);
                            selectedPanel.add(new JLabel(tmpImg));
                            selectedPanel.repaint();
                            selectedPanel.revalidate();
                            counter++;
                            game.addChar(Integer.valueOf(tmpBtm.getName()));

                            if(counter == 3){
                                game.chooseCPUChar();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e1) { }
                                setVisible(false);
                                selectedPanel.setVisible(false);
                                BattlePanel battlePanel = new BattlePanel();
                                battlePanel.setVisible(true);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Character has already been selected.");
                        }
                    }
                }
            });
            this.add(tmpBtm);
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,this);
    }
}
