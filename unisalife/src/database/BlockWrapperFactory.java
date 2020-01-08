/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Block;
import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for Block Wrapper
 *
 * @author christian
 */
class BlockWrapperFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the BlockWrapper
     * object that should follow the pattern
     * %x_source%y_source%map_source
     *
     * @throws InvalidArgumentListException
     */
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
