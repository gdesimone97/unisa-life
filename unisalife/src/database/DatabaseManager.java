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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.dizitart.no2.filters.Filters.eq;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import quests.quest.Quest;

/**
 *
 * @author alfon
 */
public class DatabaseManager {

    private static DatabaseManager instance = null;
    private Database db;

    private DatabaseManager() {
        this.db = Database.getInstance();
    }

    public static synchronized DatabaseManager getDatabaseManager() {
        if (DatabaseManager.instance == null) {
            DatabaseManager.instance = new DatabaseManager();
        }
        return DatabaseManager.instance;
    }

    public List<Quest> getQuestsFromLevel(int level) throws ObjectNotFoundException {

        Cursor<Quest> c = db.getDatabase().getRepository(Quest.class).find((ObjectFilter) eq("level", level));
        if (c.size() <= 0) {
            throw new ObjectNotFoundException();
        }
        ArrayList<Quest> returnList = new ArrayList<>();
        for (Quest q : c) {
            returnList.add(q);
        }
        return returnList;
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
        if(returnMap.size()<=0)
            throw new ObjectNotFoundException();
        return returnMap;
    }

    private Item findItem(String itemName) throws ObjectNotFoundException {
        Item res = db.getDatabase().getRepository(Item.class).find((ObjectFilter) eq("name", itemName)).firstOrDefault();
        if (res == null) {
            throw new ObjectNotFoundException();
        }
        return res;
    }

    private Professor findProfessor(Subject s) throws ObjectNotFoundException {
        Professor prof = db.getDatabase().getRepository(Professor.class).find((ObjectFilter) eq("subject", s.getInfo())).firstOrDefault();
        if (prof == null) {
            throw new ObjectNotFoundException();
        }
        return prof;
    }

    public List<Subject> getSubjects() {
        return null;
    }

    public void save(List<Saveable> elems) {
    }

    public List<Saveable> load() {
        return null;
    }
}
