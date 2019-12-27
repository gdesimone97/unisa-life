/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Guardian;
import game.GameObjects.Position;
import java.io.Serializable;
import org.dizitart.no2.objects.Id;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author christian
 */
public class GuardianWrapper implements Saveable, Storable {

    @Id
    private String nome;
    private String path;
    private Position position;

    private GuardianWrapper() {
    }

    public GuardianWrapper(String nome, Position position, String path) {
        this.nome = nome;
        this.position = position;
        this.path = path;
    }

    public GuardianWrapper(Guardian g) {
        this.nome = g.getInfo();
        this.path = g.getPath();
        this.position = g.getPosition();
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
        return this.nome;
    }

    public Guardian buildGuardian() {
        return new Guardian(nome, position, path);
    }

}
