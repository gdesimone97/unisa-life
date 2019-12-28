/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Coin;
import game.GameObjects.Position;
import java.io.Serializable;

/**
 *
 * @author christian
 */
public class CoinWrapper implements Serializable, Storable {

    private String path, info;
    private Position p;

    private CoinWrapper() {
    }

    public CoinWrapper(Position p, String path, String info) {
        this.p = p;
        this.path = path;
        this.info = info;
    }

    public CoinWrapper(Coin c) {
        this.p = c.getPosition();
        this.info = c.getInfo();
        this.path = c.getPath();
    }


    @Override
    public String getIndex() {
        return this.info;
    }

    public Coin buildCoin() {
        return new Coin(p, path, info);
    }

}
