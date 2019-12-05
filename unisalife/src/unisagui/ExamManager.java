/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.SwingUtilities;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class ExamManager {
    
    private GameFrame gameframe = GameFrame.getInstance();
    private static final String EMPTY_TEXT = "";
    
    protected ExamManager(){
        
    }
    
    public void showExamDialog(String examName, boolean show){
        //setta nome dell'esame e se visibile o meno la dialog
        SwingUtilities.invokeLater(() -> gameframe.ExamDialog.setVisible(show));
        if (!show){
            SwingUtilities.invokeLater(() -> gameframe.ExamTextArea.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> gameframe.FirstAnswer.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> gameframe.SecondAnswer.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> gameframe.ThirdAnswer.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> gameframe.FourthAnswer.setText(EMPTY_TEXT));
        }
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
