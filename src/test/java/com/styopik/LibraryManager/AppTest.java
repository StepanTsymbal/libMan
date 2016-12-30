package com.styopik.LibraryManager;

import com.styopik.librarymanager.controller.LibraryController;
import com.styopik.librarymanager.model.Book;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	
	LibraryController controller = new LibraryController();

    public AppTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( AppTest.class );
    }


    public void testBook() {
    	
    	Book expectedBook = new Book("\"name\"", "author");
    	
    	String expectedName = expectedBook.getName();
    	String expectedAuthor = expectedBook.getAuthor();
    	
    	assertEquals(expectedName, "\"name\"");
    	assertEquals(expectedAuthor, "author");
    	

    }
    
    public void testStringToBook() {
    	
    	String s = "\"name\" author";
    	
        assertEquals(controller.stringToBook(s), new Book("\"name\"", "author"));
    }
}