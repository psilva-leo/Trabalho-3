package Items;

import Characters.Character;

public class ManaPotion extends Potion {

    public ManaPotion(String name, double price, int restore) {
        super(name, price, restore);
    }

    public Item copy(){
        return new ManaPotion(this.getName(), this.getPrice(), this.getRestorePts());
    }

    // Adiciona mana ao personagem.
    public void use(Character character) {
        character.addMP(getRestorePts());
        character.removeItem(getName());
    }

    //necessario pois no pai é abstract.
    public void unuse(Character character) { }
}
