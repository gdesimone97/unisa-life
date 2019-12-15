/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import exam.booklet.Subject;
import interaction.*;
import language.Information;

/**
 *
 * @author Giuseppe De Simone
 */
public class Professor extends Person implements Information {

    private Subject subject;
    private String nome;

    public Professor(String nome, float x, float y, String path, Subject subject) {
        super(x, y, path);
        this.nome = nome;
        this.subject = subject;
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
    


}
