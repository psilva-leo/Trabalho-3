package Items;

import Characters.Character;

import java.util.ArrayList;

public class Armor extends Item{

    protected double weight;

    public Armor(String name, double price, int defensepts, double weight){
        super(name, price);
        // seta defense point range de 1 a 20.
        if(defensepts < 1){
            this.defensepts = 1;
        }else if(defensepts > 20){
            this.defensepts = 20;
        }else{
            this.defensepts = defensepts;
        }

        // seta attack point range de 1 a 20.
        if(weight < 1){
            this.weight = 1;
        }else if(weight > 20){
            this.weight = 20;
        }else{
            this.weight = weight;
        }
        this.attackpts = 0;
    }

    public Item copy(){
        return new Armor(this.getName(), this.getPrice(), this.getDefensePts(), this.getWeight());
    }

    public int getDefensePts(){
        return defensepts;
    }

    public int getAttackPts(){
        return 0;
    }

    public double getWeight(){
        return weight;
    }

    // Altera a velocidade do personagem qunado equipado.
    public void use(Character character){
        int new_speed = (int) (character.getSpeed()-(weight/2));
        character.setSpeed(new_speed);
    }

    // Altera a velocidade do personagem qunado desequipado.
    public void unuse(Character character){
        int new_speed =  (int) (character.getSpeed()+(weight/2));
        character.setSpeed(new_speed);
    }
}
