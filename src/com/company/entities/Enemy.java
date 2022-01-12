package com.company.entities;

import com.company.items.Item;
import com.company.items.Weapon;

public class Enemy extends Creature {
	
	private static Enemy[] index = new Enemy[100];
	private static int numberOfEnemies;
	private int id;
	private Item weapon;
	
	public Enemy(String name) {
		setName(name);
		
		if(name == "goblin") {
			setExperience(20);
			setGold(5);
			setWeapon(Item.checkIndex("Wooden Wand"));
		}
		
		if(name == "wolf") {
			setHpMax(15);
			setHp(15);
			setExperience(35);
			setStrength(12);
			setWeapon(Item.checkIndex("Wooden Wand"));
		}
		
		if(name == "bear") {
			setHpMax(25);
			setHp(25);
			setExperience(75);
			setStrength(16);
			setDefense(12);
			setWeapon(Item.checkIndex("Wooden Wand"));
		}
		
		if(name == "ogre") {
			setHpMax(50);
			setHp(50);
			setExperience(250);
			setStrength(16);
			setGold(50);
			setWeapon(Item.checkIndex("Wooden Sword"));
		}
		
		numberOfEnemies++;
		this.id = numberOfEnemies;
		index[numberOfEnemies] = this;
	}
	
	public void restore() {
		setHp(getHpMax());
		setMp(getMpMax());
	}
	
	public int attack(Weapon weapon, Creature target) {
		boolean crit = false;
		if(hit()+getAccuracy() >= target.getDefense()) {
			int damage = weapon.getDamage();
			damage += getMod(getStrength());
			if(Math.random() < weapon.getCrit()){
				damage *= 2;
				if(damage == 0) //prevent 0 damage crits
					damage = 1;
				crit = true;
			}
			
			if(crit) {
				System.out.println("Critical hit!");
			}
			System.out.println(getName() + " dealt " + damage + " damage!");
			return damage;
		}
		else {
			System.out.println(getName() + " missed!");
			return 0;
		}
	}
	
	public static Enemy[] getIndex() {
		return index;
	}

	public static void setIndex(Enemy index, int i) {
		Enemy.index[i] = index;
	}
	
	public int getID() {
		return id;
	}


	public void setID(int id) {
		this.id = id;
	}
	
	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}
	
	public void printStatSheet() {
		healthBar();
		System.out.println("Level:\t\t" + getLevel());
		System.out.println("Accuracy:\t" + getAccuracy());
		System.out.println("Defense:\t" + getDefense());
		System.out.println("Strength:\t" + getStrength());
		System.out.println("Dexterity:\t" + getDexterity());
		System.out.println("Constitution:\t" + getConstitution());
		System.out.println("Intelligence:\t" + getIntelligence());
		System.out.println("Wisdom:\t\t" + getWisdom());
		System.out.println("Charisma:\t" + getCharisma());
	}
	
}
