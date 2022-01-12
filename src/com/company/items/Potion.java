package com.company.items;

public class Potion extends Item{
	
	public Potion(String name) {
		
		setName(name);

		if(getName() == "Potion of Healing") {
			setValue(5);
			setRarity(0);
			setHeal(2,4,2);
			setFlavorText("This red liquid in a tiny vial smells like herbs.");
		}
		
		if(getName() == "Dog Treat") {
			setValue(1);
			setRarity(-1);
			setHeal(2,4,2);
			setFlavorText("This tastey snack smells like bacon.");
		}
		
		setUsable(true);
		
		Item.getIndex()[numberOfItems] = this;
	}
}
