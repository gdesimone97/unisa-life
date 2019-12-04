/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is an extention of QuestionFactory abstract class
 * The purpose of this class is to instantiate the object Questions
 * starting from a list of strings that is given by another class.
 * The object refers to a particular subject that is passed through constructor
 * 
 * @author 1997g
 */
public class StringsQuestionFactory extends QuestionFactory {
    private Questions questions;
    private String delimiter = "#";
    
    /**
    * The constructor creates an instance of the object Questions and starts the parsing of the strings
    * @param materia is the subject in which we want questions
    */
    public StringsQuestionFactory(Materia materia) {
        super(materia);
        questions = new Questions();
        this.parseQuestions();
    }

    /**
    * Returns the instance of Questions
    */
    @Override
    public Questions getQuestions() {
        return questions;
    }
    
    /**
    * This method is private because it's a specific implementation of QuestionFactory
    * It aims to take a list of strings passed by another class, and divide it in parts,
    * so that it can create an object Question, made of Answers also, and add to the object Questions
    */
    private void parseQuestions() {
        String[] strList = {"1#Qual è la capitale della Francia?#Parigi#Roma#Londra#Madrid",
                            "2#Quanti stati ha un bit?#2#1#3",
                            "3#Chi è lo scrubmastah di questo gruppo?#Tu#Giuseppe#Ritrovato",
                            "4#Virginia ha i capelli:#Rosa#Verdi#Rossi#Bianchi",
                            "4#Chi è l'unica ragazza del gruppo 8?#Alfonso#Gennaro#Simone",
                            "4#Peppe quante cose può guardare contemporaneamente?#Due#Una#Mille"};
        
        Scanner sc;
        boolean count;
        Integer level;
        String quest;
        ArrayList<Answer> answers;
        
        for (String str : strList) {
            sc = new Scanner(str);
            sc.useDelimiter(delimiter);
            answers = new ArrayList<Answer>();
            count = true;
            
            level = sc.nextInt();
            quest = sc.next();
            while(sc.hasNext()) {
                answers.add(new Answer(sc.next(), count));
                if (count) {
                    count = false;
                }
            }
            
            Question q = new Question(level, quest, answers);
            questions.addQuestion(q);
        }
    }
    
}
