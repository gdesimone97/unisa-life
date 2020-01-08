/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Guardian;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for Guardian
 *
 * @author christian
 */
public class GuardianFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the
     * Guardian object that should follow the pattern %name%x_source%y_source%pah
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) {
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        try {

            Guardian g = new Guardian(st.nextToken(), new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken());
            return g;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }

}
