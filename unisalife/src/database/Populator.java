/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidGameDataFormatException;
import saving.Saveable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.dizitart.no2.objects.ObjectRepository;

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

            System.out.println("Inserting object of type " + sitem.getClass() + " = " + sitem);
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

}
