package Characters;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Knight extends Character implements Attack{

    protected int power;

    public Knight(String name, int power){
        super(name);
        this.power = power;
    }

    // Adiciona power ao personagem.
    public void AddPower(int add){
        power = power+add;
    }

    public int getPower(){
        return power;
    }

    // Igual ao attack da classe Character.
    public void attackCharacter(Character enemy, int skill){
        double miss_chance = 0.1/this.getXP();
        double critical_chance = 0.02*(this.getXP()/2);
        Random r = new Random();
        double x = r.nextDouble();
        double randomValue = -5 + 10*x;
        double attack_power;

        if(miss_chance > x){
            System.out.println("Miss!");
        }else{
            attack_power = (this.getAttackPoints() - enemy.getDefensePoints())+randomValue;
            switch (skill){
                case 1:
                    attack_power *= 1;
                    break;
                case 2:
                    attack_power *= 1.5;
                    break;
                case 3:
                    attack_power *= 1.2;
                    break;
                case 4:
                    attack_power *= 1.4;
                    break;
                case 5:
                    if(attack_power == 0) attack_power = 5;
                    attack_power *= 4;
                    break;
            }
            if(attack_power <= 0){
                attack_power = 1;
            }
            if(critical_chance > x) {
                System.out.println("Critical Attack!");
                attack_power = attack_power * 2;
            }
            System.out.println("Attack power: " + (int) attack_power);
            enemy.setHP( (enemy.getHP()- (int) attack_power));
            if(enemy.getHP() < 0){
                enemy.setHP(0);
            }
        }
    }

    // Igual ao attack da classe Character adiconado de power.
    public int getDefensePoints(){
        return super.getDefensePoints() + getPower();
    }

    // Igual ao attack da classe Character.
    public int getAttackPoints(){
        return super.getAttackPoints();
    }

    public int chooseSkill(){
        int valid = 0;
        int choose = 0;
        int inputControl;

        do {
            System.out.println("1 - Bash [0]           2 - Magnum Break [40]");
            System.out.println("3 - Spear Stab [10]     4 - Pierce [30]");
            System.out.println("5 - Grand Cross (Especial)");
            Scanner in = new Scanner(System.in);
            do {
                inputControl = 0;
                try {
                    System.out.print("choose: ");
                    choose = in.nextInt();
                } catch (InputMismatchException e) {
                    inputControl = 1;
                    in.next();
                }
            } while (inputControl == 1);
            valid = SkillCost(choose);
        }while(valid == 0);
        return choose;
    }

    public int chooseSkillCPU() {
        Random r = new Random();
        int valid = 0;
        int choose;

        do {
            choose = (int) (10*r.nextDouble() % 5) +1;
            valid = SkillCost(choose);
        }while(valid == 0);
        return choose;
    }

    public int SkillCost(int choose){
        int valid = 0;
        switch (choose){
            case 1:
                valid = 1;
                break;
            case 2:
                if(getMP() >= 40) {
                    addMP(-40);
                    valid = 1;
                }
                break;
            case 3:
                if(getMP() >= 10) {
                    addMP(-10);
                    valid = 1;
                }
                break;
            case 4:
                if(getMP() >= 30) {
                    addMP(-30);
                    valid = 1;
                }
                break;
            case 5:
                if(getBonus() == 100){
                    addBonus(-100);
                    valid = 1;
                }
                break;
        }

        return valid;

    }

}

