/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Item;
import game.GameObjects.Position;
import java.io.Serializable;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author christian
 */
public class ItemWrapper implements Storable, Saveable{
 
    private String info, p;
    private Position pos;
    
    
    private ItemWrapper(){}
    
    public ItemWrapper(Position pos, String p, String info){
        this.pos = pos;
        this.p = p;
        this.info = info;
    }

    @Override
    public String getIndex(  ) {
       
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
    
    public Item buildItem(){
        return new Item(pos, p, info);
    }
    
}
