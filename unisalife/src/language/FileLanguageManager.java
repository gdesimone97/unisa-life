/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import language.exception.NoLanguegesFileFoundException;

/**
 *
 * @author Giuseppe De Simone
 */
class FileLanguageManager extends LanguageManager {

    private static FileLanguageManager instance = null;
    private HashMap<String, String> fileLanguagesMap = new HashMap<>();

    private FileLanguageManager() throws NoLanguegesFileFoundException {
        super();
        getFileLanguages();
    }

    static {
        try {
            instance = new FileLanguageManager();
        } catch (NoLanguegesFileFoundException ex) {
            instance = null;
        }
    }

    public static FileLanguageManager getLanguageManager() throws NoLanguegesFileFoundException{
        if(instance == null) {
            throw new NoLanguegesFileFoundException();
        }
        return instance;
    }

    @Override
    public Set<String> getAvailableLanguages() {
        Set<Map.Entry<String, String>> map = this.fileLanguagesMap.entrySet();
        Set<String> langsSet = new HashSet<>();
        for (Map.Entry<String, String> language : map) {
            langsSet.add(language.getValue());
        }
        return langsSet;
    }

    private void getFileLanguages() throws NoLanguegesFileFoundException {
        final String PATH_STRING = "..//lang";
        Path dir = Paths.get(PATH_STRING);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                Scanner sc = new Scanner(fileName);
                sc.useDelimiter(".txt");
                String lang = sc.next();
                this.fileLanguagesMap.put(fileName, lang);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error listing files");
        }
        if (this.fileLanguagesMap.size() == 0) {
            throw new NoLanguegesFileFoundException();
        }
    }
}
