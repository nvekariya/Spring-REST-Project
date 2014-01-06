package edu.npu.library.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.exception.DomainException;
import edu.npu.library.helper.LibraryConstants;
import edu.npu.library.services.StudentService;


/**
 * @author Neeta Vekariya
 * Controller to process requests authorized to students only
 */
@Controller
@RequestMapping(value = "/student/*")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	/**
	 * Retrieves books loaned by given student
	 * @param session
	 * @return list of books in JSON format
	 */
	@RequestMapping(value = "/loanbooklist", method = RequestMethod.GET, produces = LibraryConstants.MEDIA_TYPE_JSON)
	@ResponseBody
	public List<Book> getLoanedBookList(HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<Book> loanBooks = studentService.getLoanBooksList(user.getUserId());
		return loanBooks;
	}

	/**
	 * Book with given book id is returned by given student
	 * @param bookId
	 * @param session
	 * @throws DomainException
	 */
	@RequestMapping(value = "/returnBook/{bookId}", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void returnBook(@PathVariable int bookId, HttpSession session) throws DomainException {
		logger.info("return book");
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		studentService.returnBook(userId, bookId);
	}
	
	/**
	 * Book with given book id is loaned by given user
	 * @param bookId
	 * @param session
	 * @throws DomainException if given book if out of stock 
	 */
	@RequestMapping(value = "/loanBook/{bookId}", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void loanBook(@PathVariable int bookId, HttpSession session) throws DomainException {
		logger.info("loan book");
		
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		studentService.loanBook(userId, bookId);
		
	}
}
