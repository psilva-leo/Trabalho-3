package Characters;

import Game.Team;
import Items.HealthPotion;
import Items.Inventory;
import Items.Item;
import Items.ManaPotion;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Character {

    private String alias;
    private Inventory myitems;
    private int bonus;
    private int maxBonus;
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;
    protected int XP;
    protected int strength;
    protected int speed;
    protected int dexterity;
    protected int constitution;

    public Character(String name){
        alias = name;
        bonus = 0;
        maxBonus = 100;
        HP = 100;
        maxHP = 100;
        MP = 100;
        maxMP = 100;
        XP = 1;
        myitems = new Inventory();
        myitems.setSpaces(10);
        myitems.earnGold(5000);
        constitution = 0;
        dexterity = 0;
        strength = 0;
        speed = 0;
    }

    public int getDefensePoints(){
        int item_def_pts = myitems.getSumDefencePts();
        double temp = ((constitution*0.6+dexterity*0.1+speed*0.3)+item_def_pts)*( (double) XP/6);
        return (int) temp;
    }

    public int getAttackPoints(){
        int item_att_pts = myitems.getSumAttackPts();
        double temp =  ((strength*0.6+dexterity*0.4)+item_att_pts)*( (double) XP/2);
        return  (int) temp;
    }

    public void attackCharacter(Character enemy, int skill){}

    public int chooseSkill(){ return 0; }

    public int chooseSkillCPU(){ return 0; }

    public String getName(){
        return this.alias;
    }

    public int getBonus(){
        return this.bonus;
    }

    public int getHP(){
        return this.HP;
    }

    public int getMP(){
        return this.MP;
    }

    public int getXP(){
        return this.XP;
    }

    public int getConstitution(){
        return this.constitution;
    }

    public int getDexterity(){
        return this.dexterity;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getSpeed(){
        return speed;
    }

    public double getGold(){
        return myitems.getTotalGold();
    }


    public void addXP(int add){
        XP = XP+add;
        if( XP > 100){
            XP = XP - 100;
            // level up!
        }
    }

    public void addBonus(int add){
        bonus = bonus+add;
        if( bonus > maxBonus){
            bonus = maxBonus;
        }
    }

    public void addHP(int add){
        HP = HP+add;
        if( HP > maxHP){
            HP = maxHP;
        }
    }

    public void addMP(int add){
        MP = MP+add;
        if( MP > maxMP){
            MP = maxMP;
        }
    }


    public int checkAttributes(){
        if(strength+speed+dexterity+constitution > 100){
            return 100-(strength+speed+dexterity+constitution);
        }
        return 0;
    }

    public void setBonus(int new_bonus){
        if(new_bonus <= maxBonus){
            bonus = new_bonus;
        } else{
            bonus = maxBonus;
        }
    }
    public void setHP(int new_HP){
        if(new_HP <= maxHP){
            HP = new_HP;
        } else{
            HP = maxHP;
        }

    }

    public void setStrength(int new_strength){
        strength =	new_strength - checkAttributes();
    }

    public void setSpeed(int new_speed){
        speed =	new_speed - checkAttributes();
    }

    public void setDexterity(int new_dexterity){
        dexterity =	new_dexterity - checkAttributes();
    }

    public void setConstitution(int new_constitution){
        constitution =	new_constitution - checkAttributes();
    }

    public void addItem(Item new_item){
    myitems.insertItem(new_item);
    }

    public void removeItem(String name){
        myitems.removeItem(name);
    }

    public void removeItem(int index){
        myitems.removeItem(index);
    }

    public void spendGold(double value){
        myitems.spendGold(value);
    }

    public int getNumberOfItems(){
        return myitems.getNumberOfItems();
    }
    public void listItems(){
        myitems.listItems();
    }

    public void listPotionItems(){
        if(myitems.getNumberOfItems() == 0){
            System.out.println("Inventory is empty");
        }else {
            for (int i = 0; i < myitems.getNumberOfItems(); i++) {
                if(myitems.searchItem(i).getClass().equals(ManaPotion.class) || myitems.searchItem(i).getClass().equals(HealthPotion.class)) {
                    System.out.print(myitems.searchItem(i).getName());
                    System.out.println("    Restore points: " + myitems.searchItem(i).getDefensePts());
                }
            }
        }
    }

    public int getNumberOfPotionItems(){
        int counter = 0;
        for (int i = 0; i < myitems.getNumberOfItems(); i++) {
            if(myitems.searchItem(i).getClass().equals(ManaPotion.class) || myitems.searchItem(i).getClass().equals(HealthPotion.class)) {
                counter++;
            }
        }
        return counter;
    }

    public Item searchItem(String name){
        if(myitems.searchItem(name) != null){
            return myitems.searchItem(name);
        }
        return null;
    }

    public Item searchItem(int index){
        if(myitems.searchItem(index) != null){
            return myitems.searchItem(index);
        }
        return null;
    }


    public void getItemsPrice(){
        if(myitems.getNumberOfItems() == 0){
            System.out.println("Inventory is empty");
        }else {
            for (int i = 0; i < myitems.getNumberOfItems(); i++) {
                System.out.println(myitems.searchItem(i).getName());
                System.out.println(myitems.searchItem(i).getPrice());
            }
        }

    }

    public boolean equipItem(String name){
        return myitems.equipItem(name, this);
    }

    public void unequipItem(String name){
        myitems.unequipItem(name, this);
    }







}
