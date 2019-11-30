/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;
import java.util.Set;

/**
 *
 * @author Giuseppe De Simone
 */
public abstract class TextManager extends LanguageManager implements TextFinder{
    public abstract Set getAvailableLanguages();
    public abstract Set getCurrentLanguage();
    public abstract void setLanguage(String);
}
