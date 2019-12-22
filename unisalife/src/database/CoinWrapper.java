/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Coin;
import game.GameObjects.Position;
import java.io.Serializable;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author christian
 */
public class CoinWrapper implements Saveable, Storable{

    private String path,info;
    private Position p;
    
    private CoinWrapper(){}
    public CoinWrapper(Position p, String path, String info){
        this.p = p;
        this.path = path;
        this.info = info;
    }
    
    @Override
    public Serializable save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIndex() {
        return this.info;
    }
    
    
    public Coin buildCoin(){
        return new Coin(p, path, info);
    }
    
    
    
}
