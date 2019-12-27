/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Distributor;
import game.GameObjects.Position;
import interaction.DistributorInteractionManager;
import interaction.InteractionManager;
import java.io.Serializable;
import org.dizitart.no2.objects.Id;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author christian
 */
class DistributorWrapper implements Saveable, Storable {

    @Id
    private String info;
    private Position position;

    private DistributorWrapper() {
    }

    public DistributorWrapper(Position p, String info) {
        this.position = p;
        this.info = info;
    }

    @Override
    public String getIndex() {
        return this.info;
    }

    @Override
    public Serializable save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Distributor buildDistributor() {
        return new Distributor(position, info);
    }
}
