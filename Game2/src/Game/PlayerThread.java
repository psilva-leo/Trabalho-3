package Game;

import Characters.Character;

public class PlayerThread extends Thread{
    private int id;
    private static int count = 0;
    private Character player;
    private int clientPlayer;
    private int gameRound;
    private int round;

    PlayerThread(Character player){
        id = count;
        count++;
        this.player = player;
        round = 0;
        gameRound = 0;
        clientPlayer = 0;
    }

    public void run(){
        int i = 0;
        while (i <100) {
            i++;
            if(gameRound > round) {
                System.out.println(">>" + id);
                if(id == clientPlayer){
                    player.chooseSkill();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                round++;
            }
        }
    }

    public int getID(){
        return id;
    }

    public int getRound(){
        return round;
    }

    public int getGameRound(){
        return gameRound;
    }

    public void setGameRound(int round){
        this.gameRound = round;
    }

    public void setClientPlayer(int clientPlayer){
        this.clientPlayer = clientPlayer;
    }
}

