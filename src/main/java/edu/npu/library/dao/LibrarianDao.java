package edu.npu.library.dao;

import edu.npu.library.domain.Book;

/**
 * @author Neeta Vekariya
 * Database API to be accessed by Librarian 
 */
public interface LibrarianDao {
	
	/**
	 * Add book detail
	 * @param book
	 */
	public void addBookDetail(Book book);
	
	/**
	 * update book detail
	 * @param book
	 */
	public void updateBookDetail(Book book);
	
	
	/**
	 * detail book detail
	 * @param book
	 */
	public void deleteBookDetail(Book book);
}
