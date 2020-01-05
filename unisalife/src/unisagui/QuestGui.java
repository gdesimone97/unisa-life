/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.FileTextManager;
import language.MessageInformation;
import language.exceptions.TextFinderException;

/**
 *
 * @author virgi
 */
public class QuestGui {
   
    private String name;
    private Set<String> description;

    public QuestGui(String name, Set<String> description) {
        this.name = name;
        this.description = description;
    }    

    public String getName() {
        return name;
    }

    public String getDescription() {
        String text = "";
        for (String s : description){
            try {
                text += "\n" +
                        FileTextManager.getFileTextManager().getString(new MessageInformation("calculator")).get(0)+
                        //FileTextManager.getFileTextManager().getString(new MessageInformation("QuestDescription")).get(1)
                        ";";
            } catch (TextFinderException ex) {
                
            }
        }
        return text;
    }
        
}
