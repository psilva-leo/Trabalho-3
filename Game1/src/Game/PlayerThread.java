package Game;

import Characters.Character;
import Items.HealthPotion;
import Items.ManaPotion;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PlayerThread extends Thread{
    private int id;
    private static int count = 0;
    private Character player;
    private Team charTeam;
    private Team enemyTeam;
    private int clientPlayer;
    private int gameRound;
    private int round;

    PlayerThread(Character player, Team charTeam, Team enemyTeam){
        this.charTeam = charTeam;
        this.enemyTeam = enemyTeam;
        id = count;
        count++;
        this.player = player;
        round = 0;
        gameRound = 1;
        clientPlayer = 0;
    }

    public void run(){
        while (player.getHP() > 0) {
            if(gameRound > 1)
                System.out.println(gameRound + "   " +round);
            if(gameRound > round) {
                if (id == clientPlayer) {
                    chooseAction();
                } else {
                    if (player.getNumberOfPotionItems() > 0 && player.getHP() < 50) {
                        useItem();
                    } else {
                        Random r = new Random();
                        int attacked = (int) (10 * r.nextDouble() % 3);
                        System.out.println(player.getName() + " attacked " + enemyTeam.getChar(attacked).getName());
                        player.attackCharacter(enemyTeam.getChar(attacked), player.chooseSkillCPU());
                    }
                }
                round++;
            }
        }
    }

    public int getClientPlayer(){ return clientPlayer; }

    public int getID(){
        return id;
    }

    public String getPlayerName() { return player.getName(); }

    public int getPlayerHP() { return player.getHP(); }

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

    public void chooseAction(){
        int choose = 0;
        int inputControl;
        Scanner in = new Scanner(System.in);
        Random r = new Random();
        int cpuPlayer = (int) (10 * r.nextDouble() % 3);

        System.out.println("[1] Attack    [2] Item    [3] Change Character");
        do {
            inputControl = 0;
            try {
                System.out.print("choose: ");
                choose = in.nextInt();
            } catch (InputMismatchException e){
                inputControl = 1;
                in.next();
            }
            if(choose == 2 && player.getNumberOfPotionItems() < 0){
                System.out.println("Inventory is empty.");
                inputControl = 1;
            }

        }while(inputControl == 1 || !(choose > 0 && choose <= 3));
        switch (choose){
            case 1:
                System.out.println(player.getName() + " attacked " + enemyTeam.getChar(cpuPlayer).getName());
                player.attackCharacter(enemyTeam.getChar(cpuPlayer), player.chooseSkill());
                break;
            case 2:
                useItem();
                break;
            case 3:
                changePlayer();
                break;
        }

    }

    public void changePlayer(){
        int i=0;
        int choose=0;
        int inputControl;
        Scanner in = new Scanner(System.in);

        if(clientPlayer == id){
            for (i = 0; i < charTeam.getPlayerCount(); i++) {
                if(charTeam.getChar(i).getHP() > 0) {
                    System.out.println("[" + (i + 1) + "]" + charTeam.getChar(i).getName());
                }
            }
            do {
                inputControl = 0;
                try {
                    System.out.print("choose: ");
                    choose = in.nextInt();
                } catch (InputMismatchException e) {
                    inputControl = 1;
                    in.next();
                }
                try {
                    if (charTeam.getChar(choose - 1).getHP() == 0) { // cannot change to a dead player
                        inputControl = 1;
                    }
                }catch (IndexOutOfBoundsException e){}
            } while (inputControl == 1 || !(choose > 0 && choose <= charTeam.getPlayerCount()));

            clientPlayer = choose - 1;
            System.out.println(charTeam.getChar(choose-1).getName() + " selected!");
        }

    }

    public void useItem(){
        String itemName = "";
        int inputControl;
        Scanner in = new Scanner(System.in);

        if(player.getNumberOfPotionItems() > 0) {
            if (clientPlayer == id) {
                player.listPotionItems();
                do {
                    inputControl = 0;
                    try {
                        System.out.print("choose: ");
                        itemName = in.nextLine();
                    } catch (InputMismatchException e) {
                        inputControl = 1;
                        in.next();
                    }
                } while (inputControl == 1 || !(player.equipItem(itemName)));
            } else {
                for(int i=0; i < player.getNumberOfItems(); i++){
                    itemName = player.searchItem(i).getName();
                    if(player.searchItem(i).getClass().equals(HealthPotion.class) || player.searchItem(i).getClass().equals(ManaPotion.class)){
                        player.equipItem(itemName);
                        System.out.println(this.getName() + " used " + itemName);
                        break;
                    }
                }
            }
        }
    }





}

