package com.company.savestate;

/**
 *
 * @author Valkor
 */
public class CreatureSave {
    private String name = "jGRASPHater";
	
    private int hp = 10;//if this drops to 0 in combat you die.
    private int hpMax = 10;//the limit to your hp
    private int mp = 10;//used to cast spells and skills
    private int mpMax = 10;//the limit to your mp

    private int strength = 10;//strength increases damage and accuracy and is required for heavy armor
    private int dexterity = 10;//dex increases accuracy, defense and speed
    private int constitution = 10;//constitution increases health per level health 
    private int intelligence = 10;//intelligence adds damage to your spells
    private int wisdom = 10;//wisdom gives you mana per level
    private int charisma = 10;//charisma lowers prices in shop and effects certain skills

    private int accuracy = 0;//You add this score to your rolls to see if you hit
    private int defense = 10;//you must roll >= defense to deal damage to a target

    private int level = 1;//a general indicator of strength
    private int experience = 0;//gather xp >= xpcap and you lvl up
    private int experienceCap = 50;//the required amount of xp to lvl up

    private int gold = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public void setMpMax(int mpMax) {
        this.mpMax = mpMax;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperienceCap() {
        return experienceCap;
    }

    public void setExperienceCap(int experienceCap) {
        this.experienceCap = experienceCap;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "Creature{" + "name=" + name + ", hp=" + hp + ", hpMax=" 
                + hpMax + ", mp=" + mp + ", mpMax=" + mpMax + ", strength=" 
                + strength + ", dexterity=" + dexterity + ", constitution=" 
                + constitution + ", intelligence=" + intelligence + ", wisdom=" 
                + wisdom + ", charisma=" + charisma + ", accuracy=" + accuracy 
                + ", defense=" + defense + ", level=" + level + ", experience=" 
                + experience + ", experienceCap=" + experienceCap + ", gold=" 
                + gold + '}';
    }
    
    
}
