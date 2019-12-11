/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.justfortesting;

/**
 *
 * @author alfon
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TestConsumer.consume( () -> { return "This is my info";} );
        
    }
    
}
