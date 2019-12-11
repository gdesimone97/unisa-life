/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import language.exceptions.FileTextManagerException;
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
    private final String FORMAT = FilesInformations.getFORMAT();

    static {
        try {
            instance = new FileTextManager();
        } catch (LanguageNotSetted | FileLanguageManagerException | InvalidFileNameException | TextFinderException ex) {
            ex.printStackTrace();
            instance = null;
        }
    }

    private FileTextManager() throws FileLanguageManagerException, FileNotSetException, InvalidFileNameException, TextFinderException,LanguageNotSetted {
        super();
        fileLanguageManager = FileLanguageManager.getLanguageManager();
        String currentLang = fileLanguageManager.getCurrentLanguage();
        if(currentLang.equals(""))
            throw new LanguageNotSetted();
        String filename = currentLang + FORMAT;
        fileTextFinder = FileTextFinder.getFileTextFinder(filename);
    }

    /**
     * Method to get the instance of FileTextManager
     *
     * @return the FileTextManager
     * @throws FileTextManagerException
     */
    public synchronized static FileTextManager getFileTextManager() throws FileTextManagerException {
        if (instance == null) {
            throw new FileTextManagerException();
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
    public void setLanguage(String lang) throws LanguageSelectedNotAvailableException {
        Set<String> availableLanguages = fileLanguageManager.getAvailableLanguages();
        for (String languageAvailable : availableLanguages) {
            if (lang.equals(languageAvailable)) {
                try {
                    FileTextFinder.setFileName(FilesInformations.getPATH() + "//" + lang + FORMAT);
                    fileLanguageManager.setLanguage(lang);
                } catch (InvalidFileNameException | TextFinderException ex) {
                    throw new LanguageSelectedNotAvailableException();
                }
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
    public List<String> getString(Information obj) throws StringNotFoundException, TextFinderException {
        return fileTextFinder.getString(obj);
    }
    
}
