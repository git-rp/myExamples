package com.test.log4j;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.Logger;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    static Logger log = Logger.getLogger(AppTest.class.getName());
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    @org.junit.Test
    public void testApp()
    {
        System.out.println( "Hello World!" );

        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        try {
            throw  new NullPointerException("This is the test.......");
        }catch (NullPointerException e)
        {
            log.error(e);
        }
    }
    public  void testArrayIndex() throws ArrayIndexOutOfBoundsException
    {
        log.debug("Array Index.....");

        try {
            throw  new ArrayIndexOutOfBoundsException("This is the test.......");
        }catch (ArrayIndexOutOfBoundsException e)
        {
            log.error(e);
        }
    }

}
