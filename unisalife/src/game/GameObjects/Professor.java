/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import exam.booklet.Subject;
import interaction.*;
import java.io.Serializable;
import language.Information;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author Giuseppe De Simone
 */
public class Professor extends Person implements Information, Saveable {

    private Subject subject;
    private String nome;

    public Professor(String nome, Position p, String path, Subject subject) {
        super(p,path);
        this.nome = nome;
        this.subject = subject;
    }
	
	private Professor(){
        super();
    }

    @Override
    public void interact() {
        InteractionManager profInteraction = new ProfessorInteractionManager();
        profInteraction.execute(this);
    }

    public Subject getSubject() {
        return subject;
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    @Override
    public Serializable save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}
