/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Coin;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for Coin
 *
 * @author alfon
 */
public class CoinFactory extends StorableCreator {

    public CoinFactory() {
    }

    /**
     *
     * @param s The string that contains every field needed to construct the Coin
     * object that should follow the pattern
     * %x_source%y_source%pah%info
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) {
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        try {
            Coin cw = new Coin(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken(), st.nextToken());
            return cw;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }
}
