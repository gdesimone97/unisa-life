/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import org.dizitart.no2.Document;
import org.dizitart.no2.objects.ObjectRepository;
import quests.QuestsManager;

/**
 * This class is used to populate the internal database with game objects
 * initially described in an easily editable and easily constructable text file.
 *
 * @author cmarino
 */
public class Populator {

    private final String filepath;
    private Database db;

    /**
     *
     * @param filepath The filepath describing the location of the text file
     * containing the list of all game objects to create.
     * @throws FileNotSetException
     */
    public Populator(String filepath) throws FileNotSetException {

        this.filepath = filepath;
        //Database.setPath("../prova.db");
        this.db = DatabaseManager.getDatabaseManager().getDatabase();
    }

    /**
     * This method parse the data file line by line, decide which factory call
     * for every line/object ecounters in data file.
     *
     * @throws FileNotFoundException If the filepath doesn't lead to an
     * effective file.
     * @throws IOException If the file can't be opened.
     * @throws InvalidGameDataFormatException If a line in the file doesn't
     * follow the basic structure "[objectType] [parameters]"
     * @throws FileNotSetException If a file hasn't been set in the Database
     * class.
     */
    public void populate() throws FileNotFoundException, IOException, InvalidGameDataFormatException, FileNotSetException {

        QuestsManager.getInstance().init();
        BufferedReader r = new BufferedReader(new FileReader(filepath));
        String line = r.readLine();
        
        while (line != null) {
            
            String[] tokens = line.split(" ", 3);
            if(tokens[0].compareTo("#")!=0){
                if (!line.matches("^(?!\\s*$).+[' ']{1}[%]{1}.*")) {
                    throw new InvalidGameDataFormatException();
                }

                String type = tokens[0].toLowerCase();
                String arguments = tokens[1];

                StorableCreator s = CreatorsEnum.valueOf(type).getFactory();
                Storable sitem = s.create(arguments);
                ObjectRepository repo = db.getNitriteDatabase().getRepository(sitem.getClass());
                repo.insert(sitem);
                if (tokens.length == 3) {
                    StringTokenizer subst = new StringTokenizer(tokens[2], StorableCreator.DELIMETER);
                    String mapTok = subst.nextToken();
                    String repoTok = subst.nextToken();
                    db.getNitriteDatabase().getCollection(repoTok.equals("d") ? DatabaseManager.DYNCOLLECTIONNAME : DatabaseManager.FIXEDCOLLECTIONNAME).insert(
                            Document.createDocument("IDMAP", mapTok).put("IDOBJ", sitem.getIndex()).put("CLASSOBJ", sitem.getClass().getName())
                    );

                }
            }
            line = r.readLine();
            
        }

        db.close();

    }

    /**
     *
     * @return the path of the data file.
     */
    public String getPath() {
        return this.filepath;
    }

    /**
     * The main method is executed when there's the need to populate the database.
     * @param args Unused, Java convention
     * @throws FileNotSetException 
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        
        new Populator("data.txt").populate();
  

    }
}
