/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import language.exceptions.ListingFilesException;
import language.exceptions.FileLanguageManagerException;
import language.exceptions.NoLanguegesFileFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Class to get all available languages by files
 *
 * @author Giuseppe De Simone
 */
class FileLanguageManager extends LanguageManager {

    private static FileLanguageManager instance = null;
    private HashMap<String, String> fileLanguagesMap = new HashMap<>();
    private final String FORMAT = FilesInformations.getFORMAT();

    private FileLanguageManager() throws NoLanguegesFileFoundException, ListingFilesException {
        super();
        getFileLanguages();
    }

    static {
        try {
            instance = new FileLanguageManager();
        } catch (NoLanguegesFileFoundException | ListingFilesException ex) {
            instance = null;
        }
    }
 
    /**
     * Methods that return a instance of FileLanguageManager
     *
     * @return a instance of FileLanguageManager
     * @throws FileLanguageManagerException if something has gone wrong
     */
    public synchronized static FileLanguageManager getLanguageManager() throws FileLanguageManagerException {
        if (instance == null) {
            throw new FileLanguageManagerException();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAvailableLanguages() {
        Set<Map.Entry<String, String>> map = this.fileLanguagesMap.entrySet();
        Set<String> langsSet = new HashSet<>();
        for (Map.Entry<String, String> language : map) {
            langsSet.add(language.getValue());
        }
        return langsSet;
    }

    private void getFileLanguages() throws NoLanguegesFileFoundException, ListingFilesException {
        final String PATH_STRING = FilesInformations.getPATH();
        Path dir = Paths.get(PATH_STRING);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                Scanner sc = new Scanner(fileName);
                sc.useDelimiter(FORMAT);
                String lang = sc.next();
                String relativeFileName = PATH_STRING + "//" + fileName;                
                this.fileLanguagesMap.put(relativeFileName, lang);
            }
        } catch (IOException ex) {
            throw new ListingFilesException();
        }
        if (this.fileLanguagesMap.isEmpty()) {
            throw new NoLanguegesFileFoundException();
        }
    }
}
