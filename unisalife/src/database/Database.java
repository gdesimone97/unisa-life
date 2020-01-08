/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import org.dizitart.no2.Nitrite;

/**
 *
 * @author Alfono De Masi
 */
class Database {

    private static String path = null;
    private Nitrite db = null;
    private static Database instance;

    /**
     * get the path where the database is actually saved
     *
     * @return
     */
    static String getPath() {
        return path;
    }

    /**
     * Set a path where the database has to be saved
     *
     * @param path
     */
    static void setPath(String path) {
        Database.path = path;
    }

    /**
     * Private constructor of the databse
     */
    private Database() {
        if (this.db == null) {
            this.db = Nitrite.builder().compressed().filePath(Database.getPath()).openOrCreate("group08", "hntbae");
        }
    }

    /**
     * Returns the singleton instance of the database
     *
     * @return the instance
     * @throws FileNotSetException
     */
    static Database getInstance() throws FileNotSetException {
        if (Database.getPath() == null) {
            throw new FileNotSetException();
        }
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return instance;
    }

    /**
     * Method to obtain the proper Nitrite database used.
     *
     * @return the instance of the Nitrite database
     */
    Nitrite getNitriteDatabase() {
        if (this.db.isClosed()) {
            this.db = this.db = Nitrite.builder().compressed().filePath(Database.getPath()).openOrCreate("group08", "hntbae");
        }
        return this.db;
    }

    /**
     * Method to close the databsase
     */
    void close() {
        this.db.commit();
        this.db.close();
    }

    /**
     * Method to clear the database, deleting all the objects and all the files.
     */
    void clear() {
        for (String s : this.db.listRepositories()) {
            try {
                this.db.getRepository(s, Class.forName(s)).drop();
            } catch (ClassNotFoundException ex) {

            }
        }
        File f = new File(Database.getPath());
        f.delete();

    }
}
