package com.liferay.support.screens.test.util;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by javdaniel on 13/09/16.
 */
public class ConsoleUtilTest {

    /*
     * I have no idea how to test this at the moment.
     * TODO get back to this.
     */

    //private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public void setUp() {
        //System.setOut(new PrintStream(outContent));
        //System.setIn(new ByteArrayInputStream("Answer".getBytes()));
    }

    @Test
    public void testAskForAnswer() throws Exception {

    }

    @AfterClass
    public void tearDown() {
        System.setOut(null);
    }
}