/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import game.GameObjects.Position;
import game.GameObjects.GameObject;
import game.GameObjects.Item;
import game.GameObjects.Professor;
import java.util.HashMap;
import java.util.List;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import quests.quest.Quest;

/**
 * Manager class to handle database interction with the game. It allows to
 * retrieve objects from the database.
 *
 * @author alfon
 */
public class DatabaseManager {

    private static DatabaseManager instance = null;
    private Database db;
    private final String path = "..//db/game.db";

    private DatabaseManager() throws FileNotSetException {
        Database.setPath(path);
        this.db = Database.getInstance();
    }

    /**
     * Method to obtain the singleton instance of the manager
     *
     * @return the DatabaseManager instance
     * @throws FileNotSetException
     */
    public static synchronized DatabaseManager getDatabaseManager() throws FileNotSetException {
        if (DatabaseManager.instance == null) {
            DatabaseManager.instance = new DatabaseManager();
        }
        return DatabaseManager.instance;
    }

    /**
     * Method to return the database instance
     *
     * @return
     */
    public Database getDatabase() {
        return this.db;
    }

    /**
     * Method to get all the quests for a given level.
     *
     *
     * @param level
     * @return The list of quests (instances)
     * @throws ObjectNotFoundException
     */
    public List<Quest> getQuestsFromLevel(int level) throws ObjectNotFoundException {

        List<Quest> list = db.getNitriteDatabase().getRepository(Quest.class).find(eq("level", level)).toList();

        if (list.size() <= 0) {
            throw new ObjectNotFoundException();
        }
        return list;
    }

    /**
     * Method to obtain all the objects for a particular level of the game. This
     * includes items and professors. All the constant objects are added (like
     * cook, vending machines ecc).
     *
     * @param level
     * @return a list of those objects (instances)
     * @throws ObjectNotFoundException
     */
    public HashMap<Position, GameObject> getObjectsFromLevel(int level) throws ObjectNotFoundException {
        HashMap<Position, GameObject> returnMap = new HashMap<>();
        List<Quest> questList = this.getQuestsFromLevel(level);
        for (Quest q : questList) {
            Subject questSubject = q.getSubject();
            for (String itemName : q.getItemList()) {
                Item i = this.findItem(itemName);
                returnMap.put(i.getTiledPosition(), i);
            }
            Professor p = this.findProfessor(questSubject);
            returnMap.put(p.getTiledPosition(), p);
        }
        if (returnMap.size() <= 0) {
            throw new ObjectNotFoundException();
        }
        return returnMap;
    }

    private Item findItem(String itemName) throws ObjectNotFoundException {
        Item res = db.getNitriteDatabase().getRepository(Item.class).find(eq("info", itemName)).firstOrDefault();
        if (res == null) {
            throw new ObjectNotFoundException();
        }
        return res;
    }

    private Professor findProfessor(Subject s) throws ObjectNotFoundException {
        Professor prof = db.getNitriteDatabase().getRepository(Professor.class).find(eq("subject.subject", s.getInfo())).firstOrDefault();
        if (prof == null) {
            throw new ObjectNotFoundException();
        }
        return prof;
    }

    /**
     * Method to get all the subject at once. Useful to populate the booklet.
     * @return a list of the subjects (instances)
     */
    public List<Subject> getSubjects() {
        return db.getNitriteDatabase().getRepository(Subject.class).find().toList();
    }

    /*
    public void save(List<Saveable> elems) throws ErrorWhileSavingException {
        List<SaveableObject> finalList = elems.stream().map(e -> new SaveableObject(e)).collect(Collectors.toList());
        finalList.stream().forEach((obj) -> {
            System.out.println(obj);
        });
        ObjectRepository repo = db.getNitriteDatabase().getRepository(SaveableObject.class);
        repo.remove(ObjectFilters.ALL);
        WriteResult ws = repo.insert(finalList.toArray());
        if (ws.getAffectedCount() != elems.size()) {
            throw new ErrorWhileSavingException();
        }
    }

    public List<Saveable> load() {
        Cursor<SaveableObject> c = db.getNitriteDatabase().getRepository(SaveableObject.class).find(ObjectFilters.ALL);
        System.out.println(c.size());
        List<Saveable> returnList = new ArrayList<>();
        for (SaveableObject obj : c) {
            System.out.println(obj);
            System.out.println(obj.getInnerObj());
            returnList.add((Saveable) obj.getInnerObj());
        }
        return returnList; //l.stream().map(e -> e.getInnerObj()).collect(Collectors.toList());
    }
     
    public boolean isSaved() {
        return this.load().size() >= 0;
    }
     */
    
    /**
     * Method to close the database
     */
    public void close() {
        this.db.close();
    }

    /**
     * Method to clear the databse (it deletes it).
     */
    public void clearDatabase() {
        this.db.clear();
    }
}
