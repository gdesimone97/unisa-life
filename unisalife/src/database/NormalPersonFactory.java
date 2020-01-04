/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.NormalPerson;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Factory for Normal Person, to be inserted in the database
 * @author alfon
 */
public class NormalPersonFactory extends StorableCreator {
    
    @Override
    public Storable create(String s) {
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        try {

            NormalPerson np = new NormalPerson(st.nextToken(), new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken());
            return np;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }
}
