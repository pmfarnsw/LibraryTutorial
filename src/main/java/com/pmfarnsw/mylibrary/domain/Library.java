package com.pmfarnsw.mylibrary.domain;

public class Library {

	//variables for library pojo
	private Integer isbn;
	private Integer publicationDate;
	private String title;
	private String author;
	private String category;

	
	//getters and setters declared
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setPublicationDate(Integer publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Integer getPublicationDate() {
		return publicationDate;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public Integer getIsbn() {
		return isbn;
	}
}
