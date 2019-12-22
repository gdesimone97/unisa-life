/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import game.GameObjects.Position;
import game.GameObjects.Professor;
import java.io.Serializable;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author christian
 */
@Indices({
    @Index(value = "subject.subject", type = IndexType.Unique)
})
public class ProfessorWrapper implements Saveable, Storable{
    
    private Position p;
    private String path;
    private Subject subject;
    private String nome;

    public ProfessorWrapper(String nome, Position p, String path, Subject subject) {
        this.p = p;
        this.path = path;
        this.nome = nome;
        this.subject = subject;
    }

    private ProfessorWrapper() {}

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
        return this.subject.toString();
    }
    
    public Professor buildProfessor(){
        return new Professor(nome, p, path, subject);
    }
}
