/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

/**
 *
 * @author Giuseppe De Simone
 */
public class Professor extends Person {

    private SubjectEnum subject;

    public Professor(float x, float y, String path, SubjectEnum subject) {
        super(x, y, path);
        this.subject = subject;
    }

    @Override
    public void interact() {
    }
}
