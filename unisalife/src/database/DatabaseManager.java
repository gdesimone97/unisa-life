/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import saving.Saveable;
import exam.booklet.Subject;
import game.GameObjects.Destination;
import game.GameObjects.GameObject;
import game.GameObjects.Item;
import game.GameObjects.Professor;
import java.util.HashMap;
import java.util.List;
import org.dizitart.no2.WriteResult;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.dizitart.no2.objects.ObjectRepository;
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

        List<Quest> list = db.getDatabase().getRepository(Quest.class).find(eq("level", level)).toList();
        if (list.size() <= 0) {
            throw new ObjectNotFoundException();
        }
        return list;
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
        Item res = db.getDatabase().getRepository(Item.class).find(eq("name", itemName)).firstOrDefault();
        if (res == null) {
            throw new ObjectNotFoundException();
        }
        return res;
    }

    private Professor findProfessor(Subject s) throws ObjectNotFoundException {
        Professor prof = db.getDatabase().getRepository(Professor.class).find(eq("subject", s.getInfo())).firstOrDefault();
        if (prof == null) {
            throw new ObjectNotFoundException();
        }
        return prof;
    }

    public List<Subject> getSubjects() {
        return db.getDatabase().getRepository(Subject.class).find().toList();
    }

    public void save(List<Saveable> elems) throws ErrorWhileSavingException {
        ObjectRepository repo = db.getDatabase().getRepository(Saveable.class);
        repo.drop();
        WriteResult ws = repo.insert(elems.toArray());
        if (ws.getAffectedCount() != elems.size()) {
            throw new ErrorWhileSavingException();
        }

    }

    public List<Saveable> load() {
        return db.getDatabase().getRepository(Saveable.class).find().toList();
    }

    public boolean isSaved() {
        return this.load().size() >= 0;
    }

    public void close() {
        this.db.close();
    }
}
