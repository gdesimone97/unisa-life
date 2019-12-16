/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Item;
import game.GameObjects.Professor;
import static org.dizitart.no2.IndexOptions.indexOptions;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.Nitrite;
import quests.quest.Quest;
import saving.Saveable;

/**
 *
 * @author alfon
 */
public class Database {

    private static String path = null;
    private Nitrite db;
    private static Database instance;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Database.path = path;
    }

    private Database() {
        this.db = Nitrite.builder().compressed().filePath(Database.getPath()).openOrCreate("group08", "hntbae");
        init();
    }

    private void init() {
        if (this.db.listRepositories().size() <= 0) {
            db.getRepository(Item.class).createIndex("name", indexOptions(IndexType.Unique));
            db.getRepository(Professor.class).createIndex("subject", indexOptions(IndexType.Unique));
            db.getRepository(Quest.class).createIndex("level", indexOptions(IndexType.NonUnique));
            db.getRepository(Saveable.class);
        }
    }

    public static Database getInstance() throws FileNotSetException {
        if (Database.getPath() == null)
            throw new FileNotSetException();
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return instance;
    }

    public Nitrite getDatabase() {
        return this.db;
    }

}
