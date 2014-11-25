import Characters.Knight;
import Items.ManaPotion;
import Items.Weapon;

public class main {
    public static void main(String args[]){

        InGame game = new InGame();

        game.start();
        game.setupTeams();
        game.setupItems();
        game.battle();


        game.stop();


    }
}
