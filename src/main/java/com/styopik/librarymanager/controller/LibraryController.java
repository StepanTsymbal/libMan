package com.styopik.librarymanager.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;

import com.styopik.librarymanager.model.Book;
import com.styopik.librarymanager.service.BookService;

public class LibraryController {
	
	private BookService bookService;

	private Scanner in;
	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public void manage() {
		
        in = new Scanner(System.in);
        
        while(true) {
        
	        String s = in.nextLine();
	        
	        switch (s.split(" ")[0]) {
		        case "all":
		        	getAllBooks(s);
		    		break;
		        case "add":
		        	addBook(s);
		        	break;
		        case "remove":
		        	removeBook(s);
		        	break;
		        case "edit":
		        	editBook(s);
		        	break;
		        default:
		        	getInstruction();
		        }
        }
	}
	
	public String getName(String s) {
		return s.substring(s.indexOf(" ") + 1);
	}
	
	private void getServiceMessage() {
    	System.out.println("In order to add new book:");
    	System.out.println("add [space] [quote] [book name] [quote] [space] [author]");
    	System.out.println("Try to add one more time:");
	}
	
	private void getInstruction() {
    	System.out.println("Please, enter only valid commands:");
    	System.out.println("1. \"all books\" for all books showing");
    	System.out.println("2. \"add [space] [quote] [book name] [quote] [space] [author]\" for book's adding");
    	System.out.println("3. \"remove [space] [quote] [book name] [quote]\" for book's removing");
    	System.out.println("4. \"edit [space] [quote] [book name] [quote]\" for book's editing");
	}
	
	public Book stringToBook(String s) {
		
		String name;
		String author;
		
		if (s.indexOf('"') == -1) {
			name = "";
		} else {
			name = s.substring(s.indexOf('"'), s.lastIndexOf('"') + 1);
		}

		author = s.substring(s.lastIndexOf('"') + 2);
		
		return new Book(name, author);
	}

	private void getAllBooks(String s) {
    	System.out.println("Our books:");
    	for(Book bookTemp : bookService.findAll()) {
    		System.out.println(bookTemp);
    	}
	}

	private void addBook(String s) {
		Book book;
    	String name;
    	String author;
    	
    	while (true) {
    		try {
	        	book = stringToBook(s);
	        	name = book.getName();
	        	author = book.getAuthor();
	        	
	        	if ((name.length() * author.length() != 0) && (StringUtils.countOccurrencesOf(s, "\"") == 2)) {
	        		break;
	        	}
	        	
	        	getServiceMessage();

    		} catch (StringIndexOutOfBoundsException e) {
    			getServiceMessage();
    		}

        	s = in.nextLine();
    	}
    	
    	bookService.addBook(book);
    	System.out.println("book " + book.getName() + " " + book.getAuthor() + " has been added");
	}

	private void removeBook(String s) {
		Book book;
    	String name = getName(s);
    	List<Book> listBook = bookService.findBooksByName(name);
    	
    	if (listBook.size() > 1) {
    		int id = 0;
    		
    		showListByName(listBook, "deleted");
    		while (true) {
        		try {
            		id = Integer.parseInt(in.nextLine());
            		book = bookService.findById(id);
            		bookService.removeBookById(id);
            		System.out.println("book " + book + " has been removed");
            		break;
            		
        		} catch (EmptyResultDataAccessException e) {
            		System.out.println("A book with ID " + id + " has not been found. Try one more time:");
        		} catch (NumberFormatException ex) {
        			System.out.println("Incorrect ID format! Try one more time:");
        		}
    		}
    	} else if (listBook.size() == 1) {
    		bookService.removeBookById(listBook.get(0).getId());
    		System.out.println("book " + listBook.get(0) + " has been removed");
    	} else {
    		System.out.println("A book with such name has not been found.");
    	}
	}

	private void editBook(String s) {
		Book book;
    	String newName;
    	String name = getName(s);
    	List<Book> listBook = bookService.findBooksByName(name);
    	
    	if (listBook.size() > 1) {
    		int id = 0;
    		
    		showListByName(listBook, "edited");
    		while (true) {
        		try {
        			id = Integer.parseInt(in.nextLine());
            		book = bookService.findById(id);
            		System.out.println("Input new name:");
            		newName = in.nextLine();
            		bookService.editBookNameById(newName, id);
                	System.out.println("book " + book + " has been edited");
                	break;
            		
        		} catch (EmptyResultDataAccessException e) {
        			System.out.println("A book with ID " + id + " has not been found. Try one more time:");
        		} catch (NumberFormatException ex) {
        			System.out.println("Incorrect ID format! Try one more time:");
        		}
    		}

    	} else if (listBook.size() == 1) {
    		System.out.println("Input new name:");
    		newName = in.nextLine();
    		bookService.editBookNameById(newName, listBook.get(0).getId());
    		System.out.println("book " + listBook.get(0) + " has been edited");
    	} else {
    		System.out.println("A book with such name has not been found.");
    	}     	
	}
	
	private void showListByName(List<Book> listBook, String postfix) {
		int i = 1;
		
		System.out.println("There are several books with such name:");
    	for(Book bookTemp : listBook) {
    		System.out.println(i++ + ". " + bookTemp);
    	}
		System.out.printf("Please, input an ID of the book you want to be %s:\n", postfix);
	}

}
