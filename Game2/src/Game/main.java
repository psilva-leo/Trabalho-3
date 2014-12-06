package Game;

import Characters.Knight;
import Game.InGame;
import Game.PlayerThread;
import Graphics.*;


public class main {
    public static void main(String args[]) throws InterruptedException {

        /*
        InGame game = new InGame();

        game.start();
        game.setupTeams();
        game.setupItems();
        game.battle();
        game.stop();
        */

        InGame game = new InGame();
        game.start();
        MainFrame frame = new MainFrame(game);


        Knight knight = new Knight("Leo", 10);
        Knight knight2 = new Knight("Leo", 10);
        PlayerThread t1 = new PlayerThread(knight);
        PlayerThread t2 = new PlayerThread(knight2);
        int i=0;
        int client = 0;
        t1.start();
        t2.start();
        while (i<100){
            System.out.println("Round: " + i + " game: " + t1.getGameRound() + " thread: " + t1.getRound());
            if(i == t1.getRound() || i == t2.getRound()){
                i++;
                t1.setGameRound(i);
                t2.setGameRound(i);
            }
            Thread.sleep(1000);
        }


    }
}
