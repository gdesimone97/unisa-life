/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import game.GameObjects.Block;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author christian
 */
class BlockWrapperFactory extends StorableCreator {

    @Override
    public Storable create(String s) {
        try {

            StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
            BlockWrapper wp = new BlockWrapper(new Block(new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))), Integer.parseInt(st.nextToken()));
            //System.out.println(wp.getBlock().getPosition());
            //System.out.println(wp.getIndex());
            return wp;

        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }

}
