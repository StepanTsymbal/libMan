package com.styopik.librarymanager.service.impl;

import java.util.List;

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

	public List<Book> findAll() {
		return bookDao.findAll();
	}

	public List<Book> findBooksByName(String name) {
		return bookDao.findByName(name);
	}

	public void editBookNameById(String newName, int id) {
		bookDao.editBookById(newName, id);
	}

	public Book findById(int id) {
		return bookDao.findById(id);
	}
}
