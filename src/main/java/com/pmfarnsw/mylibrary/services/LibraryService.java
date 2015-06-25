package com.pmfarnsw.mylibrary.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pmfarnsw.mylibrary.domain.Library;
import com.pmfarnsw.mylibrary.domain.impl.LibraryDAOTemplate;

public class LibraryService {

	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	LibraryDAOTemplate libraryDAOTemplate =  (LibraryDAOTemplate)context.getBean("libraryDAOTemplate");
    
	//gets library from database
	public List<Library> getLibrary()
	{
		return libraryDAOTemplate.getBooks();
	}
	
	//adds book to database
	public List<Library> addBook(List<Library> books)
	{
		List<Library> booksWithInvalidISBN = new ArrayList<Library>();
		
		for(int i = 0; i < books.size(); i++)
		{
			if(libraryDAOTemplate.doesBookAlreadyExist(books.get(i).getIsbn())){
				libraryDAOTemplate.addBook(books.get(i).getAuthor(), books.get(i).getIsbn(), books.get(i).getPublicationDate(), books.get(i).getTitle(), books.get(i).getCategory());
			} else
			{
				booksWithInvalidISBN.add(books.get(i));
			}
			
		}
		return booksWithInvalidISBN;
	}
	
	//deletes a book from database
	public void deleteBook(Integer isbn)
	{
		libraryDAOTemplate.deleteBook(isbn);
	}
	
	
	//sorts library based on category
	public List<Library> sortLibrary(String category)
	{
		return libraryDAOTemplate.getBooksByCategory(category);
	}
}
