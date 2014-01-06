package edu.npu.library.dao.jdbc;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.LibrarianDao;
import edu.npu.library.domain.Book;

/**
 * JDBC impelementation of LibrarianDao
 * @author Neeta Vekariya
  */
@Repository("librarianDao")
@Transactional(propagation=Propagation.REQUIRED)
public class LibrarianDaoJdbcImpl implements LibrarianDao {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	/**
	 * post construct method to initialize class members
	 */
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("bookcatalog")
		                 .usingGeneratedKeyColumns("bookId")
		                 .usingColumns("title", "author", "publisher", "isbn", "edition","quantity","currentstock");
	}
	
	public void addBookDetail(Book book) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(book);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    book.setBookId(newId.intValue());
	}
	
	public void updateBookDetail(Book book) {
		String sql = "update bookcatalog set title =:title , author =:author , publisher =:publisher, "
				+"isbn =:isbn, edition=:edition, quantity=:quantity, currentstock=:currentstock where bookId=:bookId";
		//System.out.println("Book: "+book);
		SqlParameterSource params = new BeanPropertySqlParameterSource(book);
	    namedTemplate.update(sql, params);
	}
	
	public void deleteBookDetail(Book book) {
		String sql = "delete from bookcatalog where bookId =? ";
		jdbcTemplate.update(sql, book.getBookId());
	}

}
