package com.company.items;

public class MiscItem extends Item{
	
	public MiscItem(String name) {
		setName(name);
		
		if(getName() == "Copper Nugget") {
			setValue(10);
			setRarity(0);
			setFlavorText("A lump of orange metal.");
		}
		
		setEquipment(false);
		
		Item.getIndex()[numberOfItems] = this;
	}

}
