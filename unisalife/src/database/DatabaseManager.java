/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import game.GameObjects.Block;
import game.GameObjects.Cook;
import game.GameObjects.Position;
import game.GameObjects.GameObject;
import game.GameObjects.Guardian;
import game.GameObjects.Item;
import game.GameObjects.ObjectManager;
import game.GameObjects.Professor;
import game.GameResources.Map;
import game.GameResources.TileMap;
import game.Interfaces.Initializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.dizitart.no2.Document;
import org.dizitart.no2.Index;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import quests.quest.Quest;

/**
 * Manager class to handle database interction with the game. It allows to
 * retrieve objects from the database.
 *
 * @author alfon
 */
public class DatabaseManager implements Initializable {

    private static final String FIXEDCOLLECTIONNAME = "FLINKS";
    private static final String DYNCOLLECTIONNAME = "DLINKS";
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
     * includes items and professors.
     *
     * @param level
     * @return a map of those objects (instances)
     * @throws ObjectNotFoundException
     */
    public ConcurrentHashMap<Position, GameObject>[] getObjectsFromLevel(int level) throws ObjectNotFoundException, ErrorWhileSavingException {
        List<TileMap> res = this.getTileMaps();
        int mapNum = res.size();
        List<ConcurrentHashMap<Position, GameObject>> dynArrObj = new ArrayList<>();

        List<Quest> questList = this.getQuestsFromLevel(level);

        for (Quest q : questList) {
            Subject questSubject = q.getSubject();
            for (String itemName : q.getItemList()) {
                Item i = this.findItem(itemName);
                int mapId = this.findMap(itemName, DatabaseManager.DYNCOLLECTIONNAME);
                dynArrObj.get(mapId).put(i.getScaledPosition(), i);
            }
            Professor p = this.findProfessor(questSubject);
            int mapId = this.findMap(questSubject.getInfo(), DatabaseManager.DYNCOLLECTIONNAME);
            dynArrObj.get(mapId).put(p.getScaledPosition(), p);
        }

        Cook cook = this.findCook();
        int cookMapId = this.findMap(cook.getIndex(), DatabaseManager.FIXEDCOLLECTIONNAME);
        dynArrObj.get(cookMapId).put(cook.getScaledPosition(), cook);

        Guardian guardian = this.findGuardian();
        int guardMapId = this.findMap(guardian.getIndex(), DatabaseManager.FIXEDCOLLECTIONNAME);
        dynArrObj.get(guardMapId).put(guardian.getScaledPosition(), guardian);

        /*
        if ((dynArrObj.stream().filter((obj) -> obj.size() <= 0).count()) > 0) {
            throw new ErrorWhileSavingException();
        }
         */
        return (ConcurrentHashMap<Position, GameObject>[]) dynArrObj.toArray();
    }

    private int findMap(String id, String collection) {
        return Integer.parseInt(db.getNitriteDatabase()
                .getCollection(collection)
                .find(eq("IDOBJ", id))
                .firstOrDefault()
                .get("IDMAP", String.class));
    }

    public Map[] getMaps() throws ObjectNotFoundException, ClassNotFoundException {

        List<TileMap> res = this.getTileMaps();
        int mapNum = res.size();
        Map[] maps = new Map[mapNum];

        for (TileMap tilemap : res) {
            ConcurrentHashMap<Position, GameObject> fixed = new ConcurrentHashMap<>();
            ConcurrentHashMap<Position, GameObject> dyn = new ConcurrentHashMap<>();

            String id = String.valueOf(tilemap.getId());

            // this is to get Fixed Objects but not blocks
            org.dizitart.no2.Cursor c = db.getNitriteDatabase().getCollection(DatabaseManager.FIXEDCOLLECTIONNAME).find(Filters.eq("IDMAP", id));
            for (Document d : c) {
                String idobj = d.get("IDOBJ", String.class);
                String classobj = d.get("CLASSOBJ", String.class);
                ObjectRepository repo = db.getNitriteDatabase().getRepository(Class.forName(classobj));
                Index i = (Index) repo.listIndices().toArray()[0];
                GameObject o = (GameObject) repo.find(eq(i.getField(), idobj));
                fixed.put(o.getScaledPosition(), o);
            }

            // this is to get the blocks
            for (BlockWrapper bw : db.getNitriteDatabase().getRepository(BlockWrapper.class).find(eq("map", id)).toList()) {
                Block b = bw.getBlock();
                fixed.put(b.getScaledPosition(), b);
            }
            maps[tilemap.getId()] = new Map(tilemap, new ObjectManager(fixed, dyn));
        }
        return maps;
    }

    private List<TileMap> getTileMaps() {
        List<TileMap> res = db.getNitriteDatabase().getRepository(TileMap.class).find(ObjectFilters.ALL).toList();
        return res;
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

    private Cook findCook() {
        return db.getNitriteDatabase().getRepository(Cook.class).find(ObjectFilters.ALL).firstOrDefault();
    }

    private Guardian findGuardian() {
        return db.getNitriteDatabase().getRepository(Guardian.class).find(ObjectFilters.ALL).firstOrDefault();
    }

    /**
     * Method to get all the subject at once. Useful to populate the booklet.
     *
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

    @Override
    public void init() {

    }
}
