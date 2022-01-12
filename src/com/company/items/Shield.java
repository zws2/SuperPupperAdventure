package com.company.items;

public class Shield extends Armor {
	
	public Shield(String name) {
		
		setName(name);
		
		if(getName() == "Wooden Shield") {
			setValue(10);
			setRarity(0);
		}
		
		setEquipment(true);
		setDefense(2);
		
		Item.getIndex()[numberOfItems] = this;
	
	}
}
