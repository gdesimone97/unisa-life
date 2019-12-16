/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import database.populator.exceptions.InvalidGameDataFormatException;
import exam.booklet.Saveable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author cmarino
 */
public class Populator {
    
    
    
    private final String filepath;
    
    
    public Populator( String filepath ){
        
        this.filepath = filepath;
        
    }
    
    public void populate() throws FileNotFoundException, IOException, InvalidGameDataFormatException{
        
        BufferedReader r = new BufferedReader(new FileReader(filepath));
        String line = r.readLine();
        
        
        while(line != null ){
            
            String[] tokens = line.split(" ");
            if( tokens.length != 2 )
                throw new InvalidGameDataFormatException();
            
            String type = tokens[0].toLowerCase();
            String arguments = tokens[1];
            
            Saveable s = CreatorsEnum.valueOf(type).getFactory().create(arguments);
            
            //Get class from S and add it to the corresponding repository
            
            System.out.println(s);
            
        }
        
    }
    
    
    
    
}
