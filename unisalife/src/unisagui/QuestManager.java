/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.util.HashMap;
import javax.swing.SwingUtilities;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;
import quests.quest.Quest;
import quests.quest.Quests;

/**
 * 
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class QuestManager {
    private GameFrame gameframe = GameFrame.getInstance();
    private HashMap<Integer, QuestGui> quests;
    
    /**
     * empty contructor
     */
    protected QuestManager(){
        
    }
    

    
    
    //---------------------------QUESTA QUANDO FUNZIONERA' IL DB---------------------------------

    /**
     * updates quest dialog
     */
    
    public void updateQuestDialog(){
        int count = 0;
        SwingUtilities.invokeLater(() -> gameframe.listModel.removeAllElements());
        quests = new HashMap<>();
        for (Quest q : Quests.getInstance().getQuests().values()){
            try {
                String subj = FileTextManager.getFileTextManager().getString(q.getSubject()).get(0);
                quests.put(count, new QuestGui(subj,q.getItemList()));
                addToList(count,subj);
            } catch (TextFinderException ex) {
            }
            count++;
        }
    }
    
    private void addToList(int count, String subj){
        SwingUtilities.invokeLater(()-> gameframe.listModel.add(count, subj));
    }
    
    /**
     * show the description of the quest
     * @param x is the index in the list
     */
    public void showDescription(int x){
        if (x>=0)
        SwingUtilities.invokeLater(() -> {
            try {
                gameframe.QuestTextArea.setText(FileTextManager.getFileTextManager().getString(
                        new MessageInformation("QuestDescription")).get(1)
                        + " " +
                        quests.get(x).getDescription());
            } catch (TextFinderException ex) {
                
            }
        });
        
    }
    
    // --------------------------------------------------------------------------------------------
    
    /*public void showDescription(int x){
        
        if(x>=0)
            SwingUtilities.invokeLater(() -> gameframe.QuestTextArea.setText(quests.get(x).getDescription()));
            
    }*/
    
    /**
     * hides description
     */
    public void hideDescription(){
        SwingUtilities.invokeLater(() -> gameframe.QuestTextArea.setText(""));
    }
    
    /**
     * show wuest dialog
     */
    public void showQuestDialog(){
        updateQuestDialog();
        SwingUtilities.invokeLater(() -> gameframe.QuestDialog.setVisible(true));
    }
    
    /**
     * hides quest dialog
     */
    public void hideQuestDialog(){
        SwingUtilities.invokeLater(() -> gameframe.QuestDialog.setVisible(false));
        hideDescription();
    }
}
