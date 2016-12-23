package com.styopik.librarymanager.service.impl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.util.StringUtils;

import com.styopik.librarymanager.dao.BookDAO;
import com.styopik.librarymanager.model.Book;
import com.styopik.librarymanager.service.BookService;

public class BookServiceImpl implements BookService {
	
	private BookDAO bookDao;
	
	public void setBookDao(BookDAO bookDAO) {
		this.bookDao = bookDAO;
	}

	public void addBook(Book book) {
		
		bookDao.addBook(book);
	}

	public void removeBookById(int id) {
		
		bookDao.deleteBook(id);
	}

	public void removeBookByName(String name) {
		
		bookDao.deleteBookByName(name);
	}

	public List<Book> findAll() {
		
		return bookDao.findAll();
	}

	public List<Book> findBooksByName(String name) {
		
		return bookDao.findByName(name);
	}

	public void editBookName(String oldName, String newName) {
		
		bookDao.editBookByName(oldName, newName);
	}

	public void editBookNameById(String newName, int id) {
		
		bookDao.editBookById(newName, id);
	}
	
	public void manage() {
		
        Scanner in = new Scanner(System.in);
        
        while(true) {
        
	        String s = in.nextLine();
	        
	        switch (s.split(" ")[0]) {
	        
		        case "all":
		        	
		        	System.out.println("Our books:");
		        	for(Book bookTemp : findAll()) {
		        		System.out.println(bookTemp);
		        	}
		    		break;
		    		
		        case "add":
		        	
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
		        	addBook(book);
		        	System.out.println("book " + book.getName() + " " + book.getAuthor() + " has been added");
		        	break;
		        	
		        case "remove":
		        	
		        	String name2 = getName(s);
		        	
		        	if (findBooksByName(name2).size() > 1) {
		        		Book bookRemove;
		        		int id = 0;
		        		int i = 1;
		        		List<Book> bookList = findBooksByName(name2);
		        		System.out.println("There are several books with such name:");
		            	for(Book bookTemp : bookList) {
		            		System.out.println(i++ + ". " + bookTemp);
		            	}
		        		System.out.println("Please, input an ID of the book you want to be deleted:");
		        		
		        		while (true) {
		            		try {
			            		id = Integer.parseInt(in.nextLine());
		                		bookRemove = findById(id);
		                		removeBookById(id);
		                		System.out.println("book " + bookRemove + " has been removed");
		                		break;
		            		} catch (EmptyResultDataAccessException e) {
		                		System.out.println("A book with ID " + id + " was not found. Try one more time:");
		            		} catch (NumberFormatException ex) {
		            			System.out.println("Incorrect ID format! Try one more time:");
		            		}
		        		}
		        		
		        	} else if (findBooksByName(name2).size() == 1) {
		        		Book bookRemove = findBooksByName(name2).get(0);
		        		removeBookByName(name2);
		        		System.out.println("book " + bookRemove + " has been removed");
		        	} else if (findBooksByName(name2).size() == 0) {
		        		System.out.println("A book with such name was not found.");
		        	}
		        	break;
		        	
		        case "edit":
		
		        	name = getName(s);
		        	        	
		        	if (findBooksByName(name).size() > 1) {
		        		Book bookEdit = null;
		        		int i = 1;
		        		int id = 0;
		        		List<Book> bookList = findBooksByName(name);
		        		System.out.println("There are several books with such name:");
		            	for(Book bookTemp : bookList) {
		            		System.out.println(i++ + ". " + bookTemp);
		            	}
		        		System.out.println("Please, input an ID of the book you want to be edited:");
		        		
		        		while (true) {
		            		try {
		            			id = Integer.parseInt(in.nextLine());
		                		bookEdit = findById(id);
		                		System.out.println("Input new name:");
		                		String newName = in.nextLine();
		                		editBookNameById(newName, id);
		                    	System.out.println("book " + bookEdit + " has been edited");
		                    	break;
		                		
		            		} catch (EmptyResultDataAccessException e) {
		            			System.out.println("A book with ID " + id + " was not found. Try one more time:");
		            		} catch (NumberFormatException ex) {
		            			System.out.println("Incorrect ID format! Try one more time:");
		            		}
		        		}
		        		break;
		
		        	} else if (findBooksByName(name).size() == 1) {
		        		System.out.println("Input new name:");
		        		String newName = in.nextLine().toString();
		        		Book bookEdit = findBooksByName(name).get(0);
		        		editBookName(name, newName);
		        		System.out.println("book " + bookEdit + " has been edited");

		        	} else if (findBooksByName(name).size() == 0) {
		        		System.out.println("A book with such name was not found.");
		        	}     	
		        	break;
		        	
		        default:
		        	getInstruction();
		        }
        }
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

	public void removeBookByAuthor(String author) {
		
		bookDao.deleteBookByAuthor(author);
	}

	public Book findById(int id) {
		
		return bookDao.findById(id);
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
}
