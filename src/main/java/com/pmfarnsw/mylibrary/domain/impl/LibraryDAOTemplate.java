package com.pmfarnsw.mylibrary.domain.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pmfarnsw.mylibrary.domain.Library;
import com.pmfarnsw.mylibrary.domain.mapper.LibraryMapper;
import com.pmfarnsw.mylibrary.domain.repository.LibraryDAO;

public class LibraryDAOTemplate implements LibraryDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	
	//creates the connection to the jdbc template
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	//adds a book into the library
	@Override
	public void addBook(String author, Integer isbn, Integer publicationDate,
			String title, String category) {
		String SQL = "insert into Library (isbn, publicationDate, title, category, author) values (?,?,?,?,?)";

		jdbcTemplateObject.update(SQL, isbn, publicationDate, title, category, author);
		System.out.println("Created Book Author = " + author + " title = " + title + " category: "+ category + " isbn: "+isbn + " publication date: "+ publicationDate);
		return;
		
	}

	//gets books by a specific category
	@Override
	public List<Library> getBooksByCategory(String category) {
		String SQL = "select * from Library where category ='"+category+"'";
		List<Library> booksByCategory = jdbcTemplateObject.query(SQL, new LibraryMapper());
		return booksByCategory;
	}

	//gets all books in the library
	@Override
	public List<Library> getBooks() {
		String SQL = "select * from Library where markedForDeletion = 0";
		List<Library> library = jdbcTemplateObject.query(SQL,  new LibraryMapper());
		return library;
	}

	//deletes a book from library
	@Override
	public void deleteBook(Integer isbn) {
		String SQL = "update Library set markedForDeletion = 1 where isbn = ?";
		jdbcTemplateObject.update(SQL, isbn);
		System.out.println("Marked Record with isbn for deletion = " + isbn);
		return;
	}

	
	//updates a book in library
	@Override
	public void updateBook(String author, Integer isbn,
			Integer publicationDate, String title, String category) {
		String SQL = "update Library set author = ?, publicationDate =?, title = ?, category=? where isbn = ?";
		jdbcTemplateObject.update(SQL, author, isbn, publicationDate, title, category);
		System.out.println("Updated Record with isbn = " + isbn);
		return;
		
	}

	@Override
	public boolean doesBookAlreadyExist(Integer isbn) {
		String SQL = "select * from Library where isbn ='"+isbn+"'";
		List<Library> book = jdbcTemplateObject.query(SQL, new LibraryMapper());
		
		if(book.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	

	
}
