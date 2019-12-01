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
 * This class extends the abstract class TextManager and specialize his methods
 * in order to perform the operations of getting strings, setting and obtaining
 * languages on files
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

    /**
     * method to get the instance of FileTextManager
     *
     * @return the FileTextManager
     * @throws FileTextManagerNotCreatedException
     */
    public static FileTextManager getFileTextManager() throws FileTextManagerNotCreatedException {
        if (instance == null) {
            throw new FileTextManagerNotCreatedException();
        }
        return instance;
    }

    /**
     * method to get all available languages
     *
     * @return a set of all available languages
     */
    @Override
    public Set<String> getAvailableLanguages() {
        return fileLanguageManager.getAvailableLanguages();
    }

    /**
     * method to get the current language
     *
     * @return a String containing the current language
     */
    @Override
    public String getCurrentLanguage() {
        return fileLanguageManager.getCurrentLanguage();
    }

    /**
     * Set the game's language compared to the language given as parameter
     *
     * @param lang - language to set
     * @throws LanguageSelectedNotAvailableException if the given language is
     * not available
     */
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
    public List<String> getString() throws StringNotFoundException {
        return fileTextFinder.getString();
    }
}
