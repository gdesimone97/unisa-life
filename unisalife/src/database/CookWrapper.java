/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Cook;
import game.GameObjects.Position;
import java.io.Serializable;
import saving.exceptions.LoadingException;

/**
 *
 * @author christian
 */
public class CookWrapper implements Serializable, Storable {

    private String nome, path;
    private Position p;

    public CookWrapper(String nome, Position p, String path) {
        this.nome = nome;
        this.p = p;
        this.path = path;
    }

    private CookWrapper() {
    }

    public CookWrapper(Cook c) {
        this.nome = c.getInfo();
        this.p = c.getPosition();
        this.path = c.getPath();
    }

    @Override
    public String getIndex() {
        return this.nome;
    }

    public Cook buildCook() {
        return new Cook(nome, p, path);
    }

}
