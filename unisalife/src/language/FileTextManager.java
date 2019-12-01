/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import language.exceptions.FileTextManagerNotCreatedException;
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
     * Method to get the instance of FileTextManager
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
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAvailableLanguages() {
        return fileLanguageManager.getAvailableLanguages();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentLanguage() {
        return fileLanguageManager.getCurrentLanguage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLanguage(String lang) throws LanguageSelectedNotAvailableException, FileNotSetException {
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

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<String> getString() throws StringNotFoundException {
        return fileTextFinder.getString();
    }
}
