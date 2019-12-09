/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 * This class manages the entire panel dedicated to the exam screen.
 * It contains methods for updating text fields and for communicating with the exam thread.
 */
public class ExamManager {
    
    private GameFrame gameframe = GameFrame.getInstance();
    private static final String EMPTY_TEXT = "";
    protected static int RESULT=0;
    protected ResultGui rg;
    private int time = 0;
    private Timer timing;
    protected static ExamManager instance;
    
    
    
    
    protected static ExamManager getInstance() {
        if (instance == null) {
            instance = new ExamManager();
        }

        return instance;
    }
    
    
    
    /**
     * @param RESULT in this parameter the answer given by the user within the time limit will be saved.
     * If the user does not respond this parameter will remain equal to null
     */
    protected  void setRESULT(int RESULT) {
        rg.setValue(RESULT);
        
    }
   

    /**
     * This method resets all text fields of the Exam label
     */
    private void clearExam() {
        SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.TimeLabel.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> gameframe.NameOfExamLabel.setText(EMPTY_TEXT));
    }
    /**
     * @param examName
     * @param question
     * @param answer1
     * @param answer2
     * @param answer3
     * @param answer4 
     * This method sets all the text fields of the Exam label
     */
    private void fillExam(String examName, String question, String answer1, String answer2, String answer3, String answer4) {
        this.manageButtons(true);
        this.clearExam();
        SwingUtilities.invokeLater(() -> gameframe.ExamDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(question));
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(answer1));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(answer2));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(answer3));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(answer4));
        SwingUtilities.invokeLater(() -> gameframe.NameOfExamLabel.setText(examName));
        this.showTimer();
            
        
    }
    /**
     * 
     * @param enable  if is false all the button wll be disabled and viceversa
     */
    private void manageButtons(boolean enable){
        SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setEnabled(enable));
        SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setEnabled(enable));
        SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setEnabled(enable));
        SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setEnabled(enable));
        
    }
    
    /**
     * This method updates in real time the time remaining for the user to answer the question 
     * (in the respective label). This is done using the Swing Timer.
     */
    
    private void showTimer() {

        timing = new Timer(time, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (time == 0) {
                    timing.stop();
                }
                SwingUtilities.invokeLater(() -> gameframe.TimeLabel.setText(String.valueOf(time / 1000)));
                time -= 1000;
            }
        });
        timing.start();
    }

    
    protected synchronized  int showExamDialog(String examName, String question, String answer1, String answer2, String answer3, String answer4, int time){
        rg= new ResultGui(time*1000);
        this.fillExam(examName, question, answer1, answer2, answer3, answer4);
        this.time=time*1000;
        int value= rg.getValue();
        this.manageButtons(false);
        return (value);
        
        /* 
        try {
            this.wait(time*1000);
        } catch (InterruptedException ex) { }
        */
        
        
        
        
    }
    
   
    /**
     * When called the exam question is written on the Exam Dialog
     * @param question is the question that has to be written
     */
    public void setExamQuestion(String question){
         SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(question));
    }
    
    /**
     * When called one of the exam question is written in the Exam Dialog
     * @param answer is the answer that has to be written
     * @param position is the position of the answer in the exam dialog, position <=4
     */
    public void setExamAnswer(String answer, int position) throws Exception {
        if (position>4 || position<1)
            throw new Exception();
        if (position==1)
            SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(answer));
        else if (position==2)
           SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(answer));
        else if (position==3)
            SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(answer));
        else
            SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(answer));
        
        
    }
    
   
 
}
