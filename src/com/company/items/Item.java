package com.company.items;

public abstract class Item {
	
	private int id;
	private String name;
	private int value;
	private int rarity;
	private int healDice[] = new int [3];
	private boolean equipment = false;
	private boolean usable = false;
	private String flavorText = "";
	
	private static Item[] index = new Item[100];
	public static int numberOfItems = -1;
	
	public Item() {
		
		//maintains a running system of item ids that are tracked in the index.
		numberOfItems++;
		this.setID(numberOfItems);
		
	}
	
	public static Item[] getIndex() {
		return index;
	}

	public static void setIndex(Item index, int i) {
		Item.index[i] = index;
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	
	public String getName(String name) {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public int getHeal() {
		int heal = (int) (healDice[0]*(Math.random() * healDice[1])+ 1 + healDice[2]);
		return heal; 
	}

	public void setHeal(int numDice, int sizeDice, int mod) {
		this.healDice[0] = numDice;
		this.healDice[1] = sizeDice;
		this.healDice[2] = mod;
	}

	public boolean isEquipment() {
		return equipment;
	}
	
	public void setEquipment(boolean equipment) {
		this.equipment = equipment;
	}
	
	public boolean isUsable() {
		return usable;
	}
	
	public void setUsable(boolean usable) {
		this.usable = usable;
	}
	
	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}
	
	public static Item deleteItem(Item randItem) {
		randItem.id = 1;
		randItem.name = "";
		randItem.value = 0;
		randItem.rarity = 0;
		randItem.healDice[0] = 0;
		randItem.healDice[1] = 0;
		randItem.healDice[2] = 0;
		randItem.equipment = false;
		return randItem;
	}
	
	public static Item checkIndex(String name) {
		for(int i=0;i<100;i++)//search for item
			if(index[i].name == name) {
				return index[i];
			}
		return null;
	}
	
	public static void printID(Item randItem) {
		System.out.println("ID set to: " + randItem.getID());
	}
	
	public static void printName(Item randItem) {
		System.out.println(randItem.getName());
	}
	
	public static void printValue(Item randItem) {
		System.out.println("The value of the item is " + randItem.getValue() + " dc");
	}
	
	public static void printHeal(Item randItem) {
		if(randItem.healDice[0] != 0 && randItem.healDice[2] != 0)
		System.out.println("heal: " + randItem.healDice[0] + "d" + randItem.healDice[1] + " + " + randItem.healDice[2]);
	}
	
	public static void printFlavorText(Item randItem) {
		if(randItem.getFlavorText() != "")
			System.out.println(randItem.getFlavorText());
	}
	
	public static void printRarity(Item randItem) {
		String temp = "error";
		switch (randItem.getRarity()) {
		case -1: temp = "junk";
				 break;
        case 0:  temp = "Common";
                 break;
        case 1:  temp = "Uncommon";
                 break;
        case 2:  temp = "Rare";
                 break;
        case 3:  temp = "Epic";
                 break;
        case 4:  temp = "Legendary";
                 break;
        case 5:  temp = "Godly";
                 break;
		}
		
		System.out.println(temp);
	}
	
	public static void printToolTip(Item randItem) {

		if(randItem.getID() == 1)
			return;
		else {
			//printID(randItem);
			printName(randItem);
			printRarity(randItem);
			printValue(randItem);
			printHeal(randItem);
			printFlavorText(randItem);
			
		}
	}	
}
