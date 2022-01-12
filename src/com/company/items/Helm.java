package com.company.items;

public class Helm extends Armor{
	
	public Helm(String name) {
		
		setName(name);

		if(getName() == "Cool Hat") {
			setValue(10);
			setRarity(0);
			setDefense(1);
			setHeavy(false);
			setFlavorText("This Hat is so cool it distracts your opponents!");
		}
		
		if(getName() == "Copper Helm") {
			setValue(10);
			setRarity(0);
		}
		
		if(getName() == "Bronze Helm") {
			setValue(50);
			setRarity(1);
		}
		
		if(getName() == "Iron Helm") {
			setValue(250);
			setRarity(2);
		}
		
		if(getName() == "Steel Helm") {
			setValue(1000);
			setRarity(3);
		}
		
		if(getName() == "Mithril Helm") {
			setValue(5000);
			setRarity(4);
		}
		
		setEquipment(true);
		
		Item.getIndex()[numberOfItems] = this;
	}
}
