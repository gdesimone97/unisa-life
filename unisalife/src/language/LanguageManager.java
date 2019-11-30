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
abstract class LanguageManager {
    private String currentLanguage;
    private String availableLanguages;
    
    public abstract Set<String> getAvailableLanguages();
    public abstract String getCurrentLanguage();
    public abstract void setLanguage(String lang);
}
