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
 * @author alfon
 */
public class Database {

    private static String path = null;
    private Nitrite db = null;
    private static Database instance;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Database.path = path;
    }

    private Database() {
        if (this.db == null) {
            this.db = Nitrite.builder().compressed().filePath(Database.getPath()).openOrCreate("group08", "hntbae");
        }
    }

    public static Database getInstance() throws FileNotSetException {
        if (Database.getPath() == null) {
            throw new FileNotSetException();
        }
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return instance;
    }

    public Nitrite getNitriteDatabase() {
        if (this.db.isClosed()) {
            this.db = this.db = Nitrite.builder().compressed().filePath(Database.getPath()).openOrCreate("group08", "hntbae");
        }
        return this.db;
    }

    public void close() {
        this.db.commit();
        this.db.close();
    }

    public void clear() {
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
