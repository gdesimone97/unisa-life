/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import game.Interfaces.Initializable;
import game.Interfaces.Initializable.InitException;
import language.exceptions.FileTextManagerException;
import java.util.List;
import java.util.Set;
import language.exceptions.*;
import saving.exceptions.LoadingException;

/**
 * This class extends the abstract class TextManager and specialize his methods
 * in order to perform the operations of getting strings, setting and obtaining
 * languages on files
 *
 * @author Giuseppe De Simone
 */
public class FileTextManager extends TextManager implements Initializable {

    private static FileTextManager instance;
    private FileLanguageManager fileLanguageManager;
    private FileTextFinder fileTextFinder;
    private final String FORMAT = FilesInformations.getFORMAT();

    static {
        try {
            instance = new FileTextManager();
        } catch (FileLanguageManagerException | InvalidFileNameException | TextFinderException ex) {
            instance = null;
        }
    }

    private FileTextManager() throws FileLanguageManagerException, FileNotSetException, InvalidFileNameException, TextFinderException {
        super();
        fileLanguageManager = FileLanguageManager.getLanguageManager();
        String currentLang = fileLanguageManager.getCurrentLanguage();
        String filename = currentLang + FORMAT;
        fileTextFinder = FileTextFinder.getFileTextFinder(filename);
    }

    /**
     * Method to get the instance of FileTextManager
     *
     * @return the FileTextManager
     * @throws FileTextManagerException
     */
    public synchronized static FileTextManager getFileTextManager() throws InitException {
        if (instance == null) {
            throw new InitException("Text Manager not created");
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

    @Override
    public void init() throws InitException {
        SaveManagerAdapter sv = new SaveManagerAdapter();
        try {
            String loadLang = sv.getLang();
            if (loadLang.equals("")) {
                setLanguage("eng");
            } else {
                setLanguage(loadLang);
            }
        } catch (LoadingException ex) {
            throw new InitException("Errore caricamento lingua salvata");
        } catch (LanguageSelectedNotAvailableException ex) {
            throw new InitException("Lingua selezionata non disponibile");
        }
    }
}
