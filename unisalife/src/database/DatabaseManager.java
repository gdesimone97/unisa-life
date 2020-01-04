/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import game.GameObjects.Block;
import game.GameObjects.Coin;
import game.GameObjects.Cook;
import game.GameObjects.Position;
import game.GameObjects.GameObject;
import game.GameObjects.Guardian;
import game.GameObjects.ImageNotLoadedException;
import game.GameObjects.Item;
import game.GameObjects.NormalPerson;
import game.GameObjects.ObjectManager;
import game.GameObjects.Professor;
import game.GameObjects.Renderable;
import game.GameObjects.Teleport;
import game.GameResources.Map;
import game.GameResources.TileMap;
import game.Interfaces.Initializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Index;
import org.dizitart.no2.NitriteCollection;
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

    @SafeVarargs
    static private <E> E[] newArray(int length, E... array) {
        return Arrays.copyOf(array, length);
    }

    protected static final String FIXEDCOLLECTIONNAME = "FLINKS";
    protected static final String DYNCOLLECTIONNAME = "DLINKS";
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
     * @throws database.NoQuestsException
     */
    public List<Quest> getQuestsFromLevel(int level) throws NoQuestsException {

        List<Quest> list = db.getNitriteDatabase().getRepository(Quest.class).find(eq("level", level)).toList();

        if (list.size() <= 0) {
            throw new NoQuestsException();
        }
        return list;
    }

    /**
     * Method to obtain all the objects for a particular level of the game.This
     * includes items and professors.Moreover, cook, coins and guardian are
     * added.
     *
     * @param level
     * @return a map of HashMaps (instances)
     * @throws ObjectNotFoundException
     * @throws game.GameObjects.ImageNotLoadedException
     * @throws database.NoQuestsException
     */
    public ConcurrentHashMap<Position, Renderable>[] getObjectsFromLevel(int level) throws ObjectNotFoundException, ImageNotLoadedException, NoQuestsException {
        List<TileMap> res = this.getTileMaps();
        int mapNum = res.size();
        ArrayList<ConcurrentHashMap<Position, Renderable>> dynArrObj = new ArrayList<>();

        for (int index = 0; index < mapNum; index++) {
            dynArrObj.add(new ConcurrentHashMap<>());
        }

        List<Quest> questList = this.getQuestsFromLevel(level);

        for (Quest q : questList) {
            Subject questSubject = q.getSubject();
            for (String itemName : q.getItemList()) {
                if (itemName.startsWith("coin")) {
                    Coin c = this.findCoin(itemName);
                    int mapId = this.findMap(itemName, DatabaseManager.DYNCOLLECTIONNAME);
                    dynArrObj.get(mapId).put(c.getScaledPosition(), c);
                } else {
                    Item i = this.findItem(itemName);
                    int mapId = this.findMap(itemName, DatabaseManager.DYNCOLLECTIONNAME);
                    dynArrObj.get(mapId).put(i.getScaledPosition(), i);
                }
            }
            Professor p = this.findProfessor(questSubject);
            int mapId = this.findMap(questSubject.getInfo(), DatabaseManager.DYNCOLLECTIONNAME);
            dynArrObj.get(mapId).put(p.getScaledPosition(), p);
        }
        try {
            // Here dynamic but known object will be retrived.
            Cook cook = this.findCook();
            int cookMapId = this.findMap(cook.getIndex(), DatabaseManager.DYNCOLLECTIONNAME);
            dynArrObj.get(cookMapId).put(cook.getScaledPosition(), cook);
        } catch (ObjectNotFoundException ex) {
            // It's not a problem if an object of this kind is not found.
        }
        try {
            Guardian guardian = this.findGuardian();
            int guardMapId = this.findMap(guardian.getIndex(), DatabaseManager.DYNCOLLECTIONNAME);
            dynArrObj.get(guardMapId).put(guardian.getScaledPosition(), guardian);
        } catch (ObjectNotFoundException ex) {
            // It's not a problem if an object of this kind is not found.
        }
        try {
            List<NormalPerson> np_list = this.findNormalPeople();
            for (NormalPerson np : np_list) {
                int npMapId = this.findMap(np.getIndex(), DatabaseManager.DYNCOLLECTIONNAME);
                dynArrObj.get(npMapId).put(np.getScaledPosition(), np);
            }

        } catch (ObjectNotFoundException ex) {
            // It's not a problem if an object of this kind is not found.
        }

        ConcurrentHashMap<Position, Renderable>[] newArray = DatabaseManager.newArray(1, dynArrObj.get(0));
        for (int i = 1; i < mapNum; i++) {
            newArray = ArrayUtils.addAll(newArray, DatabaseManager.newArray(1, dynArrObj.get(i)));
        }
        return newArray;
    }

    /**
     * Private method aimed to find the map where an item is placed.
     *
     * @param id The value of the index of the item
     * @param collection A specification (fixed or dynamic collection)
     * @return
     */
    private int findMap(String id, String collection) {

        NitriteCollection coll = db.getNitriteDatabase().getCollection(collection);
        Cursor find = null;
        try {
            find = coll.find(Filters.eq("IDOBJ", id));
        } catch (Exception ex) {

        }
        String key = find.firstOrDefault().get("IDMAP", String.class);
        return Integer.parseInt(key);
    }

    /**
     * Method to obtain the maps populated with fixed objects.
     *
     * @return an array of Maps (intances)
     * @throws ObjectNotFoundException
     * @throws ClassNotFoundException
     */
    public Map[] getMaps() throws ObjectNotFoundException, ClassNotFoundException {

        List<TileMap> res = this.getTileMaps();
        int mapNum = res.size();
        Map[] maps = new Map[mapNum];

        for (TileMap tilemap : res) {
            ConcurrentHashMap<Position, GameObject> fixed = new ConcurrentHashMap<>();
            ConcurrentHashMap<Position, Renderable> dyn = new ConcurrentHashMap<>();
            String id = String.valueOf(tilemap.getId());

            // this is to get Fixed Objects but not blocks
            org.dizitart.no2.Cursor c = db.getNitriteDatabase().getCollection(DatabaseManager.FIXEDCOLLECTIONNAME).find(Filters.eq("IDMAP", id));
            for (Document d : c) {
                String idobj = (String) d.get("IDOBJ");
                String classobj = (String) d.get("CLASSOBJ");
                ObjectRepository repo = db.getNitriteDatabase().getRepository(Class.forName(classobj));
                Index i = (Index) repo.listIndices().toArray()[0];
                GameObject o = (GameObject) repo.find(eq(i.getField(), idobj)).firstOrDefault();
                fixed.put(o.getScaledPosition(), o);
            }

            // this is to get the blocks
            try {
                for (BlockWrapper bw : db.getNitriteDatabase().getRepository(BlockWrapper.class).find(eq("map", Integer.parseInt(id))).toList()) {
                    Block b = bw.getBlock();
                    fixed.put(b.getScaledPosition(), b);
                }
                // this is to get the teleports
                for (TeleportWrapper tw : db.getNitriteDatabase().getRepository(TeleportWrapper.class).find(eq("map", Integer.parseInt(id))).toList()) {
                    Teleport t = tw.getTeleport();
                    fixed.put(t.getScaledPosition(), t);
                }
            } catch (Exception x) {
                //x.printStackTrace();

            }
            int index = tilemap.getId();
            maps[index] = new Map(tilemap, new ObjectManager(fixed, dyn), tilemap.getMiniMapPath());
        }

        // inserire anche gli oggetti dinamici del livello zero per testare
        return maps;
    }

    /**
     * Private method to obtain all the tilemaps in the database
     *
     * @return a list of tilemaps
     */
    private List<TileMap> getTileMaps() {
        List<TileMapWrapper> res = db.getNitriteDatabase().getRepository(TileMapWrapper.class).find(ObjectFilters.ALL).toList();
        List<TileMap> returnList = new ArrayList<>();
        for (TileMapWrapper t : res) {
            returnList.add(t.buildTileMap());
        }
        return returnList;
    }

    /**
     * Private method to search for a coin in the coin repo
     *
     * @param id coin id
     * @return an instance of a coin
     * @throws ObjectNotFoundException
     */
    private Coin findCoin(String id) throws ObjectNotFoundException, ImageNotLoadedException {
        Coin c = db.getNitriteDatabase().getRepository(Coin.class).find(eq("info", id)).firstOrDefault();
        if (c == null) {
            throw new ObjectNotFoundException();
        }
        c.loadImage();
        return c;
    }

    /**
     * Private method to search for an item in the item repo
     *
     * @param itemName item id
     * @return an instance of an item
     * @throws ObjectNotFoundException
     */
    private Item findItem(String itemName) throws ObjectNotFoundException, ImageNotLoadedException {
        /*
        Document d = db.getNitriteDatabase().getRepository(Item.class).find(eq("info", itemName)).;
        // %448%1024%/Sprites/note.png%appunti
        Item res = new Item((Position) d.get("p"), (String) d.get("path"), (String) d.get("info"));
         */
        Item i = db.getNitriteDatabase().getRepository(Item.class).find(eq("info", itemName)).firstOrDefault();
        if (i == null) {
            throw new ObjectNotFoundException();
        }
        i.loadImage();
        return i;
    }

    /**
     * Private method to search for a professor in the professor repo
     *
     * @param s is the subject linked to the professor
     * @return an instance of a professor
     * @throws ObjectNotFoundException
     */
    private Professor findProfessor(Subject s) throws ObjectNotFoundException, ImageNotLoadedException {
        Professor prof = db.getNitriteDatabase().getRepository(Professor.class).find(eq("subject", s.getInfo())).firstOrDefault();
        if (prof == null) {
            throw new ObjectNotFoundException();
        }
        prof.loadImage();
        return prof;
    }

    /**
     * Private method to search for the cook
     *
     * @return an instance of the cook
     */
    private Cook findCook() throws ObjectNotFoundException, ImageNotLoadedException {
        Cook c = db.getNitriteDatabase().getRepository(Cook.class).find(ObjectFilters.ALL).firstOrDefault();
        if (c == null) {
            throw new ObjectNotFoundException();
        }
        c.loadImage();
        return c;
    }

    /**
     * Private method to search for normal people in the database
     *
     * @return a list of normal person, instantiated.
     */
    private List<NormalPerson> findNormalPeople() throws ObjectNotFoundException, ImageNotLoadedException {
        List<NormalPerson> np_list = db.getNitriteDatabase().getRepository(NormalPerson.class).find(ObjectFilters.ALL).toList();
        if (np_list == null || np_list.size() == 0) {
            throw new ObjectNotFoundException();
        }
        for (NormalPerson np : np_list) {
            np.loadImage();
        }
        return np_list;
    }

    /**
     * Private method to search for the guardian
     *
     * @return an instance of the guardian
     */
    private Guardian findGuardian() throws ObjectNotFoundException, ImageNotLoadedException {
        Guardian g = db.getNitriteDatabase().getRepository(Guardian.class).find(ObjectFilters.ALL).firstOrDefault();
        if (g == null) {
            throw new ObjectNotFoundException();
        }
        g.loadImage();
        return g;
    }

    /**
     * Method to get all the subject at once. Useful to populate the booklet.
     *
     * @return a list of the subjects (instances)
     */
    public List<Subject> getSubjects() {
        return db.getNitriteDatabase().getRepository(Subject.class).find(ObjectFilters.ALL).toList();
    }

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
