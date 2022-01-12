package com.company.entities;

import com.company.logic.Game;
import com.company.items.Pants;
import com.company.items.*;

public class Player extends Creature {
	
	private static int maxSize = 20;
	private static int equipSlots = 8;
	private static int nextSlot = equipSlots;
	private int[] inventory = new int[maxSize];
	
	private int keenEye = -1;
	private int chillTouchLearned = -1;
	private int spellweaving = -1;
	private int secondWind = -1;

	public void restore() {
		setHp(getHpMax());
		setMp(getMpMax());
		if(secondWind != -1)
			setSecondWind(-2);
	}
	
	public int attack(Weapon weapon, Creature target) {
		boolean crit = false;
		double critChance = weapon.getCrit();
		boolean isSpellweaving = false;
		int roll = hit();
		int accuracy = roll + getAccuracy();
		
		System.out.println("You rolled a " + roll + ".");
		
		if(spellweaving == 1) {
			accuracy += getMod(getWisdom());
		}
		if(keenEye == 1) {
			critChance += .1;
		}
		if(accuracy >= target.getDefense()) {
			int damage = weapon.getDamage();
			damage += getMod(getStrength());
			if(roll > 20.0*(1-critChance)){
				damage *= 2;
				if(damage == 0) //prevent 0 damage crits
					damage = 1;
				crit = true;
			}
			
			if(crit) {
				System.out.println("Critical hit!");
			}
			if(isSpellweaving)
				damage += getMod(getIntelligence());
			System.out.println(getName() + " dealt " + damage + " damage!");
			if(spellweaving == 1)
				spellweaving = 0;
			return damage;
		}
		else {
			System.out.println(getName() + " missed!");
			if(spellweaving == 1)
				spellweaving = 0;
			return 0;
		}
	}
	
	public int firebolt() {
		if(getMp() >= 3) {
			int damage = (int)(Math.random() * 4)+1;
			damage += (int)(Math.random() * 4)+1;
			damage += getMod(getIntelligence())*2;
			if(spellweaving == 0)
				spellweaving = 1;
			
			System.out.println("You dealt " + damage + " damage with Firebolt!");
			setMp(getMp()-3);
			return damage;
		}
		else return 0;
	}
	
	public int chillTouch() {
		if(getMp() >= 5) {
			int damage = (int)(Math.random() * 4)+1;
			damage += getMod(getIntelligence())*1;
			if(spellweaving == 0)
				spellweaving = 1;
			
			System.out.println("You dealt " + damage + " damage with Chill Touch!");
			setMp(getMp()-5);
			return damage;
		}
		else return 0;
	}
	
	public void usePotion(int potionID) {
		
		Item potion = Item.getIndex()[potionID];
		int heal = potion.getHeal();
		if((getHp() >= getHpMax())) {
			System.out.println("Silly doge, You are already at full hp!");
			return;
		}
		if((getHp() + heal) > getHpMax()) {
			setHp(getHpMax());
		}
		else setHp(getHp() + heal);
		System.out.println("You were healed for " + heal + " hp!");
		drop(potion.getID());
		return;
	}
	
	public void levelUp() {
		
		System.out.println("``Level Up!``");
		System.out.printf("You have leveled up from %d to %d\n",getLevel(), getLevel()+1);
		
		setLevel(getLevel() + 1);
		setHpMax((int) (getHpMax() + Math.random()*8+1) + getMod(getConstitution())); // gain max health = 1d8 + conMod
		
		if(getLevel() >= 4)
			abilityScoreIncrease();
		else {
			classFeature();
		}
		
		restore();
		printStatSheet();
	}
	
	public void classFeature() {
		
		if(this.getLevel() == 2) {
			System.out.println("You have gained access to a new ability.\n1.Keen Eye 2.Chill Touch");
			do {
				while(!Game.in.hasNextInt()) {
					Game.word = Game.in.next();
					System.out.printf("%s is not a number \n",  Game.word);
				}
				Game.num = Game.in.nextInt();
				
				if(Game.num == 1) {
					//Keen Eye
					//Increases your critical strike chance
					keenEye = 0;
					break;
				}
				if(Game.num == 2) {
					//Chill Touch
					//freezes the target, stunning them for a round and dealing a small amount of damage
					setChillTouchLearned(0);
					break;
				}
			}while(true);
		}
		
		if(this.getLevel() == 3) {
			System.out.println("You have gained access to a new ability.\n 1.Second Wind 2.Spellweaving");
			do {
				while(!Game.in.hasNextInt()) {
					Game.word = Game.in.next();
					System.out.printf("%s is not a number \n",  Game.word);
				}
				Game.num = Game.in.nextInt();
				
				if(Game.num == 1) {
					//Second Wind
					//When you are brought below 25% health, you heal hp = to your level for each turn for (con mod)+1 turns.
					secondWind = -2;//state that means you have it and its ready to use but inactive
					break;
				}
				if(Game.num == 2) {
					//Spell Weaving
					//whenever you cast a spell, your next basic attack deals additional damage (int mod) and additional accuracy (wis mod)
					spellweaving = 0;//state means you have it and inactive
					break;
				}
			}while(true);
		}
	}
	
	public void abilityScoreIncrease() {
		int i=0, j;
		
		System.out.println("You have gained 2 stat points! What would you like to spend them on?");
		System.out.println("1.Strength 2.Dexterity 3.Constitution 4.Intelligence 5.Wisdom 6.Charisma");

		do {
			while(!Game.in.hasNextInt()) {
				Game.word = Game.in.next();
				System.out.printf("%s is not a number \n",  Game.word);
			}
			Game.num = Game.in.nextInt();
			
			if(Game.num == 1) {
				j = getMod(getStrength());
				setStrength(getStrength() + 1);
				if(j< getMod(getStrength()))
					setAccuracy(getAccuracy() + 1);
				i++;
			}
			if(Game.num == 2) {
				j = getMod(getDexterity());
				setDexterity(getDexterity() + 1);
				if(j< getMod(getDexterity())) {
					setAccuracy(getAccuracy() + 1);
					setDefense(getDefense() + 1);
				}
				i++;
			}
			if(Game.num == 3) {//if your constitution modifier has gone up then add hp = to your lvl
				j = getMod(getConstitution());
				setConstitution(getConstitution() + 1);
				if(j< getMod(getConstitution()))
					setHpMax(getHpMax() + getLevel());
				i++;
			}
			if(Game.num == 4) {
				setIntelligence(getIntelligence() + 1);
				i++;
			}
			if(Game.num == 5) {//if your Wisdom modifier has gone up then add mp = to your lvl
				j = getMod(getWisdom());
				setWisdom(getWisdom() + 1);
				if(j< getMod(getWisdom()))
					setMpMax(getMpMax() + getLevel()*2);
				i++;
			}
			if(Game.num == 6) {
				setCharisma(getCharisma() + 1);
				i++;
			}
		}while(i != 2);
	}

	public void equip(int itemID) {

		if(checkInventory(Item.getIndex()[itemID].getName()) != null) {
			if(Item.getIndex()[itemID].isEquipment() == true)
			{
				if(Item.getIndex()[itemID] instanceof Sword) {
					if(itemID != inventory[0]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[0]);
						inventory[0] = itemID;
						setAccuracy(getAccuracy() + ((Weapon) Item.getIndex()[itemID]).getRarity());
					}
				}
				if(Item.getIndex()[itemID] instanceof Wand) {
					if(itemID != inventory[0]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[0]);
						inventory[0] = itemID;
						setMpMax((getMpMax() + ((Weapon) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Weapon) Item.getIndex()[itemID]).getMana());
						setAccuracy(getAccuracy() + ((Weapon) Item.getIndex()[itemID]).getRarity());
					}
				}
				if(Item.getIndex()[itemID] instanceof Shield) {
					if(itemID != inventory[1]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[1]);
						inventory[1] = itemID;
						setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
						setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
						setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
						setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
					}
				}
				if(Item.getIndex()[itemID] instanceof Helm) {
					if(itemID != inventory[2]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[2]);
						inventory[2] = itemID;
						setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
						setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
						setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
						setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
					}
				}
				if(Item.getIndex()[itemID] instanceof Chest) {
					if(itemID != inventory[3]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[3]);
						inventory[3] = itemID;
						setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
						setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
						setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
						setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
					}
				}
				if(Item.getIndex()[itemID] instanceof Pants) {
					if(itemID != inventory[4]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[4]);
						inventory[4] = itemID;
						setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
						setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
						setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
						setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
					}
				}
				if(Item.getIndex()[itemID] instanceof Boots) {
					if(itemID != inventory[5]) {
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[5]);
						inventory[5] = itemID;
						setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
						setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
						setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
						setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
					}
				}
				if(Item.getIndex()[itemID] instanceof Trinket) {
					if(inventory[6] == 0) {
						if(itemID != inventory[6]) {
							drop(inventory[findItem(itemID)]);
							unEquip(inventory[6]);
							inventory[6] = itemID;
							setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
							setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
							setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
							setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
							setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
						}
					}
					else{
						drop(inventory[findItem(itemID)]);
						unEquip(inventory[7]);
						inventory[7] = itemID;
						setMpMax((getMpMax() + ((Armor) Item.getIndex()[itemID]).getMana()));
						setMp(getMp() + ((Armor) Item.getIndex()[itemID]).getMana());
						setHpMax((getHpMax() + ((Armor) Item.getIndex()[itemID]).getHealth()));
						setHp(getHp() + ((Armor) Item.getIndex()[itemID]).getHealth());
						setDefense(getDefense() + ((Armor) Item.getIndex()[itemID]).getDefense());
					}
				}
			}	
		}
	}
	
	public void unEquip(int itemID) {
		if(Item.getIndex()[itemID].isEquipment() == true)
		{
			if(Item.getIndex()[itemID] instanceof Sword) {
				inventory[0] = 0;
				setAccuracy(getAccuracy() - ((Weapon) Item.getIndex()[itemID]).getRarity());
			}
			if(Item.getIndex()[itemID] instanceof Wand) {
				inventory[0] = 0;
				setAccuracy(getAccuracy() - ((Weapon) Item.getIndex()[itemID]).getRarity());
				setMpMax((getMpMax() - ((Weapon) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Weapon) Item.getIndex()[itemID]).getMana());
			}
			if(Item.getIndex()[itemID] instanceof Shield) {
				inventory[1] = 0;
				setMpMax((getMpMax() - ((Armor) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Armor) Item.getIndex()[itemID]).getMana());
				setHpMax((getHpMax() - ((Armor) Item.getIndex()[itemID]).getHealth()));
				setHp(getHp() - ((Armor) Item.getIndex()[itemID]).getHealth());
				setDefense(getDefense() - ((Armor) Item.getIndex()[itemID]).getDefense());
			}
			if(Item.getIndex()[itemID] instanceof Helm) {
				inventory[2] = 0;
				setMpMax((getMpMax() - ((Armor) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Armor) Item.getIndex()[itemID]).getMana());
				setHpMax((getHpMax() - ((Armor) Item.getIndex()[itemID]).getHealth()));
				setHp(getHp() - ((Armor) Item.getIndex()[itemID]).getHealth());
				setDefense(getDefense() - ((Armor) Item.getIndex()[itemID]).getDefense());
			}
			if(Item.getIndex()[itemID] instanceof Chest) {
				inventory[3] = 0;
				setMpMax((getMpMax() - ((Armor) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Armor) Item.getIndex()[itemID]).getMana());
				setHpMax((getHpMax() - ((Armor) Item.getIndex()[itemID]).getHealth()));
				setHp(getHp() - ((Armor) Item.getIndex()[itemID]).getHealth());
				setDefense(getDefense() - ((Armor) Item.getIndex()[itemID]).getDefense());
			}
			if(Item.getIndex()[itemID] instanceof Pants) {
				inventory[4] = 0;
				setMpMax((getMpMax() - ((Armor) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Armor) Item.getIndex()[itemID]).getMana());
				setHpMax((getHpMax() - ((Armor) Item.getIndex()[itemID]).getHealth()));
				setHp(getHp() - ((Armor) Item.getIndex()[itemID]).getHealth());
				setDefense(getDefense() - ((Armor) Item.getIndex()[itemID]).getDefense());
			}
			if(Item.getIndex()[itemID] instanceof Boots) {
				inventory[5] = 0;
				setMpMax((getMpMax() - ((Armor) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Armor) Item.getIndex()[itemID]).getMana());
				setHpMax((getHpMax() - ((Armor) Item.getIndex()[itemID]).getHealth()));
				setHp(getHp() - ((Armor) Item.getIndex()[itemID]).getHealth());
				setDefense(getDefense() - ((Armor) Item.getIndex()[itemID]).getDefense());
			}
			if(Item.getIndex()[itemID] instanceof Trinket) {
				if(inventory[6] == itemID)
					inventory[6] = 0;
				else inventory[7] = 0;
				setMpMax((getMpMax() - ((Armor) Item.getIndex()[itemID]).getMana()));
				setMp(getMp() - ((Armor) Item.getIndex()[itemID]).getMana());
				setHpMax((getHpMax() - ((Armor) Item.getIndex()[itemID]).getHealth()));
				setHp(getHp() - ((Armor) Item.getIndex()[itemID]).getHealth());
				setDefense(getDefense() - ((Armor) Item.getIndex()[itemID]).getDefense());
			}
			pickUp(itemID);
		}
	}
	
	public void pickUp(int itemID) {
		for(int i = equipSlots; i<maxSize; i++) {
			if(inventory[i] == 0) {
				nextSlot = i;
				i = maxSize;
			}
		}
		inventory[nextSlot] = itemID;
	}
	
	public void drop(int itemID) {
		for(int i=findItem(itemID);i<maxSize-1;i++) {
			inventory[i] = inventory[i+1];
		}
	}
	
	public Item checkInventory(String name) {
		for(int i=0;i<maxSize;i++)//search for item
			if(Item.getIndex()[inventory[i]].getName() == name) {
				return Item.getIndex()[inventory[i]];
			}
		return null;
	}
	
	public int findItem(int itemID) {
		for(int i=0;i<maxSize;i++)//search for item
			if(Item.getIndex()[inventory[i]].getID() == itemID) {
				return i;
			}
		return -1;
	}
	
	public boolean isEquipped(Item randItem) {
		boolean isEquipped = false;
		for(int i=0;i<Player.getEquipSlots();i++) {
			if(randItem.getID() == inventory[i])  
				isEquipped = true;
		}
		return isEquipped;
	}
	
	public void swapItem(Item itemToReplace, Item randItem) {
		int temp = 0;
		
		for(int i=equipSlots;i<maxSize;i++)//search for item
			if(inventory[i] == randItem.getID()) {
				temp = i;
			}
		
		for(int i=equipSlots;i<maxSize;i++)//search for item
			if(inventory[i] == itemToReplace.getID()) {
				inventory[i] = randItem.getID();
			}
		
		inventory[temp] = itemToReplace.getID();
		
	}
	
	public Item viewItem(int position) {
		Item randItem = Item.getIndex()[inventory[position]];
		return randItem;
	}
	
	public Item getMainhand() {
		return Item.getIndex()[inventory[0]];
	}
	
	public Item getOffhand() {
		return Item.getIndex()[inventory[1]];
	}
	
	public Item getHelm() {
		return Item.getIndex()[inventory[2]];
	}
	
	public Item getChest() {
		return Item.getIndex()[inventory[3]];
	}
	
	public Item getPants() {
		return Item.getIndex()[inventory[4]];
	}
	
	public Item getBoots() {
		return Item.getIndex()[inventory[5]];
	}
	
	public Item getTrinket1() {
		return Item.getIndex()[inventory[6]];
	}
	
	public double getPriceMod(boolean isBuy) {
		if(isBuy)
			return 1 - (double)getMod(getCharisma())/10;
		else return 1 + (double)getMod(getCharisma())/10;
	}
	
	public static int getMaxSize() {
		return maxSize;
	}

	public static void setMaxSize(int maxSize) {
		Player.maxSize = maxSize;
	}
	
	public static int getEquipSlots() {
		return equipSlots;
	}

	public static void setEquipSlots(int equipSlots) {
		Player.equipSlots = equipSlots;
	}

	public static int getNextSlot() {
		return nextSlot;
	}

	public static void setNextSlot(int nextSlot) {
		Player.nextSlot = nextSlot;
	}
	
	public int[] getInventory() {
		return inventory;
	}
	
	public int getInventory(int i) {
		return inventory[i];
	}

	public void setInventory(int itemID, int i) {
		this.inventory[i] = itemID;
	}
	
	public void setInventory(int[] inventory) {
		for(int i=0;i<maxSize;i++){
            this.inventory[i]=inventory[i];
        }
	}
	
	public int getSpellweaving() {
		return spellweaving;
	}
	
	public void setSpellweaving(int spellweaving) {
		this.spellweaving = spellweaving;
	}
	
	public int getSecondWind() {
		return secondWind;
	}
	
	public void setSecondWind(int secondWind) {
		this.secondWind = secondWind;
	}
	
	public int getChillTouchLearned() {
		return chillTouchLearned;
	}

	public void setChillTouchLearned(int chillTouchLearned) {
		this.chillTouchLearned = chillTouchLearned;
	}
	
	public int getKeenEye() {
		return keenEye;
	}

	public void setKeenEye(int keenEye) {
		this.keenEye = keenEye;
	}
	
	public void printStatSheet() {
		healthBar();
		System.out.println("Level\t\t" + getLevel() + ": " + getExperience() + "/" + getExperienceCap());
		System.out.println("Accuracy:\t" + getAccuracy());
		System.out.println("Defense:\t" + getDefense());
		System.out.println("Strength:\t" + getStrength());
		System.out.println("Dexterity:\t" + getDexterity());
		System.out.println("Constitution:\t" + getConstitution());
		System.out.println("Intelligence:\t" + getIntelligence());
		System.out.println("Wisdom:\t\t" + getWisdom());
		System.out.println("Charisma:\t" + getCharisma());
		System.out.println("Abilities:");
		if(keenEye != -1)
			System.out.println("lvl 2: (passive) Keen Eye");
		else if(chillTouchLearned != -1)
			System.out.println("lvl 2: (spell) Chill Touch");
		else System.out.println("None");
		if(secondWind != -1)
			System.out.println("lvl 3: (passive) Second Wind");
		else if(spellweaving != -1)
			System.out.println("lvl 3: (passive) Spellweaving");
	}
	
	public void printInventory() {
		int i;
		System.out.println("Inventory:");
		System.out.println(getGold() + " dc");
		for(i=0;i<maxSize;i++) {
			if(Item.getIndex()[inventory[i]].getName() != "") {
				if(i<equipSlots)
					switch (i) {
			        	case 0:  System.out.println("Mainhand: " + Item.getIndex()[inventory[i]].getName());
			        		break;
			        	case 1:  System.out.println("Offhand: " + Item.getIndex()[inventory[i]].getName());
				    		break;
				       	case 2:  System.out.println("Helm: " + Item.getIndex()[inventory[i]].getName());
				            break;
				        case 3:  System.out.println("Chest: " + Item.getIndex()[inventory[i]].getName());
				        	break;
				        case 4:  System.out.println("Pants: " + Item.getIndex()[inventory[i]].getName());
				          	break;
				        case 5:  System.out.println("Boots: " + Item.getIndex()[inventory[i]].getName());
				        	break;
				        case 6:  System.out.println("Trinket: " + Item.getIndex()[inventory[i]].getName());
			        		break;
				        case 7:  System.out.println("Trinket: " + Item.getIndex()[inventory[i]].getName());
			        		break;
					}
				else System.out.println(i-(equipSlots-1) + " " + Item.getIndex()[inventory[i]].getName());
			}
		}	
	}
}
