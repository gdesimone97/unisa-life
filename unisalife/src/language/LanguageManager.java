/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.util.Set;
import language.exceptions.*;

/**
 * Abstract class that allow to developer to get the current language and set
 * one. This class define also the interface to obtain all game's languages
 * respect to selected languages data source
 *
 * @author Giuseppe De Simone
 */
abstract class LanguageManager {

    private String currentLanguage;

    /**
     * Method to obtain all available game's languages
     *
     * @return a set of strings containing all games languages
     */
    public abstract Set<String> getAvailableLanguages();

    /**
     * Method to get the set language
     *
     * @return a string containing the current game's language
     */
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    /**
     * Set the game's language compared to the language given as parameter
     *
     * @param lang language to set
     * @throws LanguageSelectedNotAvailableException if the given language is
     * not available
     */
    public void setLanguage(String lang) throws LanguageSelectedNotAvailableException,FileNotSetException {
        this.currentLanguage = lang;
    }
}
