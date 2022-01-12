package com.company.savestate;

import com.company.entities.Creature;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


 
/**
 *
 * @author Valkor
 */
public class PlayerSave extends Creature {
    
	private final static int maxSpace = 56;
	private static int equipSlots = 8;
	private static int currentSlot = equipSlots;
	private int[] inventory = new int[maxSpace];


    public void loadData(){
        try{
            boolean startInventory = false;
            Scanner fileIn = new Scanner(new File(super.getName() + ".txt"));
            fileIn.useDelimiter(" |=|\\n");
            while (startInventory == false){
                String type = fileIn.next();
                
                if (type.substring(0,type.length()-1).equals("INVENTORY")){
                    startInventory = true; 
                }else{
                    String tempString = fileIn.next();
                    while(tempString.equals("")){
                        tempString = fileIn.next();
                    }
                    int value = Integer.parseInt(tempString.substring(0,tempString.length()-1));
          
                    switch(type){
                        case "hp":setHp(value);break;
                        case "hpMax":setHpMax(value);break;
                        case "mp":setMp(value);break;
                        case "mpMax":setMpMax(value);break;
                        case "strength":setStrength(value);break;
                        case "dexterity":setDexterity(value);break;
                        case "constitution":setConstitution(value);break;
                        case "intelligence":setIntelligence(value);break;
                        case "wisdom":setWisdom(value);break;
                        case "charisma":setCharisma(value);break;
                        case "accuracy":setAccuracy(value);break;
                        case "defense":setDefense(value);break;
                        case "level":setLevel(value);break;
                        case "experience":setExperience(value);break;
                        case "experienceCap":setExperienceCap(value);break;
                        case "gold":setGold(value);break;
                        //MORE VALUES HERE!! --Valkor
                        default:System.out.println("WARNING: save file may be corrupt");
                    }
                }
            }
            
            for(int i = 0; i<maxSpace; i++){
                String tempString = fileIn.next();
                int value = Integer.parseInt(tempString.substring(0,tempString.length()-1));
                inventory[i] = value;
            }
        }catch(IOException e){
            System.out.println("Failed to load data.");
        }
    }
    
    public void saveData(){
        try{
            PrintWriter outputFile = new PrintWriter(super.getName() + ".txt", "UTF-8");
            //outputFile.println("name = " + super.getName());
            outputFile.println("hp = " + super.getHp());
            outputFile.println("hpMax = " + super.getHpMax());
            outputFile.println("mp = " + super.getMp());
            outputFile.println("mpMax = " + super.getMpMax());
            outputFile.println("strength = " + super.getStrength());
            outputFile.println("dexterity = " + super.getDexterity());
            outputFile.println("constitution = " + super.getConstitution());
            outputFile.println("intelligence = " + super.getIntelligence());
            outputFile.println("wisdom = " + super.getWisdom());
            outputFile.println("charisma = " + super.getCharisma());
            outputFile.println("accuracy = " + super.getConstitution());
            outputFile.println("defense = " + super.getDefense());
            outputFile.println("level = " + super.getLevel());
            outputFile.println("experience = " + super.getExperience());
            outputFile.println("experienceCap = " + super.getExperienceCap());
            outputFile.println("gold = " + super.getGold());
            //MORE VALUES HERE!! --Valkor
            outputFile.println("INVENTORY");
            for (int i = 0; i<maxSpace; i++){
                outputFile.println(inventory[i]);
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
