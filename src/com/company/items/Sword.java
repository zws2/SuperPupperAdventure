package com.company.items;

public class Sword extends Weapon{
	
	public Sword(String name) {
		
		setName(name);

		if(getName() == "Wooden Sword") {
			setValue(1);
			setRarity(-1);
		}
		
		if(getName() == "Copper Sword") {
			setValue(10);
			setRarity(0);
		}
		
		if(getName() == "Bronze Sword") {
			setValue(50);
			setRarity(1);
		}
		
		if(getName() == "Iron Sword") {
			setValue(250);
			setRarity(2);
		}
		
		if(getName() == "Steel Sword") {
			setValue(1000);
			setRarity(3);
		}
		
		if(getName() == "Mithril Sword") {
			setValue(5000);
			setRarity(4);
		}
		
		setEquipment(true);
		setHeavy(false);
		setDamage(1,6,getRarity());//1d6 + rarity
		setSpeed(1);
		setCrit(.05);
		
		Item.getIndex()[numberOfItems] = this;
	
	}
}
