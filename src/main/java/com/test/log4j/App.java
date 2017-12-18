package com.test.log4j;

import org.apache.log4j.Logger;


/**
 * Hello world!
 *
 */
public class App 
{
    /* Get actual class name to be printed on */
    static Logger log = Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        try {
            test();
        }catch (NullPointerException e)
        {
            log.error(e);
        }
    }
    public static void test() throws NullPointerException
    {
        log.debug("Here is DEBUG");
        log.debug("this is the etest");
        throw  new NullPointerException("This is the test.......");
    }

}
