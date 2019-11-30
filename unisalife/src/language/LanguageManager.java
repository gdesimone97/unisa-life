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

    public abstract Set<String> getAvailableLanguages();

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setLanguage(String lang) {
        this.currentLanguage = lang;
    }
}
