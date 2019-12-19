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
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;
/**
 *
 * @author Giuseppe De Simone
 */
@Indices({
    @Index(value = "subject.subject", type = IndexType.NonUnique)
})
public class Professor extends Person implements Information {

    private Subject subject;
    private String nome;

    public Professor(String nome, Position p, String path, Subject subject) {
        super(p, path);
        this.nome = nome;
        this.subject = subject;
    }

    private Professor() {
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
}
