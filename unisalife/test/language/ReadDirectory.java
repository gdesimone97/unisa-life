/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author desio
 */
class ReadDirectory {
     public static Set<String> readDirectory() throws IOException {
        final String PATH_STRING = FilesInformations.getPATH();
        final String FORMAT = FilesInformations.getFORMAT();
        Path dir = Paths.get(PATH_STRING);
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        Set<String> files = new HashSet<>();
        for (Path file : stream) {
            Scanner sc = new Scanner(file.getFileName().toString());
            sc.useDelimiter(FORMAT);
            files.add(sc.next());
        }
        return files;
    }
     
     public static Set<String> readDirectory(String path) throws IOException {
        final String FORMAT = ".xml";
        Path dir = Paths.get(path);
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        Set<String> files = new HashSet<>();
        for (Path file : stream) {
            Scanner sc = new Scanner(file.getFileName().toString());
            sc.useDelimiter(FORMAT);
            files.add(sc.next());
        }
        return files;
    }
     
}
