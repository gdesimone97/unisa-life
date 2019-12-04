/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import question.Question;

/**
 *
 * @author liovi
 */
public class ExamTest {
    
    public ExamTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of verifyAnswer method, of class Exam.
     */
    @Test
    public void testVerifyAnswer() {
        System.out.println("verifyAnswer");
        boolean answer = false;
        int seconds = 0;
        int level = 0;
        Exam instance = null;
        instance.verifyAnswer(answer, seconds, level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuestion method, of class Exam.
     */
    @Test
    public void testGetQuestion() {
        System.out.println("getQuestion");
        Exam instance = null;
        Question expResult = null;
        Question result = instance.getQuestion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class Exam.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Exam instance = null;
        int expResult = 0;
        double result = instance.getScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
