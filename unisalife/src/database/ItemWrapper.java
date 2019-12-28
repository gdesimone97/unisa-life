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
public class ItemWrapper implements Storable, Serializable {

    private String info, p;
    private Position pos;

    private ItemWrapper() {
    }

    public ItemWrapper(Position pos, String p, String info) {
        this.pos = pos;
        this.p = p;
        this.info = info;
    }

    public ItemWrapper(Item i) {
        this.info = i.getInfo();
        this.pos = i.getPosition();
        this.p = i.getPath();
    }

    @Override
    public String getIndex() {

        return this.info;
    }

    public Item buildItem() {
        return new Item(pos, p, info);
    }

}
