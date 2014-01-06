package edu.npu.library.dao.jdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.StudentDao;
import edu.npu.library.domain.Book;
import edu.npu.library.helper.LibraryConstants;

/**
 * JDBC impelementation of StudentDao
 * @author Neeta Vekariya
 */
@Repository("studentDao")
@Transactional(propagation=Propagation.REQUIRED)
public class StudentDaoJdbcImpl implements StudentDao{

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private BookRowMapper bookRowMapper;
	
	/**
	 * post construct method to initialize class members
	 */
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		bookRowMapper = new BookRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("studentbookstatus")
		                 .usingColumns("userId", "bookId", "loanDate");
	}
	
	/**
	 * retuendate will be set for given loaned book
	 * that stats that student has returned that book
	 */
	public void returnBook(int userId, int bookId) {
		String sql = "update studentbookstatus set returnDate =:returnDate"
				+" where userId =:userId and bookId =:bookId";
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(LibraryConstants.DATE_FORMAT_NOW);
		String date = sdf.format(cal.getTime());
		
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("bookId", bookId);
		params.addValue("returnDate", date);
		
		namedTemplate.update(sql, params);
	}

	/**
	 * New row will be created in StudentBookStatus (Join table) table with returndate as null
	 */
	public void loanBook(int userId, int bookId) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(LibraryConstants.DATE_FORMAT_NOW);
		String date = sdf.format(cal.getTime());
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		params.addValue("bookId", bookId);
		params.addValue("loanDate", date);
		
		jdbcInsert.execute(params);
		
		
	}

	@Transactional(readOnly=true)
	public List<Book> getLoanBooksList(int userId) {
		String sql = "select b.bookId, title, author, publisher, isbn, edition, quantity, currentstock from bookcatalog b, studentbookstatus ss "+
				" where b.bookId = ss.bookId and ss.userId ="+userId+" and ss.returnDate is  null ";
		List<Book> bookList = jdbcTemplate.query(sql,
				bookRowMapper);
		return bookList;
	}

	/**
	 * increase currentstock of given book by 1
	 */
	@Override
	public void incrementBookStock(int bookId) {
		String sql = "update bookcatalog set currentstock = currentstock+1 where bookId =?";
		jdbcTemplate.update(sql, bookId);
	}

	/**
	 * decrease currentstock of given book by 1
	 */
	@Override
	public void decrementBookStock(int bookId) {
		String sql = "update bookcatalog set currentstock = currentstock-1 where bookId =?";
		jdbcTemplate.update(sql, bookId);
		
	}

	
}
