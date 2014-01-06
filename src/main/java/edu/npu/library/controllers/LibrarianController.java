package edu.npu.library.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.npu.library.domain.Book;
import edu.npu.library.exception.DomainException;
import edu.npu.library.helper.LibraryConstants;
import edu.npu.library.services.LibrarianService;

/**
 * Controller to process requests, those are authorized to librarian only
 * @author Neeta Vekariya
 */
@Controller
@RequestMapping(value = "/librarian/*")
public class LibrarianController {
	
	
	@Autowired
	LibrarianService librarianService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);
	
	/**
	 * Updates book detail for given book id. It consumes book data in JSON format
	 * @param book
	 * @param bookId
	 * @throws DomainException if book title already exists
	 */
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.PUT, consumes = LibraryConstants.MEDIA_TYPE_JSON)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void updateBookDetail(@Valid @RequestBody Book book, @PathVariable int bookId) throws DomainException
	{
		logger.info("Update book detail");
		book.setBookId(bookId);
		librarianService.updateBookDetail(book);
		
	}
	
	/**
	 * Adds book detail. It consumes and returns book data in JSON format
	 * @param book
	 * @return
	 * @throws DomainException if book title already exists
	 */
	@RequestMapping(value = "/book", method = RequestMethod.POST, consumes = LibraryConstants.MEDIA_TYPE_JSON, produces = LibraryConstants.MEDIA_TYPE_JSON)
	public @ResponseBody Book addBookDetail(@Valid @RequestBody Book book) throws DomainException
	{
		logger.info("Add book detail");
		librarianService.addBookDetail(book);
		return book;
	}
	
	
}