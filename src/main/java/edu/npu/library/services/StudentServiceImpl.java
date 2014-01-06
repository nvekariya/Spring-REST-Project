package edu.npu.library.services;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.library.controllers.CommonController;
import edu.npu.library.dao.CommonDao;
import edu.npu.library.dao.StudentDao;
import edu.npu.library.domain.Book;
import edu.npu.library.exception.DomainException;

/**
 * @author Neeta Vekariya
 * Service to access database API of StudentDao
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	@Qualifier("studentDao")
	StudentDao studentDao;
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	

	@Override
	public void returnBook(int userId, int bookId) throws DomainException{
		studentDao.returnBook(userId, bookId);
		studentDao.incrementBookStock(bookId);
	}

	@Override
	public void loanBook(int userId, int bookId) throws DomainException {
		Book book = commonDao.findBookById(bookId);
		//check book stock
		if(book != null && book.getCurrentstock() == 0){
			Locale currentLocale =  LocaleContextHolder.getLocale();
	    	String msg = messageSource.getMessage("book.emptystock", null, currentLocale);
			logger.error(msg);
			
			throw new DomainException(msg);
		}
		studentDao.loanBook(userId, bookId);
		studentDao.decrementBookStock(bookId);
	}

	@Override
	public List<Book> getLoanBooksList(int userId) {
		return studentDao.getLoanBooksList(userId);
	}

	
}
