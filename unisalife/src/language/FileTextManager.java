/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.util.Set;
import language.exceptions.NoFileLanguageManagerCreatedException;

/**
 *
 * @author Giuseppe De Simone
 */
public class FileTextManager extends TextManager {

    private static FileTextManager instance;
    private FileLanguageManager fileLanguageManager;
    private FileTextFinder fileTextFinder;
    private final String FORMAT = ".txt";
    
    static {
        try {
            instance = new FileTextManager();
        } catch (NoFileLanguageManagerCreatedException ex) {
            ex.printStackTrace();
            instance = null;
        }
    }

    private FileTextManager() throws NoFileLanguageManagerCreatedException {
        super();
        fileLanguageManager = FileLanguageManager.getLanguageManager();
        fileTextFinder = FileTextFinder.getFileTextFinder();
        
    }

    public static FileTextManager getFileTextManager() throws NoFileLanguageManagerCreatedException {
        if (instance == null) {
            throw new NoFileLanguageManagerCreatedException();
        }
        return instance;
    }

    @Override
    public Set<String> getAvailableLanguages() {
        return fileLanguageManager.getAvailableLanguages();
    }

    @Override
    public String getCurrentLanguage() {
        return fileLanguageManager.getCurrentLanguage();
    }

    @Override
    public void setLanguage(String lang) throws LanguageSelectedNotAvailableException{
        Set<String> availableLanguages = fileLanguageManager.getAvailableLanguages();
        for (String languageAvailable: availableLanguages){
            if(lang == languageAvailable){
                fileLanguageManager.setLanguage(lang);
                fileTextFinder.setFileName(lang+FORMAT);
                return;
            }
        }
        throw new LanguageSelectedNotAvailableException();
    }

    @Override
    public String getString() {
        return fileTextFinder.getString();
    }
}
