package Time;

import Characters.Character;

import java.util.ArrayList;
import java.util.TimerTask;

public class ManaTask extends TimerTask{

    private ArrayList<Character> characters;

    public ManaTask(ArrayList<Character> characters){
        this.characters = characters;
    }

    public void run(){
        for(Character chara : characters){
            chara.addMP(10);
        }
    }
}
