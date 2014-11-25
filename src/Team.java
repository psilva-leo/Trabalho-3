import java.util.ArrayList;
import Characters.Character;

public class Team {

    private String name;
    private Color color;
    private int win;
    private int lose;
    private int draw;
    ArrayList<Character> characters;

    public Team(String name, Color color){
        this.name = name;
        this.color = color;
        characters = new ArrayList<Character>();
        win = 0;
        lose = 0;
        draw = 0;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    // Retorna o numero de vitorias, perda e empates do time.
    public String getResults(){
        return "Wins: " + win + " Losses: " + lose + " Draw: " +draw;
    }

    // Retorna o total de HP do time dividido pelo numero de jogadores. 
    public int getPoints(){
        int point = 0;
        int length = characters.size();
        if(length == 0){
            return 0;
        }

        for(int i=0; i<length; i++){
            point += characters.get(i).getHP();
        }
        return (point/characters.size());
    }

    public int getPlayerCount(){
        return characters.size();
    }
    public Character getChar(int index){
        return characters.get(index);
    }

    // Retorna o numero de jogadres vivos do time.
    public int getAlive(){
        int alive =0;
        int length = characters.size();
        for(int i=0; i<length; i++){
            if(characters.get(i).getHP() > 0){
                alive++;
            }
        }
        return alive;
    }

    // Retorna o nome do time e a cor que o referencia numa string.
    public String toString(){
        return "Name: " + name + " Color " + color;
    }

    // Lista o nome dos jogadores no time.
    public void listChar(){
        int length = characters.size();
        for(int i=0; i<length; i++){
            System.out.println(characters.get(i).getName());
        }
    }

    // Compara os pontos de cada time (vide getPoints) e da vitoria caso maior, perda caso menor e empate caso igual
    public void resolveBattle(Team enemy_team){
        if(this.getPoints() > enemy_team.getPoints()){
            win++;
        }else if(this.getPoints() < enemy_team.getPoints()){
            lose++;
        }else{
            draw++;
        }
    }

    public void addChar(Character charObj){
        characters.add(charObj);
    }

    // Remove o personagem pelo indice dele.
    public void removeChar(int index){
        characters.remove(index);
    }

    // Remove o personagem passado.
    public void removeChar(Character charObj){
        int length = characters.size();
        for(int i=0; i<length; i++){
            if(characters.get(i).getName().equals(charObj.getName())){
                removeChar(i);
                i = characters.size();
            }
        }
    }

}
