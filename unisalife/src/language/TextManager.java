/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.util.List;
import java.util.Set;
import language.exceptions.*;

/**
 * Abstract class where are defined the main methods that a class have to have
 * to get the available languages of the game and the current one,
 * set the language of the game and obtain a set of string respect to a game's object.
 * @author Giuseppe De Simone
 */
public abstract class TextManager extends LanguageManager implements TextFinder {

    @Override
    public abstract Set<String> getAvailableLanguages();

    @Override
    public abstract String getCurrentLanguage();

    @Override
    public abstract void setLanguage(String lang) throws LanguageSelectedNotAvailableException;

    @Override
    public abstract List<String> getString() throws StringNotFoundException;
}
