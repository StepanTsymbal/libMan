package com.styopik.librarymanager.dao;

import java.util.List;

import javax.sql.DataSource;

import com.styopik.librarymanager.model.Book;

public interface BookDAO {
	
	public void setDataSource(DataSource dataSource);
	
	public void addBook(Book book);
	
	public void deleteBook(int id);
	
	public void editBookById(String name, int id);
	
	public List<Book> findAll();
	public List<Book> findByName(String name);
	
	public Book findById(int id);

}
