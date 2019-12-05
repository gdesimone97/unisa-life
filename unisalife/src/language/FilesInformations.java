package language;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class to get the path's language and the file's format
 *
 * @author Giuseppe De Simone
 */
class FilesInformations {

    private static final String FORMAT = ".xml";
    private static final String PATH = "..//lang";

    /**
     * Method to get the files' format
     *
     * @return a string containing files' format
     */
    public static String getFORMAT() {
        return FORMAT;
    }

    /**
     * Method to get the path of language's directory
     *
     * @return a string containing the path of language's directory
     */
    public static String getPATH() {
        return PATH;
    }

}
