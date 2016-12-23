package com.styopik.LibraryManager;

import com.styopik.librarymanager.model.Book;
import com.styopik.librarymanager.service.impl.BookServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	
	BookServiceImpl bookService = new BookServiceImpl();

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
    	
        assertEquals(bookService.stringToBook(s), new Book("\"name\"", "author"));
    }
}