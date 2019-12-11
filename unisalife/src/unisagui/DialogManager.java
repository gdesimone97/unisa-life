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
public class DialogManager {
    
    private final GameFrame gameframe;
    
    protected DialogManager(){
        gameframe = GameFrame.getInstance();
    }
    /**
     * show an hint in a small text area that can be closed using a button
     * @param hint
     * 
     */
    public void showHint(String hint){
        SwingUtilities.invokeLater(() -> gameframe.HintTextArea.setText(hint));
        SwingUtilities.invokeLater(() -> gameframe.HintScrollPane.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setVisible(true));
    }
    
    public void hideHint(){
        SwingUtilities.invokeLater(() -> gameframe.HintTextArea.setText(""));
        SwingUtilities.invokeLater(() -> gameframe.HintScrollPane.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setVisible(false));
    }
    /**
     * show a little conversation 
     * @param conversation
     * s
     */
    public void showDialog(String conversation){
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setText(conversation));
        SwingUtilities.invokeLater(() -> gameframe.ConversationScrollPane.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setVisible(true));
    }
    
    public void hideDialog(){
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setText(""));
        SwingUtilities.invokeLater(() -> gameframe.ConversationScrollPane.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setVisible(false));;
    }
}
