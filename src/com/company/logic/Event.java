package com.company.logic;

import com.company.entities.Enemy;
import com.company.entities.Player;
import com.company.items.Armor;
import com.company.items.Item;
import com.company.items.Potion;
import com.company.items.Weapon;

public class Event {
	private static int chestAquired = 0;//boolean for whether the chest has been found
	private static int townQuest = 0;//boolean for whether or not the town quest has been completed
	private static int price = 0;//a placeholder for price
	private static int temp;//a placeholder variable
	
	public static void pickClass(Player player1) {
		System.out.println("Select your class.");
		System.out.println("1.Warrior 2.Mage");
		do {
			while(!Game.in.hasNextInt()) {
				Game.word = Game.in.next();
				System.out.println("Invalid input. Please enter a number.");
			}
			Game.num = Game.in.nextInt();
			
			if(Game.num == 1) {
				player1.pickUp(Item.checkIndex("Wooden Sword").getID());
				player1.equip(Item.checkIndex("Wooden Sword").getID());
				player1.pickUp(Item.checkIndex("Wooden Wand").getID());
			}
			else if(Game.num == 2) {
				player1.pickUp(Item.checkIndex("Wooden Wand").getID());
				player1.equip(Item.checkIndex("Wooden Wand").getID());
			}
				
		}while(Game.num != 1 && Game.num != 2);
	}
	
	public static void pointBuy(Player player1) {
		int i = 0, stat = 0;
		boolean[] picked = new boolean[] {false, false, false, false, false, false};
		
		System.out.println("Input 0 to autofill stats.");
		do {
		
			if(i == 0) {
				System.out.println("Pick your favored attribute.");
				stat = 15;
			}
			else if(i == 1) {
				System.out.println("Pick your second attribute.");
				stat = 14;
			}
			else if(i == 2) {
				System.out.println("Pick your third attribute.");
				stat = 13;
			}
			else if(i == 3) {
				System.out.println("Pick your fourth attribute.");
				stat = 12;
			}
			else if(i == 4) {
				System.out.println("Pick your fifth attribute.");
				stat = 10;
			}
			else if(i == 5) {
				System.out.println("Pick your weakest attribute.");
				stat = 8;
			}
			
			if(picked[0] == false)
				System.out.print("1.Strength ");
			if(picked[1] == false)
				System.out.print("2.Dexterity ");
			if(picked[2] == false)
				System.out.print("3.Constitution ");
			if(picked[3] == false)
				System.out.print("4.Intelligence ");
			if(picked[4] == false)
				System.out.print("5.Wisdom ");
			if(picked[5] == false)
				System.out.print("6.Charisma");
			
			System.out.println();
			
			while(!Game.in.hasNextInt()) {
				Game.word = Game.in.next();
				System.out.printf("%s is not a number \n",  Game.word);
			}
			Game.num = Game.in.nextInt();
			
			if(Game.num == 0) {//autoset
				player1.setStrength(15);
				player1.setAccuracy(player1.getAccuracy() + player1.getMod(15));
				player1.setDexterity(14);
				player1.setAccuracy(player1.getAccuracy() + player1.getMod(14));
				player1.setDefense(player1.getDefense() + player1.getMod(14));
				player1.setConstitution(13);
				player1.setHpMax(player1.getHpMax() + player1.getMod(13));
				player1.setIntelligence(12);
				player1.setWisdom(10);
				player1.setMpMax(player1.getMpMax() + player1.getMod(10)*2);
				player1.setCharisma(8);
				i = 6;
			}
			
			if(Game.num == 1) {
				player1.setStrength(stat);
				player1.setAccuracy(player1.getAccuracy() + player1.getMod(stat));
				i++;
				picked[Game.num-1] = true;
			}
			else if(Game.num == 2) {
				player1.setDexterity(stat);
				player1.setAccuracy(player1.getAccuracy() + player1.getMod(stat));
				player1.setDefense(player1.getDefense() + player1.getMod(stat));
				i++;
				picked[Game.num-1] = true;
			}
			else if(Game.num == 3) {//if your constitution modifier has gone up then add hp = to your lvl
				player1.setConstitution(stat);
				player1.setHpMax(player1.getHpMax() + player1.getMod(stat));
				i++;
				picked[Game.num-1] = true;
			}
			else if(Game.num == 4) {
				player1.setIntelligence(stat);
				i++;
				picked[Game.num-1] = true;
			}
			else if(Game.num == 5) {//if your Wisdom modifier has gone up then add mp = to your lvl
				player1.setWisdom(stat);
				player1.setMpMax(player1.getMpMax() + player1.getMod(stat)*2);
				i++;
				picked[Game.num-1] = true;
			}
			else if(Game.num == 6) {
				player1.setCharisma(stat);
				i++;
				picked[Game.num-1] = true;
			}
		}while(i != 6);
		player1.restore();
	}
	
	public static void manageInventory(Player player1) {
		int temp;
		do {
			player1.printInventory();
			System.out.println("0.Exit");
			while(!Game.in.hasNextInt()) {
				Game.word = Game.in.next();
				System.out.println("Invalid input. Please enter a number.");
			}
			Game.num = Game.in.nextInt();
			
			if(Game.num != 0) {
				Game.num+=Player.getEquipSlots()-1;
				temp = Game.num;
				
				if(player1.viewItem(Game.num).isEquipment() == true)
				{
					if(player1.viewItem(Game.num) instanceof Weapon)
						Item.printToolTip(player1.viewItem(Game.num));
					if(player1.viewItem(Game.num) instanceof Armor)
						Armor.printToolTip(player1.viewItem(Game.num));
					System.out.println("Equip Item?(1/0)");
					
					while(!Game.in.hasNextInt()) {
						Game.word = Game.in.next();
						System.out.println("Invalid input. Please enter a number.");
					}
					Game.num = Game.in.nextInt();
					
					if(Game.num == 1)
						player1.equip(player1.getInventory()[temp]);	
				}
				else if(player1.viewItem(Game.num).isUsable() == true){
					Item.printToolTip(player1.viewItem(Game.num));
					System.out.println("Use Item?(1/0)");
					
					while(!Game.in.hasNextInt()) {
						Game.word = Game.in.next();
						System.out.println("Invalid input. Please enter a number.");
					}
					Game.num = Game.in.nextInt();
					
					if(Game.num == 1) {
						if(Item.getIndex()[player1.getInventory()[temp]] instanceof Potion) {
							player1.usePotion(player1.getInventory()[temp]);
						}
					}
				}	
			}
				
		}while(Game.num != 0);
	}
	
	public static void startCombat(Player player1, Enemy enemy1) {
		int stunned = 0;
		
		do {
			do {

				player1.healthBar();
				System.out.print("1.Attack 2.Firebolt(3mp) ");
				if(player1.getChillTouchLearned() == 0)
					System.out.print("3.Chill Touch(5mp) ");
				System.out.println("4.Potion 5.Run");
				
				while(!Game.in.hasNextInt()) {
					Game.word = Game.in.next();
					System.out.println("Invalid input. Please enter a number.");
				}
				Game.num = Game.in.nextInt();
				
				if(Game.num == 1) {
					System.out.println("You attacked with your " + Item.getIndex()[player1.getInventory()[0]].getName() + "!");
					enemy1.setHp(enemy1.getHp()-player1.attack((Weapon)Item.getIndex()[player1.getInventory()[0]], enemy1));
					enemy1.healthBar();
					}
				
				else if(Game.num == 2) {
					if(player1.getMp() >= 3) {
						System.out.println("You cast firebolt.");
						enemy1.setHp(enemy1.getHp() - player1.firebolt());
						enemy1.healthBar();
					}
					else{
						System.out.println("You do not have enough mp!");
						Game.num=0;
					}
				}
				
				else if(Game.num == 3) {
					if(player1.getChillTouchLearned() == -1)
						Game.num=0;
					else if(player1.getMp() >= 5) {
						System.out.println("You cast Chill Touch.");
						enemy1.setHp(enemy1.getHp() - player1.chillTouch());
						enemy1.healthBar();
						stunned = 1;
					}
					else{
						System.out.println("You do not have enough mp!");
						Game.num=0;
					}
				}
				
				else if(Game.num == 4) {
					if(player1.checkInventory("Potion of Healing") != null) {
						player1.usePotion(Item.checkIndex("Potion of Healing").getID());
						player1.healthBar();
					}
					else{
						System.out.println("You do not have any potions!");
						Game.num=0;
					}
				}
				
				else if(Game.num == 5) {
					System.out.println("You run away with your tail tucked between your legs!");
					return;
				}
			}while(Game.num == 0);
	
			if(enemy1.getHp() <= 0)
				break;

			//take damage
			if(stunned == 0) {
				player1.setHp(player1.getHp() - enemy1.attack((Weapon)enemy1.getWeapon(), player1));
				
				if(player1.getHp() <= 0) {
					System.out.println("You have died!");
					System.exit(0);
				}
			}
			else {
				stunned--;
				System.out.println(enemy1.getName() + " is frozen!");
			}
			
			if(player1.getSecondWind() == -2 && player1.getHp() <= (double)player1.getHpMax()/4) {
				player1.setSecondWind(player1.getMod(player1.getConstitution())+1);
			}
			if(player1.getSecondWind() > 0) {
				player1.setHp(player1.getHp() + player1.getLevel());
				player1.setSecondWind(player1.getSecondWind() - 1);
				System.out.println("Second Wind has healed you for " + player1.getLevel() + " hp!");
			}
			
		}while(enemy1.getHp() > 0 && player1.getHp() > 0);
		
		if(enemy1.getHp() <= 0) {
			System.out.println("You have slain the " + enemy1.getName());
			if(player1.setExperience(player1.getExperience() + enemy1.getExperience()) == true)
				player1.levelUp();
			player1.setGold(player1.getGold() + (enemy1.getGold() * (int)Math.random() + 1));
			return;
		}
		return;
	}
	
	public static void randEncounter(Player player1, int i) {
		int route = (int) (Math.random() * 12 + 1);
		
		switch (route) {
		case 1: case 2: case 3:	case 4: case 5: case 6:
			System.out.println("As you walk under the trees, you hear some rustling from the bushes.");
			System.out.println("A goblin jumps out to attack you!");
			Enemy.getIndex()[2].restore();
			startCombat(player1, Enemy.getIndex()[2]);
			break;
					
		case 7: case 8: case 9:
			System.out.println("As you walk under the trees, you hear howling off in the distance.");
			System.out.println("A lone wolf lunges at you!");
			Enemy.getIndex()[3].restore();
			startCombat(player1, Enemy.getIndex()[3]);
			break;
			
		case 10:		
			System.out.println("As you walk under the trees, the ground shakes.");
			System.out.println("A huge bear blocks your path!");
			Enemy.getIndex()[4].restore();
			startCombat(player1, Enemy.getIndex()[4]);
			break;
			
		case 11:		
			System.out.println("As you walk under the trees, All is quiet...");
			
			if(i>=6 && townQuest == 0) {
				System.out.println("As you walk under the trees, you hear some rustling from the bushes.");
				System.out.println("A squirrel pops out and stops in front of you.");
				System.out.println("It seems like it wants to show you something.");
				System.out.println("Follow it?(1/0).");
				
				while(!Game.in.hasNextInt()) {
					Game.word = Game.in.next();
					System.out.println("Invalid input. Please enter a number.");
				}
				Game.num = Game.in.nextInt();
				
				if(Game.num == 1) {
					System.out.println("You chase it through the trees. It occasionally checks to make sure you are still following.");
					System.out.println("It leads you to a dark cave...");
					System.out.println("As you enter, you hear screaming. You light a torch and hurry inside.");
					System.out.println("There, in the shadows, a massive ogre!");
					Enemy.getIndex()[5].restore();
					startCombat(player1, Enemy.getIndex()[5]);
					System.out.println("The ogre lies dead on the ground, next to a figure, tied and squirming.");
					System.out.println("You untie them and bring them back to the villiage...");
					townQuest = 1;
					town(player1);
					
				}
				else {
					System.out.println("You stare it down until it gets scared and runs off.");
					System.out.println("You've never trusted squirrels.");
				}
			}
			break;
		
		case 12:
			if(i>=3 && chestAquired == 0) {
				System.out.println("As you walk under the trees, you find an abandonded building.");
				System.out.println("Upon inspection, there is a trunk filled with Doge Coins!");
				player1.setGold(player1.getGold() + (int)(Math.random()*5+1) + (int)(Math.random()*5+1));
				player1.setGold(player1.getGold() + (int)(Math.random()*5+1) + (int)(Math.random()*5+1));
				player1.setGold(player1.getGold() + (int)(Math.random()*5+1) + (int)(Math.random()*5+1));
				player1.pickUp(Item.checkIndex("Wooden Ring").getID());
				chestAquired = 1;
			}
			else {
				System.out.println("As you walk under the trees, you see something glimmering from inside the trunk of a tree.");
				System.out.println("Treasure, stashed inside!");
				player1.setGold(player1.getGold() + (int)(Math.random()*10+1));
				player1.pickUp(Item.checkIndex("Potion of Healing").getID());
			}
			break;
		}
		return;
	}
	
	public static int woods(Player player1) {
		int i = 0;
		
		do {
			System.out.print("1.Wander 2.Inventory 3.Status 4.Return to Town");
			
			while(!Game.in.hasNextInt()) {
				Game.word = Game.in.next();
				System.out.println("Invalid input. Please enter a number.");
			}
			Game.num = Game.in.nextInt();

			if(Game.num == 1) {
				System.out.println("Which type of encounter would you like?");
				System.out.println("1.Random 2.Goblin 3.Wolf 4.Bear 5.Ogre");
				Game.num = Game.in.nextInt();
				if(Game.num == 1)
				{
				randEncounter(player1, i);
				i++;
				}
				else if(Game.num == 2)
				{
					System.out.println("As you walk under the trees, you hear some rustling from the bushes.");
					System.out.println("A goblin jumps out to attack you!");
					Enemy.getIndex()[2].restore();
					startCombat(player1, Enemy.getIndex()[2]);
				}
				else if(Game.num == 3)
				{
					System.out.println("As you walk under the trees, you hear howling off in the distance.");
					System.out.println("A lone wolf lunges at you!");
					Enemy.getIndex()[3].restore();
					startCombat(player1, Enemy.getIndex()[3]);
				}
				else if(Game.num == 4)
				{
					System.out.println("As you walk under the trees, the ground shakes.");
					System.out.println("A huge bear blocks your path!");
					Enemy.getIndex()[4].restore();
					startCombat(player1, Enemy.getIndex()[4]);
				}
				else if(Game.num == 5)
				{
					System.out.println("As you walk under the trees, you hear boulders crashing.");
					System.out.println("An ogre stomps towards you!");
					Enemy.getIndex()[5].restore();
					startCombat(player1, Enemy.getIndex()[5]);
				}
			}
			else if(Game.num == 2) {
				manageInventory(player1);
			}
			else if(Game.num == 3) {
				player1.printStatSheet();
			}
			else if(Game.num == 4) {
				System.out.println("You leave the forest and find a dirt road that leads up to a town.");
				town(player1);
			}
		}while(true);
	}
	
	public static void town(Player player1) {
		do {
			do {
				System.out.println("1.Inn 2.Shop 3.Blacksmith 4.Elder 5.Leave");
				
				while(!Game.in.hasNextInt()) {
					Game.word = Game.in.next();
					System.out.println("Invalid input. Please enter a number.");
				}
				Game.num = Game.in.nextInt();
				
				//inn
				if(Game.num == 1) {
					price = (int) (2*player1.getPriceMod(true));
					System.out.println("You enter the inn and see a cat lying on the desk.");
					System.out.println("She yawns and asks if you would like to stay the night.");
					do {
						System.out.println("1. Rest("+price+"dc) 2.Leave");
						
						while(!Game.in.hasNextInt()) {
							Game.word = Game.in.next();
							System.out.println("Invalid input. Please enter a number.");
						}
						Game.num = Game.in.nextInt();
					}while(Game.num != 1 && Game.num != 2);
					
					if(Game.num == 1) {
						if(player1.getGold() >= price) {
							player1.setGold(player1.getGold() - price);
							System.out.println("You curl up on your bed and sleep until morning.");
							System.out.println("Your game has been saved.");
							player1.restore();
							Game.saveData(player1);
						}
						else System.out.println("You dont't have enough coins for that.");
					}
				}
				//shop
				else if(Game.num == 2) {
					System.out.println("A fluffy white pupper is waiting eagerly behind the counter, tail wagging.");
					System.out.println("He greets you as you walk in and suggests you have a look around.");
					do {
						System.out.println("1. Potion of Healing 2. Cool hat 3. Dog treat 4.Sell 5.Leave");
						
						while(!Game.in.hasNextInt()) {
							Game.word = Game.in.next();
							System.out.println("Invalid input. Please enter a number.");
						}
						Game.num = Game.in.nextInt();
						
						if(Game.num == 1) {
							price = (int) (10*player1.getPriceMod(true));
							System.out.println("The pupper has followed you over and asks if you would like to buy.(1/0)");
							System.out.println("Supposedly it can heal you in a pinch. (" + price + "dc)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You tuck the vial into your bag.");
									player1.pickUp(Item.checkIndex("Potion of Healing").getID());
								}
								else {
									System.out.println("You dont't have enough gold for that.");
									System.out.println("The pupper looks sad.");
								}
							}
						}
						
						else if(Game.num == 2) {
							price = (int) (20*player1.getPriceMod(true));
							System.out.println("The pupper has followed you over and asks if you would like to buy.(1/0)");
							System.out.println("He thinks you would look cool in this hat. (" + price + "dc)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You put the hat in your bag.");
									player1.pickUp(Item.checkIndex("Cool Hat").getID());
								}
								else {
									System.out.println("You dont't have enough coins for that.");
									System.out.println("The pupper looks sad.");
								}
							}
						}
						
						else if(Game.num == 3) {
							price = (int) (2*player1.getPriceMod(true));
							System.out.println("The pupper has followed you over and asks if you would like to buy.(1/0)");
							System.out.println("He licks his lips looking at the tastey treats. (" + price + "g)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You put the treat in your bag");
									player1.pickUp(Item.checkIndex("Dog Treat").getID());
								}
								else {
									System.out.println("You dont't have enough coins for that.");
									System.out.println("The pupper looks sad.");
								}
							}
							else Game.num = 1;
						}
					
						else if(Game.num == 4) {
							System.out.println("The pupper looks excited.");
							System.out.println("You open your bag and show him what you have.");
							
							do {
								player1.printInventory();
								System.out.println("0.Exit");
								
								while(!Game.in.hasNextInt()) {
									Game.word = Game.in.next();
									System.out.println("Invalid input. Please enter a number.");
								}
								Game.num = Game.in.nextInt();
								
								if(Game.num != 0) {
									Game.num+=Player.getEquipSlots()-1;
									temp = Game.num;
									
									System.out.println("Sell Item?(1/0)");
									
									while(!Game.in.hasNextInt()) {
										Game.word = Game.in.next();
										System.out.println("Invalid input. Please enter a number.");
									}
									Game.num = Game.in.nextInt();
									
									if(Game.num == 1) {
										player1.setGold((int) (player1.getGold() + Item.getIndex()[player1.getInventory()[temp]].getValue()*player1.getPriceMod(false)));
										player1.drop(player1.getInventory()[temp]);
									}
								}
									
							}while(Game.num != 0);
						}
					}while(Game.num != 5);
				}
				//blacksmith
				else if(Game.num == 3) {
					
					System.out.println("As you enter the blacksmith's, you are greeted by a Sheep behind the counter.");
					System.out.println("You can see the glowing furnace in the back room.");
					do {
						System.out.println("1. Copper Sword 2. Bronze Sword 3. Copper Wand 4. Silver Wand 5.Leave");
						
						while(!Game.in.hasNextInt()) {
							Game.word = Game.in.next();
							System.out.println("Invalid input. Please enter a number.");
						}
						Game.num = Game.in.nextInt();
						
						if(Game.num == 1) {
							price = (int) (20*player1.getPriceMod(true));
							System.out.println("You bring the item up to the clerk and she asks if you would like to buy.(1/0)");
							System.out.println("This copper sword was cast and ground by the resident smith. (" + price + "dc)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You put the sword in your pack.");
									player1.pickUp(Item.checkIndex("Copper Sword").getID());
								}
								else {
									System.out.println("You dont't have enough coins for that.");
									System.out.println("The clerk looks unimpressed.");
								}
	
							}
						}
						else if(Game.num == 2) {
							price = (int) (100*player1.getPriceMod(true));
							System.out.println("You bring the item up to the clerk and she asks if you would like to buy.(1/0)");
							System.out.println("This bronze sword was cast and ground by the resident smith. (" + price + "dc)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You put the sword in your pack.");
									player1.pickUp(Item.checkIndex("Bronze Sword").getID());
								}
								else {
									System.out.println("You dont't have enough coins for that.");
									System.out.println("The clerk looks unimpressed.");
								}
	
							}
						}
						else if(Game.num == 3) {
							price = (int) (20*player1.getPriceMod(true));
							System.out.println("You bring the item up to the clerk and she asks if you would like to buy.(1/0)");
							System.out.println("This copper wand has a faint shimmer around it. (" + price + "dc)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You put the wand in your pack.");
									player1.pickUp(Item.checkIndex("Copper Wand").getID());
								}
								else {
									System.out.println("You dont't have enough coins for that.");
									System.out.println("The clerk looks unimpressed.");
								}
	
							}
						}
						else if(Game.num == 4) {
							price = (int) (100*player1.getPriceMod(true));
							System.out.println("You bring the item up to the clerk and she asks if you would like to buy.(1/0)");
							System.out.println("This silver wand has a faint shimmer around it. (" + price +"dc)");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								if(player1.getGold() >= price) {
									player1.setGold(player1.getGold() - price);
									System.out.println("You put the wand in your pack.");
									player1.pickUp(Item.checkIndex("Silver Wand").getID());
								}
								else {
									System.out.println("You dont't have enough coins for that.");
									System.out.println("The clerk looks unimpressed.");
								}
	
							}
						}
					}while(Game.num != 0 && Game.num != 5);
				}
				//elder
				else if(Game.num == 4) {
					
					System.out.println("You make your way to a house sitting at the edge of town on a hill.");
					System.out.println("An old tortoise sits in a rocker on the porch, smoking a pipe.");
					price = (int) (50*player1.getPriceMod(false));
					do {
						
						if(townQuest == 0) {
							temp = 3;
							System.out.println("1.Work? 2.Talk 3.Leave");
							
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								
								System.out.println("The elder tells you one of the villiagers has recently gone missing in the forest.");
								System.out.println("He offers a reward of " + price + "dc if you bring them back safely.");
							}
							if(Game.num == 2) {
								System.out.println("You sit down with the elder and talk for a while.");
								System.out.println("He says the surrounding forest has become very dangerous recently.");
								System.out.println("He feels a bit safer with you in town.");
							}
							
						}
						if(townQuest == 1) {
							System.out.println("You tell the elder you found the missing person in a cave, held by an ogre.");
							System.out.println("He is very thankful and rewards you " + price + "dc.");
							player1.setGold(player1.getGold() + price);
							player1.setExperience(player1.getExperience() + 250);
							townQuest = -1;
							
						}
						if(townQuest == -1) {
							temp = 2;
							System.out.println("1.Talk 2.Leave");
		
							while(!Game.in.hasNextInt()) {
								Game.word = Game.in.next();
								System.out.println("Invalid input. Please enter a number.");
							}
							Game.num = Game.in.nextInt();
							
							if(Game.num == 1) {
								System.out.println("You sit down with the elder and talk for a while.");
								System.out.println("He mentions how he once met an adventurer like you when he was a boy.");
								System.out.println("Maybe this was an ancient ancestor of yours.");
							}
						}
						
					}while(Game.num != 0 && Game.num != temp);
				}
				//leave
				else if(Game.num == 5) {
					System.out.println("You leave town and head back into the forest.");
					if(woods(player1) == 0)
						return;
				}
			}while(true);
		}while(true);
	}
	
	public static int getChestAquired() {
		return chestAquired;
	}

	public static void setChestAquired(int chestAquired) {
		Event.chestAquired = chestAquired;
	}

	public static int getTownQuest() {
		return townQuest;
	}

	public static void setTownQuest(int townQuest) {
		Event.townQuest = townQuest;
	}
}
