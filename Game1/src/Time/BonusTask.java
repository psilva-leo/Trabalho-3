package Time;

import Characters.Character;

import java.util.ArrayList;
import java.util.TimerTask;

public class BonusTask extends TimerTask{

    private ArrayList<Character> characters;

    public BonusTask(ArrayList<Character> characters){
        this.characters = characters;
    }
    public void run(){
        for(Character chara : characters){
            chara.addBonus(10);
        }
    }

}
