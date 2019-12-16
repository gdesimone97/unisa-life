/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import database.Database;
import database.DatabaseManager;
import database.FileNotSetException;
import database.populator.exceptions.InvalidGameDataFormatException;
import exam.booklet.Saveable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.dizitart.no2.objects.ObjectRepository;


/**
 *
 * @author cmarino
 */
public class Populator {
    
    
    
    private final String filepath;
    private DatabaseManager dbms;
    private Database db;
    
    public Populator( String filepath ) throws FileNotSetException{
        
        this.filepath = filepath;
        Database.setPath("../prova.db");
        this.db = Database.getInstance();
    }
    
    public void populate() throws FileNotFoundException, IOException, InvalidGameDataFormatException, FileNotSetException{
        
        BufferedReader r = new BufferedReader(new FileReader(filepath));
        String line = r.readLine();
        
        while(line != null ){
            
            String[] tokens = line.split(" ");
            
            if( tokens.length != 2 )
                throw new InvalidGameDataFormatException();
            
            
            String type = tokens[0].toLowerCase();
            String arguments = tokens[1];

            
            SaveableCreator s = CreatorsEnum.valueOf(type).getFactory();
            Saveable sitem = s.create(arguments);
            
            ObjectRepository repo = db.getDatabase().getRepository(s.getClass());
            repo.insert(sitem);
            
            //Get class from S and add it to the corresponding repository
            /*
            Class<Saveable> runTimeClass = sitem.getClass().asSubclass((Saveable.class)
            ObjectRepository repo = db.getDatabase().getRepository(sitem.getClass());
            repo.insert((runTimeClass)sitem);
            */
            
            line = r.readLine();
            
        }
        
    }
    
    public String getPath(){
        return this.filepath;
    }
    
    
    
    
}
