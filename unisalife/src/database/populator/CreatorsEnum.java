/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

/**
 *
 * @author cmarino
 */
public enum CreatorsEnum {
    item("item"){
        @Override
         public SaveableCreator getFactory(){
            return new ItemFactory();
        }
    },professor("professor"){
        @Override
        public SaveableCreator getFactory(){
            return new ProfessorFactory();
        }
    }, subject("subject"){
        @Override
        public SaveableCreator getFactory(){
            return new QuestFactory();
        }
    }, quest("quest"){
        @Override
        public SaveableCreator getFactory(){
            return new SubjectFactory();
        }
    };
    
    private final String type;
    
    CreatorsEnum( String type ){
        this.type = type;
    }
    
    public abstract SaveableCreator getFactory();
    
    public String getType(){
        return this.type;
    }
    
    
    
}
