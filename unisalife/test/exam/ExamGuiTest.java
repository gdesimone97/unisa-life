/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import exam.question.Materia;

/**
 *
 * @author liovi
 */
public class ExamGuiTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ExamResult er = new ExamResult();
        Exam esame = new Exam(Materia.matematica);
        Thread esameThread = new Thread(esame);
        esameThread.start();
        
    }
    
}
