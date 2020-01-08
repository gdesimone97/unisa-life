/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.NormalPerson;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Factory for Normal Person, to be inserted in the database
 *
 * @author alfon
 */
public class NormalPersonFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the
     * Normal Person object that should follow the pattern
     * %name%x_source%y_source%pah
     *
     * @throws InvalidArgumentListException
     */
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
