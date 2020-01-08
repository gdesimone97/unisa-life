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
 * this class represents a professor in the game
 * @author Giuseppe De Simone
 */
@Indices({
    @Index(value = "subject", type = IndexType.Unique)
})
public class Professor extends Person implements Information {

    private String subject;
    private String nome;

    /**
     *
     * @param nome name of the player
     * @param p position of the player in the map
     * @param path path of the image to be loaded and rendered in the game
     * @param subject subject of the exam of the player
     */
    public Professor(String nome, Position p, String path, String subject) {
        super(p, path);
        this.nome = nome;
        this.subject = subject;
    }

    private Professor() {
        super();
    }

    /**
     * this method is used to interact with the professor and start the exam
     */
    @Override
    public void interact() {
        InteractionManager profInteraction = new ProfessorInteractionManager();
        profInteraction.execute(this);
    }

    /**
     *
     * @return the subject of professor
     */
    public String getSubject() {
        return subject;
    }

    @Override
    public String getInfo() {
        return this.nome;
    }

    /**
     *
     * @return index of professore in order to access to the Database
     */
    @Override
    public String getIndex() {
        return this.subject;
    }
    
    /**
     *
     * @return the name of the professor
     */
    public String getNome() {
        return nome;
    }
}
