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
        Exam esame = new Exam(Materia.matematica, er);
        Thread esameThread = new Thread(esame);
        esameThread.start();
        
        int finalVote = er.getValue();
        
        //GuiManager.getInstance().showHint(FileTextManager.getFileTextManager().getString(new MessageInformation("ScoreTaken")).get(0) + getScore());
        System.out.println("il tuo voto: " + finalVote);
    }
    
}
