/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import interaction.*;
import language.Information;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;
/**
 *
 * @author Giuseppe De Simone
 */
@Indices({
    @Index(value = "subject", type = IndexType.Unique)
})
public class Professor extends Person implements Information {

    private String subject;
    private String nome;

    public Professor(String nome, Position p, String path, String subject) {
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

    public String getSubject() {
        return subject;
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    @Override
    public String getIndex() {
        return this.subject;
    }
    
    public String getNome() {
        return nome;
    }
}
