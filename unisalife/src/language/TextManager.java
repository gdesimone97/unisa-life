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
 * to get the available languages of the game and the current one, set the
 * language of the game and obtain a set of strings respect to a game's object.
 *
 * @author Giuseppe De Simone
 */
public abstract class TextManager extends LanguageManager implements TextFinder {

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract Set<String> getAvailableLanguages();

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract String getCurrentLanguage();

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract void setLanguage(String lang) throws LanguageSelectedNotAvailableException;

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract List<String> getString() throws StringNotFoundException;
}
