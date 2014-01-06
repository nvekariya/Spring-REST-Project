package edu.npu.library.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.CommonDao;
import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.domain.UserLoginInfo;

/**
 * JDBC impelementation of CommonDao
 * @author Neeta Vekariya
 */
@Repository("commonDao")
public class CommonDaoJdbcImpl implements CommonDao {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private UserRowMapper userRowMapper;
	private BookRowMapper bookRowMapper;
	
	/**
	 * post construct method to initialize class members
	 */
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		userRowMapper = new UserRowMapper();
		bookRowMapper = new BookRowMapper();
		
	}
	@Override
	public User loginUser(UserLoginInfo userInfo) {
		String sql = "SELECT userId, userName, password, firstName, lastName, street, city, state, zipcode, email, userRole " + 
				"FROM User where userName =:userName and password =:password";
		SqlParameterSource params = new BeanPropertySqlParameterSource(userInfo);
	    
		List<User> users = namedTemplate.query(sql, params, userRowMapper);
		if(users.size() == 0)return null;
		
		return users.get(0);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Book> findBooks() {
		String sql = "select b.bookId, title, author, publisher, isbn, edition, quantity, currentstock from bookcatalog b ";
		List<Book> bookList = jdbcTemplate.query(sql,
				bookRowMapper);
		return bookList;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUserProfile(User user) {
		StringBuilder sql = new StringBuilder("update user set userName =:userName ");
		if(user.getPassword() != null){
			sql.append(", password =:password ");
		}
		sql.append(" , firstName =:firstName , lastName =:lastName , street =:street ");
		sql.append(" , city =:city , state =:state , zipcode =:zipcode ");
		sql.append(" , email =:email where userId =:userId ");
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
	    namedTemplate.update(sql.toString(), params);
	}
	
	@Override
	public User findUserById(int userId) {
		String sql = "SELECT userId, userName, password, firstName, lastName, street, city, state, zipcode, email, userRole " + 
				"FROM User where userId =? ";
		
		List<User> users = jdbcTemplate.query(sql,
				userRowMapper, userId);
		if(users == null) return null;
		return users.get(0);
	}
	
	@Override
	public User findUserByName(String userName) {
		String sql = "SELECT userId, userName, password, firstName, lastName, street, city, state, zipcode, email, userRole " + 
				"FROM User where userName =? ";
		
		List<User> users = jdbcTemplate.query(sql,
				userRowMapper, userName);
		
		if(users == null) return null;
		return users.get(0);
	}
	@Override
	public Book findBookById(int bookId) {
		String sql = "select bookId, title, author, publisher, isbn, edition, quantity, currentstock from bookcatalog where bookId =?";
		
		List<Book> books = jdbcTemplate.query(sql,
				bookRowMapper, bookId);
		if(books.size() == 0)return null;
		return books.get(0);
	}
	@Override
	public Book findBookByTitle(String title) {
		String sql = "select bookId, title, author, publisher, isbn, edition, quantity, currentstock from bookcatalog where title =?";
		
		List<Book> books = jdbcTemplate.query(sql,
				bookRowMapper, title);
		
		if(books.size() == 0)return null;
		return books.get(0);
	}
	

}
