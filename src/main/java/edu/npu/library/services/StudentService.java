package edu.npu.library.services;

import java.util.List;

import edu.npu.library.domain.Book;
import edu.npu.library.exception.DomainException;

/**
 * @author Neeta Vekariya
 * Database API to be accessed by Students 
 */

public interface StudentService {
	
	/**
	 * @param userId
	 * @param bookId
	 * @throws DomainException
	 */
	public void returnBook(int userId, int bookId) throws DomainException;
	
	
	/**
	 * @param userId
	 * @param bookId
	 * @throws DomainException if book is out of stock
	 */
	public void loanBook(int userId, int bookId) throws DomainException;
	
	/**
	 * @param userId
	 * @return books loaned by given user
	 */
	public List<Book> getLoanBooksList(int userId);
}
