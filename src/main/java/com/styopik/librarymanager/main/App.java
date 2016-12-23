package com.styopik.librarymanager.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.styopik.librarymanager.controller.LibraryController;

public class App {
	
    public static void main( String[] args ) {

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");       
        LibraryController controller = (LibraryController) context.getBean("libraryController");
        
        controller.manage();
    }
}
