/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import org.dizitart.no2.Nitrite;


/**
 *
 * @author alfon
 */
public class Database {
    
    private static String path;
    private Nitrite db;
    private static Database instance;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Database.path = path;
    }
    
    private Database(){
        this.db =  Nitrite.builder().compressed().filePath(Database.getPath()).openOrCreate("group08", "hntbae");
    }
    
    public static Database getInstance(){
        if(Database.instance==null)
            Database.instance = new Database();
        return instance;
    }
    
    public Nitrite getDatabase(){
        return this.db;
    }
    
}
