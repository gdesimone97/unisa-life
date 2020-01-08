/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for Professor
 * @author cmarino
 */
public class ProfessorFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the
     * ProfessorItem that should follow the pattern %name%x%y%map%subject
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) throws InvalidArgumentListException {

        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);

        try {

            //Retrieve subject from the DB
            Professor pw = new Professor(st.nextToken(), new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), 
                    st.nextToken(), st.nextToken());
            return pw;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
        //Insert professor in the DB
    }

}
