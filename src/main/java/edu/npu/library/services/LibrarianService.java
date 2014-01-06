package edu.npu.library.services;

import edu.npu.library.domain.Book;
import edu.npu.library.exception.DomainException;

/**
 * @author Neeta Vekariya
 * Services to be accessed by Librarian 
 */

public interface LibrarianService {
	/**
	 * Add book detail
	 * @param book
	 * @throws DomainException if book title already exists
	 */
	public void addBookDetail(Book book) throws DomainException;
	
	/**
	 * Update book detail
	 * @param book
	 * @throws DomainException if book title already exists
	 */
	public void updateBookDetail(Book book) throws DomainException;
	
	
	/**
	 * detail book detail
	 * @param book
	 */
	public void deleteBookDetail(Book book);
}
