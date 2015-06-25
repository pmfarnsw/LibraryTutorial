package com.pmfarnsw.mylibrary.domain.mapper;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pmfarnsw.mylibrary.domain.Library;

public class LibraryMapper implements RowMapper<Library> {
   
	//maps data retrieved from database to pojo
	public Library mapRow(ResultSet rs, int rowNum) throws SQLException {
      Library library = new Library();
      library.setIsbn(rs.getInt("isbn"));
      library.setAuthor(rs.getString("author"));
      library.setTitle(rs.getString("title"));
      library.setCategory(rs.getString("category"));
      library.setPublicationDate(rs.getInt("publicationDate"));
      return library;
   }

}
