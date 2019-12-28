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
 * @author alfon
 */
public class CoinWrapperFactory extends StorableCreator {

    public CoinWrapperFactory() {
    }

    @Override
    public Storable create(String s) {
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        try {
            CoinWrapper cw = new CoinWrapper(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken(), st.nextToken());
            return cw;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }
}
