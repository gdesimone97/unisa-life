/**
 * Sprint #1
 * User Story: (13) As a player I would like more languages
 * Task: (7) Structure of the language package
 * Team Members: Alfonso De Masi, Giuseppe De Simone
 */
package language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete class that implements a cache in the proxy patter. It also uses the
 * Singleton Pattern to have just one cache.
 *
 * @author alfon
 */
public class CacheFileTextFinder extends FileTextFinder {

    /**
     * Inner class to implement elements to be stored in a TreeMap in order to
     * prioritize cache elements.
     */
    class ValueNode {

        int freq;
        List<String> messages;

        ValueNode(List<String> messages) {
            this.freq = 0;
            this.messages = messages;
        }

        public void increase() {
            this.freq++;
        }

    }

    private static CacheFileTextFinder instance = null;
    private ConcreteFileTextFinder concreteFileTextFinder;
    private HashMap<String, ValueNode> cache;

    private static final int MAXSIZE = 100;
    private static final int TRESH_FREQ = 3;
    private static final int REMOVAL_NUM = 5;

    /**
     * Private Constructor
     */
    private CacheFileTextFinder() throws FileNotSetException {
        this.concreteFileTextFinder = ConcreteFileTextFinder.getConcreteFileTextFinder();
        this.cache = new HashMap<>(CacheFileTextFinder.MAXSIZE);
    }

    /**
     * Static getter method to istantiate a CacheFileTextFinder
     *
     * @return the current istance of the CacheFileTextFinder
     */
    public static synchronized CacheFileTextFinder getCacheFileTextFinder() throws FileNotSetException {
        if (instance == null)
            instance = new CacheFileTextFinder();
        return instance;
    }

    /**
     * Method to perform the query. A search in the file is performed if the
     * strings to find are not present in memory.
     *
     * @return a list of strings
     */
    @Override
    public List<String> getString() throws StringNotFoundException {
        List<String> returnList = null;
        String exp = this.computeExpression();
        ValueNode returnNode = this.cache.get(exp);
        if (returnNode == null) {
            returnList = this.concreteFileTextFinder.getString();
            this.addToCache(exp, returnList);
        } else {
            this.updateCache(returnNode);
        }
        return returnList;
    }

    /**
     * Method to cache newly discovered elements. It performrs a clean-up of
     * some elements if needed.
     *
     * @param exp The key of the element to be stored
     * @param elem The list of messages to store
     */
    private void addToCache(String exp, List<String> elem) {
        // when size exceeds the MAXSIZE, remove some elements
        int size = this.cache.size();
        if (size >= CacheFileTextFinder.MAXSIZE) {
            List<String> toRemove = new ArrayList<>();
            int visited = 0;

            for (Map.Entry<String, ValueNode> v : this.cache.entrySet()) {
                if (v.getValue().freq < CacheFileTextFinder.TRESH_FREQ || (size - visited) >= CacheFileTextFinder.REMOVAL_NUM) {
                    // i take an item if it's a low frequency one or i'm running out of elements to remove
                    toRemove.add(v.getKey());
                    if (toRemove.size() >= CacheFileTextFinder.REMOVAL_NUM) {
                        // if i found the amount of element to remove, i can exit
                        break;
                    }
                }
                visited++;
            }

            for (String s : toRemove) {
                this.cache.remove(s);
            }

        }
        // Insert the new node in the cache
        this.cache.put(exp, new ValueNode(elem));
    }

    /**
     * Method to update the frequency of an element in the cache.
     *
     * @param v The NodeValue of the element to update.
     */
    private void updateCache(ValueNode v) {
        v.increase();
    }

    /**
     * Method to clean up the cache and start with a new one
     */
    void cleanCache() {
        this.cache = null;
        this.cache = new HashMap<>(CacheFileTextFinder.MAXSIZE);
    }
}
