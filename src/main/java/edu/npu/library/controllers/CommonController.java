package edu.npu.library.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.npu.library.domain.Book;
import edu.npu.library.domain.User;
import edu.npu.library.exception.DomainException;
import edu.npu.library.helper.LibraryConstants;
import edu.npu.library.services.CommonService;

/**
 * Controller to process requests, those are authorized to all logged-in users
 * @author Neeta Vekariya
 */
@Controller
@RequestMapping("/*")
public class CommonController {
	
	
	@Autowired
	CommonService commonService;
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	/**
	 * Retrieves books from book catalog and returns data in JSON format
	 */
	@RequestMapping(value = "/booklist", method = RequestMethod.GET, produces = LibraryConstants.MEDIA_TYPE_JSON)
	@ResponseBody
	public List<Book> getBookList() {
		List<Book> books = commonService.findBooks();
		return books;
	}
	
	/**
	 * Retrieves book detail for given book id and returns book detail in JSON format
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET, produces = LibraryConstants.MEDIA_TYPE_JSON)
	public @ResponseBody Book getBookDetail(@PathVariable int bookId) 
	{
		logger.info("get book detail");
			
		Book book= commonService.findBookById(bookId);
		return book;
	}
	
	/**
	 * Finds user by username and returns user detail (except password) in JSON format
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/profile/{userName}", method = RequestMethod.GET, produces = LibraryConstants.MEDIA_TYPE_JSON)
	@ResponseBody
	public User getUserProfile(@PathVariable String userName) {
		User user = commonService.findUserByName(userName);
		if(user != null){
			user.setPassword(null);
		}
		return user;
	}
	
	/**
	 * Updates user profile detail and set the same in session
	 * @param user
	 * @param session
	 * @throws DomainException that can be thrown if username already exists
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.PUT, consumes = LibraryConstants.MEDIA_TYPE_JSON)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void updateUserProfile(@Valid @RequestBody User user, HttpSession session) throws DomainException{
		logger.info("Update profile");
		commonService.updateUserProfile(user);
		session.setAttribute("user", user);
	}
	
	
}
