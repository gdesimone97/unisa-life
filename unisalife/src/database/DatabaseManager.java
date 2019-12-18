/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import game.GameObjects.Destination;
import game.GameObjects.GameObject;
import game.GameObjects.Item;
import game.GameObjects.Professor;
import java.util.HashMap;
import java.util.List;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import quests.quest.Quest;

/**
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

    public static synchronized DatabaseManager getDatabaseManager() throws FileNotSetException {
        if (DatabaseManager.instance == null) {
            DatabaseManager.instance = new DatabaseManager();
        }
        return DatabaseManager.instance;
    }

    public List<Quest> getQuestsFromLevel(int level) throws ObjectNotFoundException {

        List<Quest> list = db.getNitriteDatabase().getRepository(Quest.class).find(eq("level", level)).toList();

        if (list.size() <= 0) {
            throw new ObjectNotFoundException();
        }
        return list;
    }

    public Database getDatabase() {
        return this.db;
    }

    public HashMap<Destination, GameObject> getObjectsFromLevel(int level) throws ObjectNotFoundException {
        HashMap<Destination, GameObject> returnMap = new HashMap<>();
        List<Quest> questList = this.getQuestsFromLevel(level);
        for (Quest q : questList) {
            Subject questSubject = q.getSubject();
            for (String itemName : q.getItemList()) {
                Item i = this.findItem(itemName);
                returnMap.put(new Destination(i.getX(), i.getY()), i);
            }
            Professor p = this.findProfessor(questSubject);
            returnMap.put(new Destination(p.getX(), p.getY()), p);
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
    public void close() {
        this.db.close();
    }

    public void clearDatabase() {
        this.db.clear();
    }
}
