/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

import language.Information;

/**
 * This Enumeration contains all the subjects of the game
 *
 * @author 1997g
 */
public enum Materia implements Information {
    matematica("Matematica"),
    fisica("Fisica"),
    programmazioneadoggetti("Programmazione ad oggetti"),
    retidicalcolatori("Reti di Calcolatori"),
    database("Database");

    private String subject;

    private Materia(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return subject;
    }

    @Override
    public String getInfo() {
        return subject;
    }
}
