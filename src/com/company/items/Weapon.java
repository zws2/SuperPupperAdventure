package com.company.items;

public class Weapon extends Item{
	
	private int damageDice[] = new int[3];
	private int mana;
	private int health;
	private int defense;
	private double speed;
	private double crit;
	private boolean heavy;

	public int getDamage() {
		int damage = (int) (damageDice[0]*(Math.random() * damageDice[1] + 1) + damageDice[2]);
		if(damage < 0)
			return 0;
		else return damage; 
	}

	public void setDamage(int numDice, int sizeDice, int mod) {
		this.damageDice[0] = numDice;
		this.damageDice[1] = sizeDice;
		this.damageDice[2] = mod;
	}
	
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
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

	public double getCrit() {
		return crit;
	}


	public void setCrit(double crit) {
		this.crit = crit;
	}


	public boolean isHeavy() {
		return heavy;
	}


	public void setHeavy(boolean heavy) {
		this.heavy = heavy;
	}
	
	public static void printMana(Weapon randWeapon) {
		if(randWeapon.getMana() != 0)
			System.out.println("Mana: " + randWeapon.mana);
	}
	
	public static void printHealth(Weapon randWeapon) {
		if(randWeapon.health != 0)
			System.out.println("Health: " + randWeapon.health);
	}
	
	public static void printDamage(Weapon randWeapon) {
		if(randWeapon.damageDice[0] != 0 && randWeapon.damageDice[2] != 0)
		System.out.println("Damage: " + randWeapon.damageDice[0] + "d" + randWeapon.damageDice[1] + " + " + randWeapon.damageDice[2]);
	}
	
	public static void printSpeed(Weapon randWeapon) {
		if(randWeapon.speed != 0)
			System.out.println("Speed: " + randWeapon.speed);
	}
	public static void printCrit(Weapon randWeapon) {
		if(randWeapon.crit != 0)
			System.out.println("Crit: " + randWeapon.crit);
	}
	public static void printDefense(Weapon randWeapon) {
		if(randWeapon.defense != 0)
			System.out.println("Defense: " + randWeapon.defense);
	}
	
	public static void printHeavy(Weapon randWeapon) {
		if(randWeapon.isEquipment() == true) {
			if(randWeapon.isHeavy() == true)
				System.out.print("2 handed");
			else System.out.print("1 handed");
		}	
	}
	
	public static void printToolTip(Weapon randWeapon) {

		if(randWeapon.getID() == 1)
			return;
		else {
			//printID(randWeapon);
			printName(randWeapon);
			printHeavy(randWeapon);
			printRarity(randWeapon);
			printDamage(randWeapon);
			printHeal(randWeapon);
			printSpeed(randWeapon);
			printCrit(randWeapon);
			printDefense(randWeapon);
			printValue(randWeapon);
			printMana(randWeapon);
			printHealth(randWeapon);
			printFlavorText(randWeapon);
			
		}
	}
	
	public static Weapon deleteWeapon(Weapon randWeapon) {
		randWeapon.setID(1);
		randWeapon.setName("");
		randWeapon.setValue(0);
		randWeapon.setRarity(0);
		randWeapon.damageDice[0] = 0;
		randWeapon.damageDice[1] = 0;
		randWeapon.damageDice[2] = 0;
		randWeapon.mana = 0;
		randWeapon.health = 0;
		randWeapon.speed = 0;
		randWeapon.crit = 0;
		randWeapon.defense = 0;
		randWeapon.heavy = false;
		randWeapon.setEquipment(false);
		return randWeapon;
	}

}
