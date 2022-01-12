package com.company.savestate;

/**
 *
 * @author Valkor
 */
public class Saveload {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PlayerSave a = new PlayerSave();
        a.loadData();
        System.out.println(a.toString());
        a.saveData();
    }
    
}
