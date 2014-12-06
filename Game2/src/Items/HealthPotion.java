package Items;
import Characters.Character;

public class HealthPotion extends Potion{

    public HealthPotion(String name, double price, int restore){
        super(name, price, restore);
    }

    public Item copy(){
        return new HealthPotion(this.getName(), this.getPrice(), this.getRestorePts());
    }

    public void use(Character character){
        character.addHP(getRestorePts());
        character.removeItem(getName());
    }

    public void unuse(Character character){}
}
