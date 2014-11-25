package Items;
import java.util.ArrayList;

import Characters.Character;

public class Inventory{

    private int spaces;
    private double gold;
    private ArrayList<Pair> items;

    public Inventory(){
        this.spaces = 0;
        this.gold = 0.0;
        items =  new ArrayList<Pair>();
    }

    public int getNumberOfItems(){
        return items.size();
    }

    // Soma o attack ponit de cada item equipado.
    public int getSumAttackPts(){
        int item_att_pts = 0;

        for(Pair item : items){
            if(item.second) {
                item_att_pts = item_att_pts+item.first.getAttackPts();
            }
        }
        return item_att_pts;
    }

    // Soma o defense ponit de cada item equipado.
    public int getSumDefencePts(){
        int item_def_pts = 0;
        for(Pair item : items){
            if(item.second) {
                item_def_pts = item_def_pts + item.first.getDefensePts();
            }
        }
        return item_def_pts;
    }

    public double getTotalGold(){
        return this.gold;
    }

    // retorna espaços vazios do inventório.
    public int getAvailableSpace(){
        return this.spaces;
    }

    public void setSpaces(int spaces){
        this.spaces = this.spaces + spaces;
    }

    // Esta funcao gasta dinheiro com algo. Assim, uma futura função para a compra deve ser implementada.
    public void spendGold(double gold){
        this.gold = this.gold - gold;
    }

    public void earnGold(double gold){
        this.gold = this.gold + gold;
    }

    // Adiciona o item no inventório.
    public void insertItem(Item new_item){
        Pair temp = new Pair(new_item,false);
        if(getAvailableSpace() == 0){
            System.out.println("Inventory is full");
        }else{
            items.add(temp);
            spaces--;
        }
    }

    // Remove o item pelo nome.
    public void removeItem(String name){
        int pos = searchItemPosition(name);
        items.remove(pos);
        spaces++;
    }

    // Remove item pelo indice.
    public void removeItem(int index){
        items.remove(index);
        spaces++;
    }

    // Procura item pelo nome.
    public Item searchItem(String name){
        for (Pair item : items) {
            if (item.first.getName().equalsIgnoreCase(name)) {
                return item.first;
            }
        }
        return null;
    }

    // Procura item pelo indice.
    public Item searchItem(int index){
        if(index >= 0 && index < items.size()){
            return items.get(index).first;
        }
        return null;
    }

    // Procura a posicao do item pelo nome.
    public int searchItemPosition(String name){
        int i = 0;
        for (Pair item : items) {
            if (item.first.getName().equalsIgnoreCase(name)) {
                return i;
            }
            i++;
        }
        System.out.println("Item not found");
        return -1;
    }

    // Printa os items no inventorio.
    public void listItems(){
        if(getNumberOfItems() == 0){
            System.out.println("Inventory is empty.");
        }else{
            for(Pair item : items){
                System.out.println(item.first.getName());
            }
        }
    }

    // Equipa o item. Se for do tipo poção ela é usada e depois retirada do inventório,
    // Se for weapon é apenas equipada pair.second = true e se for Armor altera a speed e seta pair.second como true.
    public boolean equipItem(String name, Character character){
        int pos = searchItemPosition(name);
        if(pos != -1){
            items.get(pos).second = true;
            items.get(pos).first.use(character);
            return true;
        }
        return false;
    }

    // Se for weapon é apenas equipada pair.second = false e se for Armor altera a speed e seta pair.second como false.    
    public void unequipItem(String name, Character character){
        int pos = searchItemPosition(name);
        if(pos != -1){
            items.get(pos).second = false;
            items.get(pos).first.unuse(character);
        }
    }














}
