/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Position;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Storable Creator for TileMap
 *
 * @author christian
 */
class TileMapWrapperFactory extends StorableCreator {

    /**
     *
     * @param s The string that contains every field needed to construct the
     * TileMapWrapper object that should follow the pattern
     * %id%length%width%tile_path%map_path%x%y%minimap_path
     * 
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) {

        try {
            StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
            TileMapWrapper tm = new TileMapWrapper(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(),
                    new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), st.nextToken());
            return tm;

        } catch (NumberFormatException | NoSuchElementException e) {
            throw new InvalidArgumentListException();
        }

    }

}
