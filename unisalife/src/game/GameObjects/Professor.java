/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import exam.booklet.Saveable;
import exam.booklet.Subject;
import interaction.*;
import java.io.Serializable;
import language.Information;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author Giuseppe De Simone
 */
public class Professor extends Person implements Information, Saveable {
    @Id
    private String subjectName;
    private Subject subject;
    private String nome;

    private Professor(){
        super();
    }
    
    public Professor(String nome, float x, float y, String path, Subject subject) {
        super(x, y, path);
        this.nome = nome;
        this.subject = subject;
        this.subjectName = subject.getInfo();
    }

    @Override
    public void interact() {
        InteractionManager profMìInteraction = new ProfessorInteractionManager();
        profMìInteraction.execute(this);
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
    public void load(Serializable obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}
