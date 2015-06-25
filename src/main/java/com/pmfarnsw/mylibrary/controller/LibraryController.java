package com.pmfarnsw.mylibrary.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmfarnsw.mylibrary.domain.Library;
import com.pmfarnsw.mylibrary.services.LibraryService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LibraryController {
	
	
	private LibraryService libraryService = new LibraryService();
	
	
	//loads the viewLibrary jsp 
	@RequestMapping(value = "/LibraryCatalog", method = RequestMethod.GET)
	public String libraryCatalog() {	

		
		return "LibraryCatalog";
	}
	
	
	//retrieves the library from the database after receiving an ajax request
	@RequestMapping(value = "/ViewLibrary", method = RequestMethod.GET)
	
	public @ResponseBody List<Library> viewLibrary()
	{
		 List<Library> libraryData = libraryService.getLibrary();
		
			return libraryData;
	}
	
	
	//will send isbn string to database after receiving it from from ajax put 
   @RequestMapping(value = "/remove/{isbn}", method = RequestMethod.PUT)
	
	public @ResponseBody void deleteBook(@PathVariable Integer isbn)
	{
		 libraryService.deleteBook(isbn);
		
			
	}
   
   //will send a category string to database and return library containing only the category specified by the string after retrieving it from an ajax method
   @RequestMapping(value = "/sortLibrary/{category}", method = RequestMethod.GET)
	
	public @ResponseBody List<Library> sortLibrary(@PathVariable String category)
	{
		 return libraryService.sortLibrary(category);
		
			
	}
   
   // adds a book through database  after parsing from json and retrieving json from ajax put
   @RequestMapping(value = "/add/{book}", method = RequestMethod.POST)
	
	public @ResponseBody List<Library> addBook(@PathVariable String book) throws JsonProcessingException, IOException
	{
	   
	   
	   ObjectMapper mapper = new ObjectMapper();
   
	 
	   List<Library> booksToBeAdded = mapper.readValue(book, mapper.getTypeFactory().constructCollectionType(List.class, Library.class));
	   
	 

	
	   
     List<Library> booksThatWereNotAdded = libraryService.addBook(booksToBeAdded);
     System.out.println("unable to add books " + booksThatWereNotAdded.size());
		
	return booksThatWereNotAdded;
	}
	
}
