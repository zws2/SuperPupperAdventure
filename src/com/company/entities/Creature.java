package com.company.entities;

public abstract class Creature {

	private String name = "Doge";
	
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
	
	//returns the modifier for your stat. Every 2 points above 2 increases the modifier by 1
		//10=0, 12=1, 14=2, 16=3, 18=4, 20=5. 8=-1, 6=-2, 4=-3, 2=-4, 0=-5.
	public int getMod(int stat) {
		if(stat == 9)
			return -1;
		return (stat-10)/2;				
	}
	
	//prints out the health bar for the specified character
	public void healthBar() {
		System.out.println(name + ":");
		if(this instanceof Player) {
			System.out.print("hp: " + hp + "/" + hpMax + "\t");
			System.out.println("mp: " + mp + "/" + mpMax);
		}
		else if(this instanceof Enemy)
			System.out.println("hp: " + hp + "/" + hpMax);
	}
	
	public int hit() {
		int accuracy = (int)(Math.random() * 20) + 1;
		return accuracy;
	}

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
		if(hp < 0)
			hp = 0;
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
		if(mp < 0)
			mp = 0;
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

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
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
	
//	public double getSpeed(double speed) {
//		if(this instanceof Player)
//			(Player) .getMainhand().getSpeed();
//	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}
	
	//sets your experience to the new value and returns true if you've leveled up
	public boolean setExperience(int experience) {
		this.experience = experience;
		if(this instanceof Player){
			if(this.experience >= experienceCap) {
				this.experience -= experienceCap;
				experienceCap *= 2;
				return true;
			}
		}
		
		return false;
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
	
	
}
