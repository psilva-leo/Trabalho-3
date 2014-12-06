package Items;

import Characters.Character;

public abstract class Item {

    private String name;
    private double price;
    protected int attackpts;
    protected int defensepts;

    public Item(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public abstract Item copy();

    public abstract int getAttackPts();

    public abstract int getDefensePts();

    public double getPrice(){
        return this.price;
    }

    public abstract void use(Character character);

    public abstract void unuse(Character character);
}
