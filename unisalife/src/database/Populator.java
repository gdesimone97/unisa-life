/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidGameDataFormatException;
import game.GameObjects.Coin;
import game.GameObjects.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
     * follow the basic structure <objectType> <parameters>
     * @throws FileNotSetException If a file hasn't been set in the Database
     * class.
     */
    public void populate() throws FileNotFoundException, IOException, InvalidGameDataFormatException, FileNotSetException {
        
        QuestsManager.getInstance().init();
        BufferedReader r = new BufferedReader(new FileReader(filepath));
        String line = r.readLine();

        while (line != null) {

            String[] tokens = line.split(" ", 2);

            if (!line.matches("^(?!\\s*$).+[' ']{1}[%]{1}.*")) {
                throw new InvalidGameDataFormatException();
            }

            String type = tokens[0].toLowerCase();
            String arguments = tokens[1];

            StorableCreator s = CreatorsEnum.valueOf(type).getFactory();
            Storable sitem = s.create(arguments);
            Class c = type.equals("Coin") ? Item.class : sitem.getClass();
            System.out.println("Inserting object of type " + c + " = " + sitem);
            ObjectRepository repo = db.getNitriteDatabase().getRepository(sitem.getClass());
            repo.insert(sitem);

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

    public static void main(String[] args) throws FileNotSetException, Exception {
        //        if (args.length < 1) {
        //            throw new FileNotSetException("No filename specified");
        //        }
        //
        //        String filePath = args[0];
        //        try{
        //            new Populator(filePath).populate();
        //        }catch(Exception ex){
        //            throw new Exception("Error while populating");
        //        }
        //        
        //
        //        System.out.println("Database populated");
        System.out.println(System.getProperty("user.dir"));
        new Populator("data.txt").populate();

    }
}
