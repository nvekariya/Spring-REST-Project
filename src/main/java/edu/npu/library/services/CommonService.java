package edu.npu.library.services;

import java.util.List;

import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.domain.UserLoginInfo;
import edu.npu.library.exception.DomainException;

/**
 * @author Neeta Vekariya
 * Services  to be accessed by all users
 */
public interface CommonService {

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
	 * @param userName
	 * @return user for given user name
	 */
	public User findUserByName(String userName);
	
	
	/**
	 * Update user profile for given user
	 * @param user
	 * @throws DomainException if username alreasy exists
	 */
	public void updateUserProfile(User user)  throws DomainException;
}
