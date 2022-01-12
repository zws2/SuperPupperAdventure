package com.company.items;

public class Wand extends Weapon{
	
	public Wand(String name) {

		setName(name);

		if(getName() == "Wooden Wand") {
			setValue(1);
			setRarity(-1);
			setMana(10);
			setFlavorText("It's just a stick.\nYou like sticks.");
		}
		
		if(getName() == "Copper Wand") {
			setValue(10);
			setRarity(0);
		}
		
		if(getName() == "Silver Wand") {
			setValue(50);
			setRarity(1);
		}
		
		if(getName() == "Gold Wand") {
			setValue(250);
			setRarity(2);
		}
		
		if(getName() == "Platinum Wand") {
			setValue(1000);
			setRarity(3);
		}
		
		if(getName() == "Orichalcum Wand") {
			setValue(5000);
			setRarity(4);
		}
		
		setEquipment(true);
		setHeavy(false);
		setDamage(1,4,getRarity());//1d4+rarity
		setSpeed(1);
		setCrit(.0);
		
		Item.getIndex()[numberOfItems] = this;
	}
	
}
