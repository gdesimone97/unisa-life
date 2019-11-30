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
        } catch (NoFileLanguageManagerCreatedException | FileNotSetException ex) {
            ex.printStackTrace();
            instance = null;
        }
    }

    private FileTextManager() throws NoFileLanguageManagerCreatedException, FileNotSetException {
        super();
            fileLanguageManager = FileLanguageManager.getLanguageManager();
            String filename = fileLanguageManager.getCurrentLanguage() + FORMAT;
            fileTextFinder = FileTextFinder.getFileTextFinder(filename);
    }

    public static FileTextManager getFileTextManager() throws FileTextManagerNotCreatedException {
        if (instance == null) {
            throw new FileTextManagerNotCreatedException();
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
    public void setLanguage(String lang) throws LanguageSelectedNotAvailableException {
        Set<String> availableLanguages = fileLanguageManager.getAvailableLanguages();
        for (String languageAvailable : availableLanguages) {
            if (lang.equals(languageAvailable)) {
                fileLanguageManager.setLanguage(lang);
                FileTextFinder.setFileName(lang + FORMAT);
                return;
            }
        }
        throw new LanguageSelectedNotAvailableException();
    }

    @Override
    public List<String> getString() throws StringNotFoundException{
        return fileTextFinder.getString();
    }
}
