package com.pmfarnsw.mylibrary.domain.repository;

import java.util.List;

import javax.sql.DataSource;

import com.pmfarnsw.mylibrary.domain.Library;

public interface LibraryDAO {
	 public void setDataSource(DataSource ds);
	
	 	
	 	//adds book to to Library
	   public void addBook(String author, Integer isbn, Integer publicationDate, String title, String category );
	 

	   //gets book by category
	   public List<Library> getBooksByCategory(String category);
	   

	   //gets all books from library
	   public List<Library> getBooks();
	   

	   //deletes book from library
	   public void deleteBook(Integer isbn);
	  

	   //updates books from library
	   public void updateBook(String author, Integer isbn, Integer publicationDate, String title, String category );
	   
	   public boolean doesBookAlreadyExist(Integer isbn);
	   
	 
}
