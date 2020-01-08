/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Distributor;
import game.GameObjects.Position;
import org.dizitart.no2.objects.Id;


/**
 * Wrapper class to mantain a Distributor object and its map 
 * @author christian
 */
class DistributorWrapper implements Storable {

    @Id
    private String info;
    private Position position;

    private DistributorWrapper() {
    }
    
    /**
     * Contructor of the wrapper class
     * @param p position of the distributor
     * @param info unique string
     */
    public DistributorWrapper(Position p, String info) {
        this.position = p;
        this.info = info;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndex() {
        return this.info;
    }
    
    /**
     * Method to build a distributor based on the wrapped informations
     * @return an instance of distributor
     */
    public Distributor buildDistributor() {
        return new Distributor(position, info);
    }
}
