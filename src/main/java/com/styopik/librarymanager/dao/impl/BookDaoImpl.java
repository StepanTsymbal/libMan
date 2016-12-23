package com.styopik.librarymanager.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.styopik.librarymanager.dao.BookDAO;
import com.styopik.librarymanager.model.Book;
import com.styopik.librarymanager.util.BookMapper;

public class BookDaoImpl implements BookDAO {
	
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addBook(Book book) {
		
		String sql = "INSERT INTO BOOK (NAME, AUTHOR) VALUES (?, ?)";

		jdbcTemplate.update(sql, book.getName(), book.getAuthor());
	}

	public void deleteBook(int id) {
		
		String sql = "DELETE FROM BOOK WHERE ID = ?";
      
      	jdbcTemplate.update(sql, id);
	}

	public void editBookById(String name, int id) {
		
		String sql = "UPDATE BOOK SET NAME = ? WHERE ID = ?";
  
		jdbcTemplate.update(sql, name, id);
	}

	public List<Book> findAll() {
		
        String sql = "SELECT * FROM BOOK";
        
        return jdbcTemplate.query(sql, new BookMapper());
	}

	public List<Book> findByName(String name) {
		
		String sql = "SELECT * FROM BOOK WHERE NAME IN('" + name + "'," + "'\"" + name + "\"')";

        return jdbcTemplate.query(sql, new BookMapper());
	}

	public void deleteBookByName(String name) {
		
		String sql = "DELETE FROM BOOK WHERE NAME = ?";
	      
		jdbcTemplate.update(sql, name);
	}

	public void editBookByName(String oldName, String newName) {
		
		String sql = "UPDATE BOOK SET NAME = ? WHERE NAME = " +  "'" + oldName + "'";

		jdbcTemplate.update(sql, newName);
	}

	public void deleteBookByAuthor(String author) {
		
		String sql = "DELETE FROM BOOK WHERE AUTHOR = ?";
 
		jdbcTemplate.update(sql, author);
	}

	public Book findById(int id) {
		
	    String sql = "SELECT * FROM BOOK WHERE ID = " + id;

	    return jdbcTemplate.queryForObject(sql, new BookMapper());
	}

}
