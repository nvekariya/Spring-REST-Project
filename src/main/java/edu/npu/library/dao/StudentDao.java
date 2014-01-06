package edu.npu.library.dao;

import java.util.List;

import edu.npu.library.domain.Book;

/**
 * @author Neeta Vekariya
 * Database API to be accessed by Students 
 */
public interface StudentDao {
	/**
	 * user returns book
	 * @param userId
	 * @param bookId
	 */
	public void returnBook(int userId, int bookId);
	
	/**
	 * user loans book
	 * @param userId
	 * @param bookId
	 */
	public void loanBook(int userId, int bookId);
	
	/**
	 * @param userId
	 * @return books loaned by given user
	 */
	public List<Book> getLoanBooksList(int userId);
	
	/**
	 * Increase book stock by 1
	 * @param bookId
	 */
	public void incrementBookStock(int bookId);
	
	/**
	 * Decrease book stock by 1
	 * @param bookId
	 */
	public void decrementBookStock(int bookId);
	
}
