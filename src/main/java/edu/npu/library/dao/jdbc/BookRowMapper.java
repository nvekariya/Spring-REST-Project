package edu.npu.library.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.library.domain.Book;

/**
 * Mapper to map resultset data with Book object
 * @author Neeta Vekariya
 *
 */
public class BookRowMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet resultSet, int row) throws SQLException {

		Book book = new Book();
		book.setBookId(resultSet.getInt("bookId"));
		book.setTitle(resultSet.getString("title"));
		book.setAuthor(resultSet.getString("author"));
		book.setEdition(resultSet.getInt("edition"));
		book.setPublisher(resultSet.getString("publisher"));
		book.setIsbn(resultSet.getLong("isbn"));
		book.setQuantity(resultSet.getInt("quantity"));
		book.setCurrentstock(resultSet.getInt("currentstock"));
		
		return book;
	}

}
