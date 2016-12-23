package com.styopik.librarymanager.service;

import java.util.List;

import com.styopik.librarymanager.model.Book;

public interface BookService {
	
	public void addBook(Book book);
	
	public void removeBookById(int id);
	public void removeBookByName(String name);
	public void removeBookByAuthor(String author);
	
	public List<Book> findAll();
	public List<Book> findBooksByName(String name);
	public Book findById(int id);
	
	public void editBookName(String oldName, String newName);
	public void editBookNameById(String newName, int id);
	
	public void manage();

}
