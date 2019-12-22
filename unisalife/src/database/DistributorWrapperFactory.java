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
 *
 * @author christian
 */
public class DistributorWrapperFactory extends StorableCreator{

    @Override
    public Storable create(String s) {
        try{
            StringTokenizer st = new StringTokenizer(s,StorableCreator.DELIMETER);
            Distributor d = new Distributor(new Position(Integer.parseInt((st.nextToken())),Integer.parseInt(st.nextToken())),st.nextToken());
            return d;
            
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new InvalidArgumentListException();
        }
    }
    
    
    
}
