/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author liovi
 */

import booklet.BookletSingleton;
import exam.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import question.*;


/**
 * This class is created due to the necessity of test the Exam class 
 * and the BookletSingleton class
 * 
 * @author liovi
 */
public class ExamTest {
    
    public static void main(String[] args) {
        Exam esame = new Exam(Materia.matematica);
        BookletSingleton booklet = BookletSingleton.getInstance();        
        int i;
        //Test of the class Exam in particular the functionality of score assignment
        for (i=1; i<=3; i++){
            doExam(esame, 10);
        }
        System.out.println("Il voto finale e': " + esame.getScore());
//        if(esame.getScore()==30){
//            doExam(esame, 20, i);
//        }
//        System.out.println("Il voto è: " + esame.getScore());
//        
//        //Test of the class BookletSingleton functionality
//        if(esame.getScore()>18)
//            booklet.setScore(Materia.matematica, (int) esame.getScore());
//        
//        System.out.println("Libretto");
//        for (Materia x : Materia.values()){
//            if(!booklet.getAvailablity(x))
//                System.out.println(x.toString() + " already done: yes score:"+booklet.getScore(x));
//            else
//                System.out.println(x.toString() + " already done: no");
//        }
    }
    
    
    public static void doExam (Exam esame, int seconds){
        Question question = esame.getQuestion();
        String stringa = JOptionPane.showInputDialog(question.toString() + "\n\n Risposta: ");

        ArrayList<Answer> answers = question.getAnswers();

        Answer answer = answers.get(Integer.parseInt(stringa)-1);
        System.out.println("Hai risposto " + answer + ". " + (question.isCorrect(answer)));
        esame.verifyAnswer(question.isCorrect(answer), seconds, question.getLevel()); //the parameter seconds is given, change it to experience a variation in the resulting score
        System.out.println("current score: "+esame.getCurrentScore());
    }
}
