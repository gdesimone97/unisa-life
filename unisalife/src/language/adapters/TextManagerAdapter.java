/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.adapters;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import language.Information;
import language.TextManager;
import language.exceptions.LanguageSelectedNotAvailableException;
import language.exceptions.TextFinderException;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 *
 * @author Giuseppe De Simone
 */
public class TextManagerAdapter extends TextManager implements Saveable {

    private static final TextManagerAdapter instance = new TextManagerAdapter();

    public static TextManagerAdapter getTextManagerAdpter() {
        return instance;
    }

    @Override
    public Set<String> getAvailableLanguages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getString(Information obj) throws TextFinderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Serializable save() {
        String lang = getCurrentLanguage();
        return lang;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        try {
            String lang = (String) obj;
            setLanguage(lang);
        } catch (LanguageSelectedNotAvailableException ex) {
            throw new LoadingException();
        }
    }

}