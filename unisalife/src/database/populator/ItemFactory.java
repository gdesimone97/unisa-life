/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import database.populator.exceptions.InvalidArgumentListException;
import saving.Saveable;
import game.GameObjects.Item;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author cmarino
 */
public class ItemFactory extends SaveableCreator {

    /**
     *
     * @param s The formatted string containing the parameters needed to
     * construct an Item object, the pattern of the stirng should be the
     * following : %x%y%path%info%mts indicating respectively x : x-coordinate
     * where Item should appear y : y-coordinate where Item should appear path :
     * path to the file of the sprite of the Item mts : a String that identifies
     * the exact map where Item has to spawn
     *
     */
    @Override
    public Saveable create(String s) throws InvalidArgumentListException {

        StringTokenizer st = new StringTokenizer(s, SaveableCreator.DELIMETER);
        try {
            Item i = new Item(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
            return i;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }

    }

}
