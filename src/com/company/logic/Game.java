package com.company.logic;
import com.company.entities.Enemy;
import com.company.entities.Player;
import com.company.items.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * @author CEN3031 Group 7 MW 13-14:15
 * @since 2018-10-1
 * @version 1.0
 */
public class Game {

    public static Scanner in = new Scanner(System.in);
    public static int num = 0;
    public static String word;

    /**
     * 
     * @param args 
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        MiscItem empty = new MiscItem("");
        Enemy none = new Enemy("");
        for(int i=0;i<100;i++){//init item index
            Item.getIndex()[i] = empty;
        }
        for(int i=0;i<100;i++){//init enemy index
            Enemy.getIndex()[i] = none;
        }
        Player player1 = new Player();

        //init items
        Sword woodenSword = new Sword("Wooden Sword");
        Sword copperSword = new Sword("Copper Sword");
        Sword bronzeSword = new Sword("Bronze Sword");
        Sword ironSword = new Sword("Iron Sword");
        Sword steelSword = new Sword("Steel Sword");
        Sword mithrilSword = new Sword("Mithril Sword");

        Wand woodenWand = new Wand("Wooden Wand");
        Wand copperWand = new Wand("Copper Wand");
        Wand silverWand = new Wand("Silver Wand");
        Wand goldWand = new Wand("Gold Wand");
        Wand platinumWand = new Wand("Platinum Wand");
        Wand orichalcumWand = new Wand("Orichalcum Wand");

        Shield woodenShield = new Shield("Wooden Shield");

        Potion potionOfHealing = new Potion("Potion of Healing");
        Potion dogTreat = new Potion("Dog Treat");
        Helm coolHat = new Helm("Cool Hat");
        Trinket woodenRing = new Trinket("Wooden Ring");

        Sword godHand = new Sword("Pimp Hand");
        godHand.setRarity(100);

        //init enemies
        Enemy goblin = new Enemy("goblin");
        Enemy wolf = new Enemy("wolf");
        Enemy bear = new Enemy("bear");
        Enemy ogre = new Enemy("ogre");

        //starting items
        player1.pickUp(Item.checkIndex("Wooden Sword").getID());
        player1.equip(Item.checkIndex("Wooden Sword").getID());
        player1.pickUp(Item.checkIndex("Wooden Wand").getID());
        player1.pickUp(potionOfHealing.getID());
        player1.setGold(10);

        if(false){//loadData(player1) == 1) {
            System.out.println("You wake up in your bed at the inn and head out into town.");
        	Event.town(player1);
        }
        else {
        System.out.println("Welcome " + player1.getName() + " To Super Pupper Adventure!");
        System.out.println("You are a proud doge that has come from distant lands in search of adventure.");

        Event.pointBuy(player1);

        System.out.println("You find yourself exploring a forest.");
        Event.woods(player1);
        }
        
        in.close();
    }
    
    public static int loadData(Player player1){
        try{
            Scanner fileIn = new Scanner(new File(player1.getName() + ".txt"));
            fileIn.useDelimiter(" |=|\n");
            while (fileIn.hasNext()){
                String type = fileIn.next();
                System.out.println(type);
                String tempString = fileIn.next();
                while(tempString.equals("")){
                    tempString = fileIn.next();
                }
                int value = Integer.parseInt(tempString.substring(0,tempString.length()-1));
             
                switch(type){
                    case "hp":player1.setHp(value);break;
                    case "hpMax":player1.setHpMax(value);break;
                    case "mp":player1.setMp(value);break;
                    case "mpMax":player1.setMpMax(value);break;
                    case "strength":player1.setStrength(value);break;
                    case "dexterity":player1.setDexterity(value);break;
                    case "constitution":player1.setConstitution(value);break;
                    case "intelligence":player1.setIntelligence(value);break;
                    case "wisdom":player1.setWisdom(value);break;
                    case "charisma":player1.setCharisma(value);break;
                    case "accuracy":player1.setAccuracy(value);break;
                    case "defense":player1.setDefense(value);break;
                    case "level":player1.setLevel(value);break;
                    case "experience":player1.setExperience(value);break;
                    case "experienceCap":player1.setExperienceCap(value);break;
                    case "gold":player1.setGold(value);break;
                    case "keenEye":player1.setKeenEye(value);break;
                    case "chillTouchLearned":player1.setChillTouchLearned(value);break;
                    case "spellweaving":player1.setSpellweaving(value);break;
                    case "secondWind":player1.setSecondWind(value);break;
                    case "chestAquired":Event.setChestAquired(value);break;
                    case "townQuest":Event.setTownQuest(value);break;
                    
                    default:System.out.println("WARNING: save file may be corrupt");
                }
            }
            
            for(int i = 0; i<Player.getMaxSize(); i++){
                String tempString = fileIn.next();
                int value = Integer.parseInt(tempString.substring(0,tempString.length()-1));
                player1.getInventory()[i] = value;
            }

            fileIn.close();
            return 1;
            
        }catch(IOException e){
            System.out.println("Failed to load data.");
        	return 0;
        }
    }
    
    public static void saveData(Player player1){
        try{
            PrintWriter outputFile = new PrintWriter(player1.getName() + ".txt", "UTF-8");
            outputFile.println("hp = " + player1.getHp());
            outputFile.println("hpMax = " + player1.getHpMax());
            outputFile.println("mp = " + player1.getMp());
            outputFile.println("mpMax = " + player1.getMpMax());
            outputFile.println("strength = " + player1.getStrength());
            outputFile.println("dexterity = " + player1.getDexterity());
            outputFile.println("constitution = " + player1.getConstitution());
            outputFile.println("intelligence = " + player1.getIntelligence());
            outputFile.println("wisdom = " + player1.getWisdom());
            outputFile.println("charisma = " + player1.getCharisma());
            outputFile.println("accuracy = " + player1.getConstitution());
            outputFile.println("defense = " + player1.getDefense());
            outputFile.println("level = " + player1.getLevel());
            outputFile.println("experience = " + player1.getExperience());
            outputFile.println("experienceCap = " + player1.getExperienceCap());
            outputFile.println("gold = " + player1.getGold());
	        outputFile.println("keenEye = " + player1.getKeenEye());
	        outputFile.println("chillTouchLearned = " + player1.getChillTouchLearned());
	        outputFile.println("spellweaving = " + player1.getSpellweaving());
	        outputFile.println("secondWind = " + player1.getSecondWind());
	        outputFile.println("chestAquired = " + Event.getChestAquired());
	        outputFile.println("townQuest = " + Event.getTownQuest());
	        outputFile.println("INVENTORY");
            for (int i = 0; i<Player.getMaxSize(); i++){
                outputFile.println(player1.getInventory()[i]);
            }
            outputFile.close();
        }catch(IOException e){
            System.out.println("Failed to save data.");
        }
        
    }

    @Override
    public String toString() {
        return super.toString() + " -> Player{" + '}';
    }

}