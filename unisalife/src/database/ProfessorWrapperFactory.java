/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import saving.Saveable;
import exam.booklet.Subject;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author cmarino
 */
public class ProfessorWrapperFactory extends StorableCreator {

    public ProfessorWrapperFactory() {
    }

    /**
     *
     * @param s The string that contains every field needed to construct the
     * ProfessorItem that should follow the pattern %name%x%y%map%subject The
     * subject is required as Object, so it needs to be queried on the DB
     * starting from the String identifier specified in the text file.
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) throws InvalidArgumentListException {
        
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        
        try {

            //Retrieve subject from the DB
            ProfessorWrapper pw = new ProfessorWrapper(st.nextToken(), new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken(), new Subject(st.nextToken()));
            return pw;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
        //Insert professor in the DB
    }

}
