package com.styopik.librarymanager.service;

import java.util.List;

import com.styopik.librarymanager.model.Book;

public interface BookService {
	
	public void addBook(Book book);
	
	public void removeBookById(int id);
	
	public List<Book> findAll();
	public List<Book> findBooksByName(String name);
	public Book findById(int id);
	
	public void editBookNameById(String newName, int id);

}
