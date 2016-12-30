package com.styopik.librarymanager.service;

import java.util.List;

import com.styopik.librarymanager.model.Book;

public interface BookService {
	
	void addBook(Book book);
	
	void removeBookById(int id);
	
	List<Book> findAll();
	List<Book> findBooksByName(String name);
	Book findById(int id);
	
	void editBookNameById(String newName, int id);

}
