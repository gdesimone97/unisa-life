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
public class ProfessorWrapper implements Serializable, Storable {

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

    public ProfessorWrapper(Professor p) {
        this.p = p.getPosition();
        this.path = p.getPath();
        this.subject = p.getSubject();
        this.nome = p.getInfo();
    }

    private ProfessorWrapper() {
    }

    @Override
    public String getIndex() {
        return this.subject.toString();
    }

    public Professor buildProfessor() {
        return new Professor(nome, p, path, subject);
    }
}
