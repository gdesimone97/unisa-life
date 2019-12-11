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
public class TestConsumer {
    public static void consume(TestInterface obj){
        System.out.println(obj.getClass().getSimpleName() + obj.testMethod());
    }
}
