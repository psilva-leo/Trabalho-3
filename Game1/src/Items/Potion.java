package Items;

public abstract class Potion extends Item{

    private int restorepts;

    public Potion(String name, double price, int restore){
        super(name, price);
        restorepts = restore;
    }

    public Potion(Potion copyPotion){
        this(copyPotion.getName(), copyPotion.getPrice(), copyPotion.getRestorePts());
    }

    public abstract Item copy();

    public int getRestorePts(){
        return restorepts;
    }

    // Tipo poção não tem atack point nem defense point.
    public int getAttackPts(){
        return 0;
    }

    public int getDefensePts(){
        return restorepts;
    }

    // Necessario pois em Item (classe pai) é setado como abstract.
    public void use(Character character){}

    public void unuse(Character character){}
}
