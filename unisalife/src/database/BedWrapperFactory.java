/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Bed;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for BedWrapper
 *
 * @author alfon
 */
public class BedWrapperFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the
     * BedWrapper object that should follow the pattern %x_source%y_source%x_dest%y_dest%map_source
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) {
        try {
            StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
            BedWrapper bw = new BedWrapper(new Bed(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                    new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))),
                    Integer.parseInt(st.nextToken()));

            return bw;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }

}
