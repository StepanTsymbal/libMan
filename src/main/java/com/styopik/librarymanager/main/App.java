package com.styopik.librarymanager.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.styopik.librarymanager.service.BookService;
import com.styopik.librarymanager.service.impl.BookServiceImpl;

public class App {
	
    public static void main( String[] args ) {

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");       
        BookService bookService = (BookServiceImpl) context.getBean("bookServiceImpl");
        
        bookService.manage();
    }
}
