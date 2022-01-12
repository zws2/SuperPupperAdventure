package com.company.items;

public class Trinket extends Armor{
	
public Trinket(String name) {
		
		setName(name);

		if(getName() == "Wooden Ring") {
			setValue(15);
			setRarity(0);
			setHealth(5);
			setFlavorText("This polished wooden ring pulses with a vague magical energy.");
		}
		
		setEquipment(true);
		
		Item.getIndex()[numberOfItems] = this;
	}

}
