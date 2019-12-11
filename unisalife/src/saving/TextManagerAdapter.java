/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

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
class TextManagerAdapter extends TextManager implements Saveable {

    private static final TextManagerAdapter instance = new TextManagerAdapter();

    public static TextManagerAdapter getTextManagerAdpter() {
        return instance;
    }

    /**
     * Not implemented
     */
    @Override
    public Set<String> getAvailableLanguages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented
     */
    public List<String> getString(Information obj) throws TextFinderException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable save() {
        String lang = getCurrentLanguage();
        return lang;
    }

    /**
     * {@inheritDoc}
     */
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
