package edu.npu.library.services;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.dao.CommonDao;
import edu.npu.library.dao.LibrarianDao;
import edu.npu.library.domain.Book;
import edu.npu.library.exception.DomainException;

/**
 * @author Neeta Vekariya
 * Service to access database API of LibrarianDao
 */
@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService {
	@Autowired
	@Qualifier("librarianDao")
	private LibrarianDao librarianDao;
	
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final Logger logger = LoggerFactory.getLogger(LibrarianServiceImpl.class);
	

	@Override
	public void addBookDetail(Book book) throws DomainException{
		//validate book title
		validateBookTitle(book);
		librarianDao.addBookDetail(book);
		
	}

	@Override
	public void updateBookDetail(Book book)  throws DomainException{
		//validate book title
		validateBookTitle(book);
		librarianDao.updateBookDetail(book);
	}

	
	@Override
	public void deleteBookDetail(Book book)  {
		librarianDao.deleteBookDetail(book);
	}
	
	/**
	 * validates book tile 
	 * @param book
	 * @throws DomainException if title already exists in database for other book
	 */
	public void validateBookTitle(Book book) throws DomainException{
		Book dbBook = commonDao.findBookByTitle(book.getTitle());
		if(dbBook != null && (book.getBookId() != dbBook.getBookId())) {
			Locale currentLocale =  LocaleContextHolder.getLocale();
			String msg = messageSource.getMessage("bookname.exists", null, currentLocale);
			logger.error(msg);
	    	throw new DomainException(msg);
		}
	}

}
