/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Position;
import game.GameObjects.Teleport;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author alfon
 */
public class TeleportWrapperFactory extends StorableCreator {

    @Override
    public Storable create(String s) {
        try {
            StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
            TeleportWrapper tw = new TeleportWrapper(new Teleport(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                    Integer.parseInt(st.nextToken()),
                    new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))),
                    Integer.parseInt(st.nextToken()));

            return tw;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }

}
