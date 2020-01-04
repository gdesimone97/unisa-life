/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    protected QuestManager(){
        
    }
    
    /*public void updateQuestDialog(){
        int count = 0;
        SwingUtilities.invokeLater(() -> gameframe.listModel.removeAllElements());
        quests = new HashMap<>();
        for (Quest q : Quests.getInstance().getQuests().values()){
            quests.put(count, new QuestGui(q.getSubject().toString(),q.getItemList().toString()));
             SwingUtilities.invokeLater(() -> gameframe.listModel.add(count, q.getSubject().toString()));
        }
    }*/
    
    
    //---------------------------QUESTA QUANDO FUNZIONERA' IL DB---------------------------------
    
    public void updateQuestDialog(){
        int count = 0;
        SwingUtilities.invokeLater(() -> gameframe.listModel.removeAllElements());
        quests = new HashMap<>();
        for (Quest q : Quests.getInstance().getQuests().values()){
            try {
                String subj = FileTextManager.getFileTextManager().getString(q.getSubject()).get(0);
                quests.put(count, new QuestGui(subj,q.getItemList()));
                SwingUtilities.invokeLater(() -> gameframe.listModel.add(count, subj));
            } catch (TextFinderException ex) {
            }
        }
    }
    

    
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
    
    public void hideDescription(){
        SwingUtilities.invokeLater(() -> gameframe.QuestTextArea.setText(""));
    }
    
    public void showQuestDialog(){
        updateQuestDialog();
        SwingUtilities.invokeLater(() -> gameframe.QuestDialog.setVisible(true));
    }
    
    public void hideQuestDialog(){
        SwingUtilities.invokeLater(() -> gameframe.QuestDialog.setVisible(false));
        hideDescription();
    }
}
