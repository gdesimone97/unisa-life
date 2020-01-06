/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Distributor;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for DistributorWrapper
 *
 * @author christian
 */
public class DistributorWrapperFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the
     * DistributorWrapper object that should follow the pattern
     * %x_source%y_source%info
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) {
        try {
            StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
            Distributor d = new Distributor(new Position(Integer.parseInt((st.nextToken())), Integer.parseInt(st.nextToken())), st.nextToken());
            return d;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }

}
