package com.company.items;

public class Armor extends Item{

	private int mana;
	private int health;
	private int defense;
	private boolean heavy;
	
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isHeavy() {
		return heavy;
	}


	public void setHeavy(boolean heavy) {
		this.heavy = heavy;
	}
	
	public static void printMana(Armor armor) {
		if(armor.getMana() != 0)
			System.out.println("Mana: " + armor.mana);
	}
	
	public static void printHealth(Armor armor) {
		if(armor.health != 0)
			System.out.println("Health: " + armor.health);
	}
	
	public static void printDefense(Armor armor) {
		if(armor.defense != 0)
			System.out.println("Defense: " + armor.defense);
	}
	
	public static void printHeavy(Armor armor) {
		if(armor.isEquipment() == true) {
			if(armor.isHeavy() == true)
				System.out.print("2 handed");
			else System.out.print("1 handed");
		}	
	}
	
	public static void printToolTip(Armor armor) {

		if(armor.getID() == 1)
			return;
		else {
			//printID(armor);
			printName(armor);
			printHeavy(armor);
			printRarity(armor);
			printHeal(armor);
			printDefense(armor);
			printValue(armor);
			printMana(armor);
			printHealth(armor);
			printFlavorText(armor);
			
		}
	}
	
	public static Armor deleteArmor(Armor armor) {
		armor.setID(1);
		armor.setName("");
		armor.setValue(0);
		armor.setRarity(0);
		armor.mana = 0;
		armor.health = 0;
		armor.defense = 0;
		armor.heavy = false;
		armor.setEquipment(false);
		return armor;
	}

}
