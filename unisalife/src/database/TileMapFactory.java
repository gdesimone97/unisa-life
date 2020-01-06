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
class TileMapFactory extends StorableCreator {

    @Override
    public Storable create(String s) {

        try {
            StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
            TileMapWrapper tm = new TileMapWrapper(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(),
                    new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),st.nextToken());
            return tm;

        } catch (NumberFormatException | NoSuchElementException e) {
            throw new InvalidArgumentListException();
        }

    }

}
