package edu.npu.library.dao;

import java.util.List;

import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.domain.UserLoginInfo;
/**
 * @author Neeta Vekariya
 * Database API to be accessed by all users
 */
public interface CommonDao {

	/**
	 * @param user
	 * @return user detail based on given login username and password
	 */
	public User loginUser(UserLoginInfo user);
	
	/**
	 * @return list of books available in book catalog 
	 */
	public List<Book> findBooks();
	
	/**
	 * @param bookId
	 * @return book detail for given book id
	 */
	public Book findBookById(int bookId);
	
	/**
	  * @param title
	 * @return book detail for given title
	 */
	public Book findBookByTitle(String title);
	
	/**
	 * @param userId
	 * @return user for given user id
	 */
	public User findUserById(int userId);
	
	/**
	 * @param userName
	 * @return user for given user name
	 */
	public User findUserByName(String userName);
	
	/**
	 * Update user profile for given user
	 * @param user
	 */
	public void updateUserProfile(User user);
}
