package Game;

import Characters.Character;
import Characters.Knight;
import Characters.Thief;
import Characters.Wizard;
import Items.*;
import Time.BonusTask;
import Time.ManaTask;

import java.util.*;

public class InGame {

    private ArrayList<Character> charList;
    private ArrayList<Item> itemsList;
    private Team cpuTeam;
    private Team clientTeam;
    private int cpuPlayer;
    private int clientPlayer;
    private Timer bonusThread;
    private Timer manaThread;


    public InGame(){
        charList = new ArrayList<Character>();
        itemsList = new ArrayList<Item>();
        cpuTeam = new Team("CPU Team",Color.red);
        clientTeam = new Team("Blue Team", Color.blue);
        cpuPlayer = 0;
        clientPlayer = 0;
        bonusThread = new Timer();
        manaThread = new Timer();
    }

    public void start(){

        Knight knight1 = new Knight("Ike",6);
        knight1.setStrength(35);
        knight1.setSpeed(10);
        knight1.setDexterity(15);
        knight1.setConstitution(23);
        charList.add(knight1);
        Knight knight2 = new Knight("Aragorn",7);
        knight2.setStrength(30);
        knight2.setSpeed(20);
        knight2.setDexterity(13);
        knight2.setConstitution(16);
        charList.add(knight2);
        Wizard wizard1 = new Wizard("Aidyn",5);
        wizard1.setStrength(23);
        wizard1.setSpeed(14);
        wizard1.setDexterity(12);
        wizard1.setConstitution(16);
        charList.add(wizard1);
        Thief thief1 = new Thief("Garret",5);
        thief1.setStrength(25);
        thief1.setSpeed(20);
        thief1.setDexterity(18);
        thief1.setConstitution(14);
        charList.add(thief1);
        Knight knight3 = new Knight("Ganondorf",8);
        knight3.setStrength(37);
        knight3.setSpeed(9);
        knight3.setDexterity(14);
        knight3.setConstitution(18);
        charList.add(knight3);
        Wizard wizard2 = new Wizard("Yosin",4);
        wizard2.setStrength(23);
        wizard2.setSpeed(30);
        wizard2.setDexterity(15);
        wizard2.setConstitution(24);
        charList.add(wizard2);
        Wizard wizard3 = new Wizard("Pit",5);
        wizard3.setStrength(20);
        wizard3.setSpeed(25);
        wizard3.setDexterity(18);
        wizard3.setConstitution(21);
        charList.add(wizard3);
        Thief thief2 = new Thief("Robin",6);
        thief2.setStrength(26);
        thief2.setSpeed(30);
        thief2.setDexterity(17);
        thief2.setConstitution(25);
        charList.add(thief2);

        // Mana thread and Bonus thread
        BonusTask bonusTask = new BonusTask(charList);
        ManaTask manaTask = new ManaTask(charList);
        bonusThread.scheduleAtFixedRate(bonusTask, 0, 10000);
        manaThread.scheduleAtFixedRate(manaTask, 0, 5000);
    }

    public void setupTeams(){
        int i=1;
        int choose=0;
        String teamName = "";
        int inputControl;
        int control=0;

        // Client Team setup
        System.out.println("###################\n#Choose your team!#\n###################\n");
        System.out.println("###################\n#You have to choose 3 players!#\n###################\n");

        System.out.println("Choose your Team name!");
            Scanner in = new Scanner(System.in);
            do {
                inputControl = 0;
                try {
                    System.out.print("Name: ");
                    teamName = in.nextLine();
                } catch (InputMismatchException e){
                    inputControl = 1;
                    in.next();
                }
            }while(inputControl == 1);
            clientTeam.setName(teamName);


        for(Character charac : charList){
            System.out.println("[" + i + "]  " + charac.getName());
            System.out.println("Speed: " + charac.getSpeed());
            System.out.println("Constitution: " + charac.getConstitution());
            System.out.println("Strength: " + charac.getStrength());
            System.out.println("Dexterity: " + charac.getDexterity());
            System.out.println();
            i++;
        }

        i=1;
        do {
            System.out.println(i + "º Character: ");
            do {
                inputControl = 0;
                try {
                    System.out.print("choose: ");
                    choose = in.nextInt();
                } catch (InputMismatchException e){
                    inputControl = 1;
                    in.next();
                }
            }while(inputControl == 1);

            control = 0;
            if(choose > 0 && choose <= charList.size()) {
                for(int j=0; j< clientTeam.getPlayerCount(); j++){
                    if(clientTeam.getChar(j).getName().equals(charList.get(choose-1).getName())) control = 1; // ja esta no time;
                }
                if(control != 1) {
                    clientTeam.addChar(charList.get(choose - 1));
                    System.out.println(charList.get(choose - 1).getName() + " added to your team!");
                    i++;
                }else{
                    System.out.println("\n" + charList.get(choose - 1).getName() + " is already on your team. Choose another character.\n");
                }
            }
        }while(i<=3);

        // CPU Team setup
        Random r = new Random();
        int extension;
        int c;
        control=0;
        do {
            c = 1;
            extension = (int) (10 * r.nextDouble() % charList.size());
            for (int j = 0; j < cpuTeam.getPlayerCount(); j++) { // cannot have double character
                if (charList.get(extension).getName().equals(cpuTeam.getChar(j).getName())) {
                    c = 0;
                }
            }
            for (int j = 0; j < clientTeam.getPlayerCount(); j++) { // cannot have double character
                if (charList.get(extension).getName().equals(clientTeam.getChar(j).getName())) {
                    c = 0;
                }
            }
            if(c != 0){
                System.out.println(charList.get(extension).getName());
                cpuTeam.addChar(charList.get(extension));
                control++;
            }

        }while(control <3);

        clearConsole();
    }

    public void setupItems(){
        // Armor Construction
        Armor berseksuit = new Armor("Bersek Suit", 1000, 20, 100.3);
        itemsList.add(berseksuit);
        Armor sterlinghauberk = new Armor("Sterling Hauberk", 750, 18, 110.0);
        itemsList.add(sterlinghauberk);
        Armor tombplate = new Armor("Tomb PLate", 800, 17, 70.0);
        itemsList.add(tombplate);
        Armor arcadehauberk = new Armor("Arcadian Hauberk", 900, 15, 50.0);
        itemsList.add(arcadehauberk);
        Armor mysticcoat = new Armor("Mystic Coat", 850, 16, 55.0);
        itemsList.add(mysticcoat);
        Armor garmetglory = new Armor("Garment of Glory", 700, 16, 57.0);
        itemsList.add(garmetglory);

        // Weapon Construction
        Weapon shieldfate = new Weapon("Shield of Fate",493, 30, 5.0);
        itemsList.add(shieldfate);
        Weapon greatsword = new Weapon("Great Sword",986, 30, 5.0);
        itemsList.add(greatsword);
        Weapon ebonblade = new Weapon("Ebon Blade",977, 60, 5.0);
        itemsList.add(ebonblade);
        Weapon soulreaper = new Weapon("Soul Reaper",598, 65, 5.0);
        itemsList.add(soulreaper);
        Weapon conjunction = new Weapon("Conjunction",589, 58, 5.0);
        itemsList.add(conjunction);
        Weapon bloodshave = new Weapon("BloodShave",859, 50, 5.0);
        itemsList.add(bloodshave);
        Weapon steelforgedshield = new Weapon("Steelforged Shield",743, 40, 5.0);
        itemsList.add(steelforgedshield);
        Weapon emissaria = new Weapon("Emissaria",754, 47, 5.0);
        itemsList.add(emissaria);
        Weapon nocturne = new Weapon("Nocturne",800, 57, 5.0);
        itemsList.add(nocturne);
        Weapon scartracer = new Weapon("Scartracer",650, 43, 5.0);
        itemsList.add(scartracer);

        // Health Potion Construction
        HealthPotion redpotion = new HealthPotion("Red Potion", 100, 10);
        itemsList.add(redpotion);
        HealthPotion whitepotion = new HealthPotion("White Potion", 150, 20);
        itemsList.add(whitepotion);
        HealthPotion meat = new HealthPotion("Meat", 200, 30);
        itemsList.add(meat);
        HealthPotion dragonmeat = new HealthPotion("Dragon Meat", 300, 40);
        itemsList.add(dragonmeat);
        HealthPotion redherb = new HealthPotion("Red Herb", 50, 5);
        itemsList.add(redherb);

        // Mana Potion Constructor
        ManaPotion bluepotion = new ManaPotion("Blue Potion", 10, 10);
        itemsList.add(bluepotion);
        ManaPotion blueherb = new ManaPotion("Blue Herb", 10, 5);
        itemsList.add(blueherb);
        ManaPotion holygraal = new ManaPotion("Holy Graal", 10, 30);
        itemsList.add(holygraal);

        System.out.println("\n###################\n#Choose your characters items!#\n###################\n");
        System.out.println("You have to choose one Armor, one Weapon and one Potion per PLayer\n");

        int i=1;
        for (Item item : itemsList){
            System.out.println("[" + i + "]" + item.getName());
            System.out.println("Attack Points: " + item.getAttackPts() + "  Defense Points: " + item.getDefensePts() + "\n");
            i++;
        }

        int choose=0;
        int inputControl;
        String itemType[] = {"Armor", "Weapon", "Potion"};
        for(int j = 0; j < clientTeam.getPlayerCount(); j++) {
            System.out.println(clientTeam.getChar(j).getName());
            i=0;
            do {
                Scanner in = new Scanner(System.in);
                System.out.println(" Gold: " + clientTeam.getChar(j).getGold());
                System.out.print(itemType[i] + ": ");
                do {
                    inputControl = 0;
                    try {
                        System.out.print("choose: ");
                        choose = in.nextInt();
                    } catch (InputMismatchException e){
                        inputControl = 1;
                        in.next();
                    }
                }while(inputControl == 1);
                if (choose > 0 && choose <= itemsList.size()) {
                    switch (i){
                        case 0:
                            if(itemsList.get(choose-1).getClass().equals(Armor.class)){
                                if(clientTeam.getChar(j).getGold() >= itemsList.get(choose - 1).getPrice()) {
                                    clientTeam.getChar(j).spendGold(itemsList.get(choose - 1).getPrice());
                                    clientTeam.getChar(j).addItem(itemsList.get(choose - 1));
                                    clientTeam.getChar(j).equipItem(itemsList.get(choose - 1).getName());
                                    i++;
                                }else{
                                    System.out.println("Not enough gold to buy this item.");
                                }
                            }else {
                                System.out.print("This item is not a Armor.\n");
                            }
                            break;
                        case 1:
                            if(itemsList.get(choose-1).getClass().equals(Weapon.class)){
                                if(clientTeam.getChar(j).getGold() >= itemsList.get(choose - 1).getPrice()) {
                                    clientTeam.getChar(j).spendGold(itemsList.get(choose - 1).getPrice());
                                    clientTeam.getChar(j).addItem(itemsList.get(choose - 1));
                                    clientTeam.getChar(j).equipItem(itemsList.get(choose - 1).getName());
                                    i++;
                                }else{
                                    System.out.println("Not enough gold to buy this item.");
                                }
                            }else {
                                System.out.print("This item is not a Weapon.\n");
                            }
                            break;
                        case 2:
                            if(itemsList.get(choose-1).getClass().equals(HealthPotion.class) || itemsList.get(choose-1).getClass().equals(ManaPotion.class)){
                                if(clientTeam.getChar(j).getGold() >= itemsList.get(choose - 1).getPrice()) {
                                    clientTeam.getChar(j).spendGold(itemsList.get(choose - 1).getPrice());
                                    clientTeam.getChar(j).addItem(itemsList.get(choose - 1));
                                    i++;
                                }else{
                                    System.out.println("Not enough gold to buy this item.");
                                }
                            }else {
                                System.out.print("This item is not a Potion.\n");
                            }
                            break;
                    }
                }
            } while (i < 3); // 3 items per player
        }


        // CPU items select
        Random r = new Random();

        for(int j=0; j<cpuTeam.getPlayerCount(); j++) {
            i=0;
            do {
                choose = (int) (100 * r.nextDouble() % itemsList.size());
                if (choose > 0 && choose <= itemsList.size()) {
                    switch (i){
                        case 0:
                            if(itemsList.get(choose-1).getClass().equals(Armor.class)){
                                if(cpuTeam.getChar(j).getGold() >= itemsList.get(choose - 1).getPrice()) {
                                    cpuTeam.getChar(j).spendGold(itemsList.get(choose - 1).getPrice());
                                    cpuTeam.getChar(j).addItem(itemsList.get(choose - 1).copy());
                                    cpuTeam.getChar(j).equipItem(itemsList.get(choose - 1).getName());
                                    i++;
                                }
                            }
                            break;
                        case 1:
                            if(itemsList.get(choose-1).getClass().equals(Weapon.class)){
                                if(cpuTeam.getChar(j).getGold() >= itemsList.get(choose - 1).getPrice()) {
                                    cpuTeam.getChar(j).spendGold(itemsList.get(choose - 1).getPrice());
                                    cpuTeam.getChar(j).addItem(itemsList.get(choose - 1).copy());
                                    cpuTeam.getChar(j).equipItem(itemsList.get(choose - 1).getName());
                                    i++;
                                }
                            }
                            break;
                        case 2:
                            if(itemsList.get(choose-1).getClass().equals(HealthPotion.class) || itemsList.get(choose-1).getClass().equals(ManaPotion.class)){
                                if(cpuTeam.getChar(j).getGold() >= itemsList.get(choose - 1).getPrice()) {
                                    cpuTeam.getChar(j).spendGold(itemsList.get(choose - 1).getPrice());
                                    cpuTeam.getChar(j).addItem(itemsList.get(choose - 1).copy());
                                    i++;
                                }
                            }
                            break;
                    }
                }
            } while (i < 3); // 3 items per player
        }
        clearConsole();
    }

    public void battle(){
        ArrayList<PlayerThread> threads = new ArrayList<PlayerThread>();

        // Client Team Thread
        PlayerThread t1 = new PlayerThread(clientTeam.getChar(0),clientTeam, cpuTeam);
        t1.start();
        threads.add(t1);
        PlayerThread t2 = new PlayerThread(clientTeam.getChar(1),clientTeam, cpuTeam);
        t2.start();
        threads.add(t2);
        PlayerThread t3 = new PlayerThread(clientTeam.getChar(2),clientTeam, cpuTeam);
        t3.start();
        threads.add(t3);

        // CPU Team Thread
        PlayerThread t4 = new PlayerThread(cpuTeam.getChar(0), cpuTeam, clientTeam);
        t4.start();
        threads.add(t4);
        PlayerThread t5 = new PlayerThread(cpuTeam.getChar(1), cpuTeam, clientTeam);
        t5.start();
        threads.add(t5);
        PlayerThread t6 = new PlayerThread(cpuTeam.getChar(2), cpuTeam, clientTeam);
        t6.start();
        threads.add(t6);


        int round=1;
        int oldRound = 0;
        while (clientTeam.getAlive() > 0 && cpuTeam.getAlive() > 0){
            if(oldRound < round) {
                System.out.println("Round: " + round);
                System.out.println(t1.getPlayerName()+" HP: " + t1.getPlayerHP()+" game: "+t1.getGameRound()+" round:"+t1.getRound());
                System.out.println(t2.getPlayerName()+" HP: " + t2.getPlayerHP()+" game: "+t2.getGameRound()+" round:"+t2.getRound());
                System.out.println(t3.getPlayerName()+" HP: " + t3.getPlayerHP()+" game: "+t3.getGameRound()+" round:"+t3.getRound());
                System.out.println(t4.getPlayerName()+" HP: " + t4.getPlayerHP()+" game: "+t4.getGameRound()+" round:"+t4.getRound());
                System.out.println(t5.getPlayerName()+" HP: " + t5.getPlayerHP()+" game: "+t5.getGameRound()+" round:"+t5.getRound());
                System.out.println(t6.getPlayerName()+" HP: " + t6.getPlayerHP()+" game: "+t6.getGameRound()+" round:"+t6.getRound());
                oldRound++;
            }
            if(round == t1.getRound() && round == t2.getRound() && round == t3.getRound() && round == t4.getRound() && round == t5.getRound() && round == t6.getRound()){
                round++;
                t1.setGameRound(round);
                t2.setGameRound(round);
                t3.setGameRound(round);
                t4.setGameRound(round);
                t5.setGameRound(round);
                t6.setGameRound(round);
                /*for(PlayerThread thr : threads) {
                    thr.setGameRound(round);
                    if(thr.getClientPlayer() != clientPlayer){
                        clientPlayer = thr.getClientPlayer();
                        for(PlayerThread thre : threads){
                            thre.setClientPlayer(clientPlayer);
                        }
                    }
                }*/


            }
        }
        if(clientTeam.getAlive() > 0){
            System.out.println(clientTeam.getName() + " win!");
        }else{
            System.out.println(cpuTeam.getName() + " win!");
        }
    }






    public void stop(){
        bonusThread.cancel();
        manaThread.cancel();
    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

    public static void main(String args[]){
        InGame game = new InGame();

        game.start();
        game.setupTeams();
        game.setupItems();
        game.battle();


        game.stop();
    }
}
