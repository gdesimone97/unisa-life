/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;
import java.util.List;
import java.util.Set;
import language.exceptions.*

/**
 *
 * @author Giuseppe De Simone
 */
public abstract class TextManager extends LanguageManager implements TextFinder{
    @Override
    public abstract Set<String> getAvailableLanguages();
    @Override
    public abstract String getCurrentLanguage();
    @Override
    public abstract void setLanguage(String lang) throws LanguageSelectedNotAvailableException;
    @Override
    public abstract List<String> getString();
}
