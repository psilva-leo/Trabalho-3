package Items;

import Characters.Character;

public class Weapon extends Item{

    protected double range;

    public Weapon(String name, double price, int attackpts, double range){
        super(name, price);
        // seta attack point range de 1 a 20.
        if(attackpts < 1){
            this.attackpts = 1;
        }else if(attackpts > 9){
            this.attackpts = 9;
        }else{
            this.attackpts = attackpts;
        }
        
        this.range = range;
        this.defensepts = 0;
    }

    public Item copy(){
        return new Weapon(this.getName(), this.getPrice(), this.getAttackPts(), this.getRange());
    }

    public int getDefensePts(){
        return 0;
    }

    public int getAttackPts(){
        return attackpts;
    }

    public double getRange(){
        return range;
    }

    // Necessário pois o pai é abstrato.
    public void use(Character character){

    }

    // Necessário pois o pai é abstrato.
    public void unuse(Character character){

    }

}
