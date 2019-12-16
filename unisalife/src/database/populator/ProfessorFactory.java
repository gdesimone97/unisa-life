/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import database.populator.exceptions.InvalidArgumentListException;
import exam.booklet.Saveable;
import exam.booklet.Subject;
import game.GameObjects.Professor;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author cmarino
 */
public class ProfessorFactory extends SaveableCreator {

    public ProfessorFactory() {
    }

    /**
     * 
     * @param s The string that contains every field needed to construct the ProfessorItem that should follow the pattern
     * %name%x%y%map%subject
     * The subject is required as Object, so it needs to be queried on the DB starting from the String identifier specified in the text file.
     * 
     * @throws InvalidArgumentListException 
     */
    @Override
    public Saveable create(String s) throws InvalidArgumentListException {
StringTokenizer st = new StringTokenizer(s,SaveableCreator.DELIMETER);
        try{
        
        //Retrieve subject from the DB
        Professor p = new Professor(st.nextToken(),Float.parseFloat(st.nextToken()),Float.parseFloat(st.nextToken()),st.nextToken(),new Subject(st.nextToken()));
        return p;
        
        }catch( NoSuchElementException | NumberFormatException e ){
            throw new InvalidArgumentListException();
        }
        //Insert professor in the DB
    }
    
}
