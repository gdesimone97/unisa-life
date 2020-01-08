/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 * This enumeration works as a map between the objects that need to be managed
 * (load/save) by the internal database and the factory method to create them.
 *
 * @author cmarino
 */
public enum CreatorsEnum {

    /**
     * Returns a factory for the class Item
     */
    item("item") {
        @Override
        public StorableCreator getFactory() {
            return new ItemFactory();
        }
    },

    /**
     * Returns a factory for the class Professor
     */
    professor("professor") {
        @Override
        public StorableCreator getFactory() {
            return new ProfessorFactory();
        }
    },

    /**
     * Returns a factory for the class Coin
     */
    coin("coin") {
        @Override
        public StorableCreator getFactory() {
            return new CoinFactory();
        }
    },

    /**
     * Returns a factory for the class Subject
     */
    subject("subject") {
        @Override
        public StorableCreator getFactory() {
            return new SubjectFactory();
        }
    },

    /**
     * Returns a factory for the class Quest
     */
    quest("quest") {
        @Override
        public StorableCreator getFactory() {
            return new QuestFactory();
        }
    },

    /**
     * Returns a factory for the class Cook
     */
    cook("cook") {
        @Override
        public StorableCreator getFactory() {
            return new CookFactory();
        }
    },

    /**
     * Returns a factory for the class Guardian
     */
    guardian("guardian") {
        @Override
        public StorableCreator getFactory() {
            return new GuardianFactory();
        }
    },

    /**
     * Returns a factory for the class DistributorWrapper
     */
    distributor("distributor") {
        @Override
        public StorableCreator getFactory() {
            return new DistributorWrapperFactory();
        }
    },

    /**
     * Returns a factory for the class BlockWrapper
     */
    block("block") {
        @Override
        public StorableCreator getFactory() {
            return new BlockWrapperFactory();
        }
    },

    /**
     * Returns a factory for the class TileMapWrapper
     */
    tilemap("tilemap") {
        @Override
        public StorableCreator getFactory() {
            return new TileMapWrapperFactory();
        }
    },

    /**
     * Returns a factory for the class TeleportWrapper
     */
    teleport("teleport"){
        @Override
        public StorableCreator getFactory() {
            return new TeleportWrapperFactory();
        }
    },

    /**
     * Returns a factory for the class NormalPerson
     */
    normalperson("normalperson"){
        @Override
        public StorableCreator getFactory() {
            return new NormalPersonFactory();
        }
    },

    /**
     * Returns a factory for the class BedWrapper
     */
    bed("bed"){
        @Override
        public StorableCreator getFactory(){
            return new BedWrapperFactory();
        }
    };

    private final String type;

    CreatorsEnum(String type) {
        this.type = type;
    }

    /**
     *
     * @return the Factory of the desired class
     */
    public abstract StorableCreator getFactory();

    /**
     *
     * @return
     */
    public String getType() {
        return this.type;
    }

}
