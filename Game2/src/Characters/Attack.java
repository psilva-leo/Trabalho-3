package Characters;

interface Attack {

    int chooseSkill();

    int chooseSkillCPU();

    void attackCharacter(Character enemy, int skill);

}
