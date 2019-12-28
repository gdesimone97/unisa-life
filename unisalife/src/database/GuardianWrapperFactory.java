/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author christian
 */
public class GuardianWrapperFactory extends StorableCreator {

    @Override
    public Storable create(String s) {
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        try {

            GuardianWrapper g = new GuardianWrapper(st.nextToken(), new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken());
            return g;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }

}
